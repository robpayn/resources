package org.payn.resources.particleold.cell;

import java.util.ArrayList;

import org.payn.chsm.State;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteFlow;

import neoch.HolonBoundary;
import neoch.HolonCell;

public class ParticleOneDim extends ParticleConcTracker {

   protected HolonBoundary currentBound;
   protected double endDistance;
   protected double currentDistance;

   public ParticleOneDim(ParticleManager manager, String resourceName) 
   {
      super(manager, resourceName);
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

   @Override
   public void sample() 
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
      value.addValue(
            new ValueDouble(time.n), 
            new ValueDouble(conc)
            );
   }

   protected void move() 
   {
      double velocity = getCurrentVelocity();
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

   private double getCurrentVelocity() 
   {
      State state = currentBound.getState(BehaviorSoluteFlow.NAME_WATER_FLOW);
      if (state == null)
      {
         return Math.abs(((ValueDouble)currentBound.getAdjacentBoundary().getState(BehaviorSoluteFlow.NAME_WATER_FLOW).getValue()).n) /
         ((ValueDouble)currentBound.getAdjacentBoundary().getState(BehaviorSoluteFlow.REQ_STATE_AREA_XSECT).getValue()).n;
      }
      else
      {
         return Math.abs(((ValueDouble)currentBound.getState(BehaviorSoluteFlow.NAME_WATER_FLOW).getValue()).n) /
         ((ValueDouble)currentBound.getState(BehaviorSoluteFlow.REQ_STATE_AREA_XSECT).getValue()).n;
      }
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

   protected boolean isFlowPositive() 
   {
      State state = currentBound.getState(BehaviorSoluteFlow.NAME_WATER_FLOW);
      if (state == null)
      {
         state = currentBound.getAdjacentBoundary().getState(
               BehaviorSoluteFlow.NAME_WATER_FLOW
               );
         return -((ValueDouble)state.getValue()).n > 0;
      }
      else
      {
         return ((ValueDouble)state.getValue()).n > 0;
      }
   }

}
