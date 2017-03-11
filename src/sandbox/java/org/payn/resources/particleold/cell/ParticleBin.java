package org.payn.resources.particleold.cell;

import java.util.ArrayList;
import java.util.Random;

import org.payn.chsm.Holon;
import org.payn.chsm.State;
import org.payn.chsm.resources.time.BehaviorTime;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueParticleList;
import org.payn.resources.particle.Particle;
import org.payn.resources.particle.ParticleList;
import org.payn.resources.particle.boundary.ParticleLoad;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;
import org.payn.resources.solute.cell.BehaviorSoluteStorage;

import neoch.HolonBoundary;
import neoch.HolonCell;
import neoch.HolonMatrix;
import neoch.UpdaterStorage;
import neoch.behaviors.BehaviorMatrix;

public class ParticleBin extends ParticleList implements UpdaterStorage {

   ParticleLoad incomingLoad;
   ParticleLoad outgoingLoad;
   private Random random;
   private ValueDouble outFlow;
   private ValueDouble volume;
   private ValueDouble timeInterval;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      super.setUpdateDependencies();
      
      for (HolonBoundary boundary: ((HolonCell)state.getParentHolon()).getBoundaryMap().values())
      {
         ArrayList<State> loadList = boundary.getLoads(
               ((BehaviorMatrix)getState().getBehavior()).getResource()
               );
         boolean isFlowIn = false;
         if (loadList == null)
         {
            if (boundary.getAdjacentBoundary() != null)
            {
               loadList = boundary.getAdjacentBoundary().getLoads(
                     ((BehaviorMatrix)getState().getBehavior()).getResource()
                     );
               if (loadList != null)
               {
                  isFlowIn = true;
               }
            }
         }
         else
         {
            outFlow = (ValueDouble)createDependency(boundary, BehaviorSoluteFlow.NAME_WATER_FLOW).getValue();
         }
         
         if (loadList != null)
         {
            if (loadList.size() > 1)
            {
               throw new Exception("Particle tracker not configured properly");
            }
            if (isFlowIn)
            {
               incomingLoad = (ParticleLoad)loadList.get(0).getProcessor();
            }
            else
            {
               outgoingLoad = (ParticleLoad)loadList.get(0).getProcessor();
            }
         }
      }
      
      volume = (ValueDouble)createDependency(BehaviorSoluteStorage.REQ_STATE_VOLUME).getValue();
      Holon matrixHolon = (Holon)loadController().getState();
      timeInterval = (ValueDouble)matrixHolon.getState(BehaviorTime.ITERATION_INTERVAL).getValue();
      random = new Random(System.currentTimeMillis());
   }

   @Override
   public void update() throws Exception 
   {
      if (outgoingLoad != null)
      {
         double fractionDrained = -(timeInterval.n * outFlow.n) / volume.n;
         ArrayList<Particle> remove = new ArrayList<Particle>();
         for (Particle particle: value.particleList)
         {
            if (random.nextDouble() < fractionDrained)
            {
               outgoingLoad.addParticle(particle);
               remove.add(particle);
            }
         }
         value.particleList.removeAll(remove);
      }      
      if (incomingLoad != null)
      {
         for (Particle particle: incomingLoad.getParticleList())
         {
            value.particleList.add(particle);
            particle.setCurrentCell((HolonCell)state.getParentHolon());
         }
      }
   }

   @Override
   public void addParticle(Particle particle) 
   {
      super.addParticle(particle);
      particle.setCurrentCell((HolonCell)state.getParentHolon());
   }

   public void removeParticle(Particle particle) 
   {
      value.particleList.remove(particle);
   }
   
}
