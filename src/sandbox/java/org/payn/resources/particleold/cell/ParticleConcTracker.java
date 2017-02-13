package org.payn.resources.particleold.cell;

import org.payn.chsm.processors.ProcessorTimeSeries;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueTimeSeries;
import org.payn.resources.particle.Particle;
import org.payn.resources.particle.ResourceParticle;
import org.payn.resources.solute.ResourceSolute;

import neoch.HolonCell;

public class ParticleConcTracker extends ProcessorTimeSeries implements Particle {

   protected String resourceName;

   @Override
   public String getResourceName() 
   {
      return resourceName;
   }

   protected ParticleManager manager;
   protected ValueLong tick;
   protected ValueDouble time;
   protected ValueDouble timeStep;
   protected ValueLong interval;
   protected double startTime;
   
   protected HolonCell currentCell;
   
   @Override
   public void setCurrentCell(HolonCell cell) 
   {
      this.currentCell = cell;
   }

   protected HolonCell endCell;

   public ParticleConcTracker(ParticleManager manager, String resourceName) 
   {
      this.manager = manager;
      this.resourceName = resourceName;
      value = new ValueTimeSeries();
      value.newValue();
   }

   @Override
   public void update() throws Exception 
   {
      if (tick.n % interval.n == 0)
      {
         sample();
      }
      if (currentCell.getName().equals(endCell.getName()))
      {
         manager.reportFinishedParticle(this);
         ParticleBin bin = (ParticleBin)endCell.getState(
               manager.getResourceName() + ResourceParticle.NAME_BIN).getProcessor();
         bin.removeParticle(this);
      }
   }

   @Override
   public void sample() 
   {
      double conc = ((ValueDouble)currentCell.getState(
            resourceName + ResourceSolute.NAME_SOLUTE_CONC).getValue()).n;
      value.addValue(
            new ValueDouble(time.n), 
            new ValueDouble(conc)
            );
   }

   @Override
   public void initializeTime(ValueLong tick, ValueDouble time, ValueDouble timeStep, ValueLong interval) 
   {
      this.tick = tick;
      this.time = time;
      this.timeStep = timeStep;
      this.interval = interval;
      startTime = time.n;
   }

   @Override
   public void initializeLocation(HolonCell releaseCell, HolonCell endCell) 
   {
      ((ParticleBin)releaseCell.getState(manager.getResourceName() + ResourceParticle.NAME_BIN).getProcessor()).addParticle(this);
      this.endCell = endCell;
   }

}
