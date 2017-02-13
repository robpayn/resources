package org.payn.resources.particle.cell;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.payn.chsm.State;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.resources.particle.Particle;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;

import neoch.HolonBoundary;
import neoch.HolonCell;

public abstract class ParticleConcTracker implements Particle {

   protected ParticleManagerVel particleManager;
   protected String resourceName;
   private ValueLong tick;
   private ValueDouble time;
   protected ValueDouble timeStep;
   private ValueLong interval;
   private double startTime;
   protected HolonCell currentCell;
   protected HolonCell endCell;
   protected HolonBoundary currentBound;
   protected double endDistance;
   protected double currentDistance;
   protected BufferedWriter writer;

   public ParticleConcTracker(ParticleManagerVel particleManager,
         String resourceName) 
   {
      this.particleManager = particleManager;
      this.resourceName = resourceName;
   }

   @Override
   public void update() throws Exception 
   {
      if (tick.n % interval.n == 0)
      {
         sample();
      }
      move();
   }

   public void sample() throws IOException 
   {
      double conc = ((ValueDouble)currentBound.getCell().getState(
            resourceName + ResourceSolute.NAME_SOLUTE_CONC).getValue()).n;
      HolonBoundary adjBound = currentBound.getAdjacentBoundary();
      double adjConc;
      if (adjBound != null)
      {
         adjConc = ((ValueDouble)adjBound.getCell().getState(
               resourceName + ResourceSolute.NAME_SOLUTE_CONC).getValue()).n;
      }
      else
      {
         adjConc = ((ValueDouble)currentBound.getState(
               resourceName + ResourceSolute.NAME_SOLUTE_CONC).getValue()).n;
      }
      if (isFlowPositive())
      {
         conc = conc + ((endDistance - currentDistance) / (2 * endDistance)) * (adjConc - conc);
      }
      else
      {
         conc = conc + (currentDistance / (2 * endDistance)) * (adjConc - conc);
      }
      writer.write(String.format("%d %f %f", tick.n, time.n, conc));
      writer.newLine();
   }

   @Override
   public void initializeTime(ValueLong tick, ValueDouble time,
         ValueDouble timeStep, ValueLong interval) 
   {
      this.tick = tick;
      this.time = time;
      this.timeStep = timeStep;
      this.interval = interval;
      startTime = time.n;
   }


   @Override
   public void initializeOutput(int particleCount, String output) throws IOException 
   {
      File outputDir = new File(output + File.separator + resourceName);
      if (!outputDir.exists())
      {
         outputDir.mkdirs();
      }
      writer = new BufferedWriter(new FileWriter(new File(
            outputDir.getAbsolutePath() + File.separator + 
            String.format("particle_%06d", particleCount) + ".txt"
            )));
   }

   protected boolean isFlowPositive() 
   {
      State state = currentBound.getState(BehaviorSoluteFlow.REQ_STATE_FLOW);
      if (state == null)
      {
         state = currentBound.getAdjacentBoundary().getState(
               BehaviorSoluteFlow.REQ_STATE_FLOW
               );
         return -((ValueDouble)state.getValue()).n > 0;
      }
      else
      {
         return ((ValueDouble)state.getValue()).n > 0;
      }
   }
   
   @Override
   public String getResourceName()
   {
      return resourceName;
   }
   
   @Override
   public void close() throws IOException
   {
      writer.close();
   }

   protected abstract void move() throws IOException;

}
