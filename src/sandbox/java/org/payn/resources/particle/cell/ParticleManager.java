package org.payn.resources.particle.cell;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.payn.chsm.Holon;
import org.payn.chsm.processors.ProcessorLong;
import org.payn.chsm.resources.time.BehaviorTime;
import org.payn.chsm.resources.time.Iteration;
import org.payn.chsm.resources.time.Time;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueString;
import org.payn.chsm.values.ValueTimeSeries;
import org.payn.resources.particle.Particle;

import neoch.HolonCell;
import neoch.HolonMatrix;
import neoch.UpdaterInfo;

public abstract class ParticleManager extends ProcessorLong implements UpdaterInfo {
   
   class CurrencyStructure {
      
      public ParticleMean particleMean;
      public ParticleTimeSeries particleTimeSeries;
      public ArrayList<Particle> finishedParticles;
     
      public CurrencyStructure(ParticleTimeSeries particleTimeSeries, ParticleMean particleMean)
      {
         this.particleTimeSeries = particleTimeSeries;
         this.particleMean = particleMean;
         finishedParticles = new ArrayList<Particle>();
      }
   }

   LinkedHashMap<String, CurrencyStructure> currencies;
   ArrayList<Particle> particles;
   protected HolonCell releaseCell;
   protected HolonCell endCell;
   protected ValueLong tick;
   protected ValueLong releaseInterval;
   protected ValueDouble time;
   protected ValueLong recordInterval;
   protected ValueDouble timeStep;
   
   public ParticleManager()
   {
      currencies = new LinkedHashMap<String, CurrencyStructure>();
      particles = new ArrayList<Particle>();
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      ValueString releaseCellName = (ValueString)createDependency(
            BehaviorConcTrackerLagrange.REQ_STATE_RELEASE_NAME
            ).getValue();
      releaseCell = ((HolonMatrix)getController().getState()).getCell(releaseCellName.string);
      
      ValueString endCellName = (ValueString)createDependency(
            BehaviorConcTrackerLagrange.REQ_STATE_END_NAME
            ).getValue();
      endCell = ((HolonMatrix)getController().getState()).getCell(endCellName.string);
      
      tick = (ValueLong)createDependency(
            (Holon)getController().getState(),
            BehaviorTime.DEFAULT_ITERATION_NAME
            ).getValue();
      time = (ValueDouble)createDependency(
            (Holon)getController().getState(),
            BehaviorTime.DEFAULT_TIME_NAME
            ).getValue();
      timeStep = (ValueDouble)createDependency(
            (Holon)getController().getState(),
            BehaviorTime.ITERATION_INTERVAL
            ).getValue();
      releaseInterval = (ValueLong)createDependency(
            BehaviorConcTrackerLagrange.REQ_STATE_INTERVAL_RELEASE
            ).getValue();
      recordInterval = (ValueLong)createDependency(
            BehaviorConcTrackerLagrange.REQ_STATE_INTERVAL_RECORD
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      for (Particle particle: particles)
      {
         particle.update();
      }
      for (Entry<String, CurrencyStructure> currency: currencies.entrySet())
      {
         ArrayList<Particle> finishedParticleList = currency.getValue().finishedParticles;
         if (!finishedParticleList.isEmpty())
         {
            ValueTimeSeries finishedParticle = finishedParticleList.get(finishedParticleList.size() - 1).getValue();
            currency.getValue().particleTimeSeries.getValue().map = finishedParticle.map;
            currency.getValue().particleMean.getValue().n = finishedParticle.getTimeWeightedMean();
            particles.removeAll(finishedParticleList);
            finishedParticleList.clear();
         }
      }
      releaseParticleConditional();
      value.n = particles.size();
   }
   
   public void reportFinishedParticle(Particle particle)
   {
      currencies.get(particle.getResourceName()).finishedParticles.add(particle);
   }

   public void addCurrencyWatch(String currencyName, ParticleMean meanProc,
         ParticleTimeSeries timeSeriesProc) 
   {
      currencies.put(currencyName, new CurrencyStructure(timeSeriesProc, meanProc));
   }

   protected void releaseParticle() 
   {
      for (Entry<String, CurrencyStructure> currency: currencies.entrySet())
      {
         Particle particle = ((BehaviorParticle)state.getBehavior()).createParticle(this, currency.getKey());
         particle.initializeTime(tick, time, timeStep, recordInterval);
         particle.initializeLocation(releaseCell, endCell);
         particles.add(particle);
      }
   }

   protected abstract void releaseParticleConditional() throws Exception;

}
