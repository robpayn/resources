package org.payn.resources.particle.cell;

import java.io.IOException;
import java.util.ArrayList;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;

import neoch.HolonBoundary;
import neoch.HolonCell;

public class ParticleVel extends ParticleConcTracker {

   private double velocity;

   public ParticleVel(ParticleManagerVel particleManager, String resourceName,
         double velocity) 
   {
      super(particleManager, resourceName);
      this.velocity = velocity;
   }

   @Override
   public void initializeLocation(HolonCell releaseCell, HolonCell endCell) 
   {
      this.currentCell = releaseCell;
      this.endCell = endCell;
      ArrayList<HolonBoundary> bounds = currentCell.getBoundaries(resourceName + "." + ResourceSolute.BEHAVIOR_FLOW);
      if (bounds.size() < 2)
      {
         bounds = currentCell.getBoundaries(resourceName + "." + ResourceSolute.BEHAVIOR_CONCBOUND_INJECT);
         if (bounds.size() == 0)
         {
            bounds = currentCell.getBoundaries(resourceName + "." + ResourceSolute.BEHAVIOR_CONCBOUND);
         }
         currentBound = bounds.get(0);
      }
      else
      {
         for (HolonBoundary bound: bounds)
         {
            currentBound = bound;
            if (isFlowPositive())
            {
               break;
            }
         }
      }
      
      endDistance = ((ValueDouble)currentBound.getState(BehaviorSoluteFlow.REQ_STATE_LENGTH).getValue()).n / 2;
      currentDistance = 0;
      
   }

   @Override
   protected void move() throws IOException 
   {
      currentDistance += velocity * timeStep.n;
      if (currentDistance > endDistance)
      {
         double extraDistance = currentDistance - endDistance;
         if (isFlowPositive())
         {
            if (currentCell.getName().equals(endCell.getName()))
            {
               currentDistance = endDistance;
               particleManager.reportFinishedParticle(this);
            }
            else
            {
               ArrayList<HolonBoundary> bounds = currentCell.getBoundaries(resourceName + "." + ResourceSolute.BEHAVIOR_FLOW);
               for (HolonBoundary bound: bounds)
               {
                  currentBound = bound;
                  if (!isFlowPositive())
                  {
                     break;
                  }
               }
               endDistance = ((ValueDouble)currentBound.getState(BehaviorSoluteFlow.REQ_STATE_LENGTH).getValue()).n / 2;
               currentDistance = extraDistance;
            }
         } 
         else
         {
            currentBound = currentBound.getAdjacentBoundary();
            currentCell = currentBound.getCell();
            currentDistance = extraDistance;
         }
      }   
   }

}
