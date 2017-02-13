package org.payn.resources.particleold.cell;

import java.util.ArrayList;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;

import neoch.HolonBoundary;

public class ParticleOneDimVel extends ParticleOneDim {

   private double velocity;

   public ParticleOneDimVel(ParticleManager manager, String resourceName, double velocity) 
   {
      super(manager, resourceName);
      this.velocity = velocity;
   }
   
   @Override
   protected void move() 
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
               manager.reportFinishedParticle(this);
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
