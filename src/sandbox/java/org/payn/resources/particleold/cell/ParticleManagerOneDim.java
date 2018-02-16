package org.payn.resources.particleold.cell;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.payn.chsm.finitedifference.Holon;
import org.payn.chsm.processors.ProcessorLong;
import org.payn.chsm.resources.time.BehaviorTime;
import org.payn.chsm.resources.time.Iteration;
import org.payn.chsm.resources.time.Time;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueString;
import org.payn.chsm.values.ValueTimeSeries;

import neoch.HolonCell;
import neoch.HolonMatrix;
import neoch.UpdaterInfo;

public class ParticleManagerOneDim extends ParticleManager implements UpdaterPostauxiliary {
   
   private class ResourceStructure {
      
      public ParticleMean particleMean;
      public ParticleTimeSeries particleTimeSeries;
      public ArrayList<ParticleOneDim> finishedParticles;
     
      public ResourceStructure(ParticleTimeSeries particleTimeSeries, ParticleMean particleMean)
      {
         this.particleTimeSeries = particleTimeSeries;
         this.particleMean = particleMean;
         finishedParticles = new ArrayList<ParticleOneDim>();
      }
   }

   LinkedHashMap<String, ResourceStructure> resources;
   ArrayList<ParticleOneDim> particles;
   private HolonCell releaseCell;
   private HolonCell downstreamCell;
   private ValueLong tick;
   private ValueLong releaseInterval;
   private ValueDouble time;
   private ValueLong recordInterval;
   private ValueDouble timeStep;
   
   public ParticleManagerOneDim()
   {
      resources = new LinkedHashMap<String, ResourceStructure>();
      particles = new ArrayList<ParticleOneDim>();
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      ValueString releaseCellName = (ValueString)createDependency(
            BehaviorConcTracker.REQ_STATE_RELEASE_NAME
            ).getValue();
      releaseCell = ((HolonMatrix)loadController().getState()).getCell(releaseCellName.string);
      
      ValueString endCellName = (ValueString)createDependency(
            BehaviorConcTracker.REQ_STATE_END_NAME
            ).getValue();
      downstreamCell = ((HolonMatrix)loadController().getState()).getCell(endCellName.string);
      
      tick = (ValueLong)createDependency(
            (Holon)loadController().getState(),
            BehaviorTime.DEFAULT_ITERATION_NAME
            ).getValue();
      time = (ValueDouble)createDependency(
            (Holon)loadController().getState(),
            BehaviorTime.DEFAULT_TIME_NAME
            ).getValue();
      timeStep = (ValueDouble)createDependency(
            (Holon)loadController().getState(),
            BehaviorTime.ITERATION_INTERVAL
            ).getValue();
      releaseInterval = (ValueLong)createDependency(
            BehaviorConcTracker.REQ_STATE_INTERVAL_RELEASE
            ).getValue();
      recordInterval = (ValueLong)createDependency(
            BehaviorConcTracker.REQ_STATE_INTERVAL_RECORD
            ).getValue();
//      for (Entry<String, CurrencyStructure> currency: currencies.entrySet())
//      {
//         createDependency(currency.getValue().particleTimeSeries.getState().getName());
//         createDependency(currency.getValue().particleMean.getState().getName());
//      }
   }

   @Override
   public void update() throws Exception 
   {
      for (ParticleOneDim particle: particles)
      {
         particle.update();
      }
      for (Entry<String, ResourceStructure> currency: resources.entrySet())
      {
         ArrayList<ParticleOneDim> finishedParticleList = currency.getValue().finishedParticles;
         if (!finishedParticleList.isEmpty())
         {
            ValueTimeSeries finishedParticle = finishedParticleList.get(finishedParticleList.size() - 1).getValue();
            currency.getValue().particleTimeSeries.getValue().map = finishedParticle.map;
            currency.getValue().particleMean.getValue().n = finishedParticle.getTimeWeightedMean();
            particles.removeAll(finishedParticleList);
            finishedParticleList.clear();
         }
      }
      if (tick.n == 1 || tick.n % releaseInterval.n == 0)
      {
         releaseParticle();
      }
      value.n = particles.size();
   }
   
   public void reportFinishedParticle(ParticleOneDim particle)
   {
      resources.get(particle.getResourceName()).finishedParticles.add(particle);
   }

   private void releaseParticle() 
   {
      for (Entry<String, ResourceStructure> resource: resources.entrySet())
      {
         ParticleOneDim particle = new ParticleOneDim(this, resource.getKey());
         particle.initializeTime(tick, time, timeStep, recordInterval);
         particle.initializeSpace(releaseCell, downstreamCell);
         particles.add(particle);
      }
   }

   public void addCurrencyWatch(String currencyName, ParticleMean meanProc,
         ParticleTimeSeries timeSeriesProc) 
   {
      resources.put(currencyName, new ResourceStructure(timeSeriesProc, meanProc));
   }

}
