package org.payn.resources.particle.cell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.payn.chsm.finitedifference.Holon;
import org.payn.chsm.processors.ProcessorLong;
import org.payn.chsm.resources.time.BehaviorTime;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueString;
import org.payn.resources.particle.Particle;

import neoch.HolonCell;
import neoch.HolonMatrix;
import neoch.UpdaterInfo;

/**
 * Particle manager for velocity distributed particles
 * 
 * @author robpayn
 *
 */
public class ParticleManagerVel extends ProcessorLong implements UpdaterInfo {

   /**
    * Map of resources being tracked by particles
    */
   LinkedHashMap<String, ArrayList<Particle>> finishedParticles;
   /**
    * List of active particles
    */
   ArrayList<Particle> particles;
   /**
    * Cell where particles are released
    */
   private HolonCell releaseCell;
   /**
    * Cells where particles are finished
    */
   private HolonCell endCell;
   /**
    * Interval
    */
   private ValueLong tick;
   /**
    * Simulation time
    */
   private ValueDouble time;
   /**
    * Time step
    */
   private ValueDouble timeStep;
   /**
    * Iteration where particles are released
    */
   private ValueLong releaseInterval;
   /**
    * Interval at which particles record the resource
    */
   private ValueLong recordInterval;
   private String output;
   private String velocityFile;

   public ParticleManagerVel()
   {
      finishedParticles = new LinkedHashMap<String, ArrayList<Particle>>();
      particles = new ArrayList<Particle>();
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      ValueString releaseCellName = (ValueString)createDependency(
            BehaviorConcTrackerVel.REQ_STATE_RELEASE_NAME
            ).getValue();
      releaseCell = ((HolonMatrix)loadController().getState()).getCell(releaseCellName.string);
      
      ValueString endCellName = (ValueString)createDependency(
            BehaviorConcTrackerVel.REQ_STATE_END_NAME
            ).getValue();
      endCell = ((HolonMatrix)loadController().getState()).getCell(endCellName.string);
      
      String outputLocation = ((ValueString)createDependency(
            BehaviorConcTrackerVel.REQ_STATE_OUTPUT_LOC
            ).getValue()).string;
      output = System.getProperty("user.dir") + File.separator + outputLocation;
      
      velocityFile = ((ValueString)createDependency(
            BehaviorConcTrackerVel.REQ_STATE_VEL_FILE
            ).getValue()).string;
      
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
            BehaviorConcTrackerVel.REQ_STATE_INTERVAL_RELEASE
            ).getValue();
      recordInterval = (ValueLong)createDependency(
            BehaviorConcTrackerVel.REQ_STATE_INTERVAL_RECORD
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      for (Particle particle: particles)
      {
         particle.update();
      }
      for (Entry<String, ArrayList<Particle>> resource: finishedParticles.entrySet())
      {
         ArrayList<Particle> particleList = resource.getValue();
         for(Particle particle: particleList)
         {
            particle.close();
            particles.remove(particle);
         }
         particleList.clear();
      }
      if (tick.n == releaseInterval.n)
      {
         BufferedReader reader = new BufferedReader(new FileReader(new File(
               System.getProperty("user.dir") + File.separator + velocityFile
               )));
         int particleCount = 0;
         while(reader.ready())
         {
            double velocity = Double.valueOf(reader.readLine());
            for (Entry<String, ArrayList<Particle>> resource: finishedParticles.entrySet())
            {
               Particle particle = new ParticleVel(this, resource.getKey(), velocity);
               particle.initializeTime(tick, time, timeStep, recordInterval);
               particle.initializeLocation(releaseCell, endCell);
               particle.initializeOutput(particleCount, output);
               particles.add(particle);
               particleCount++;
            }
         }
         reader.close();
      }

      value.n = particles.size();
   }

   public void reportFinishedParticle(Particle particle)
   {
      finishedParticles.get(particle.getResourceName()).add(particle);
   }

   public void addResource(String resourceName) 
   {
      finishedParticles.put(resourceName, new ArrayList<Particle>());
   }
   
}
