package org.payn.resources.solute.boundary.otis;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior for solutes in boundaries of a one-dimensional
 * model based on concentration calculations
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteFlowOTIS extends BehaviorAbstract {

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(ResourceSolute.NAME_WATER_FLOW, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {}

}
