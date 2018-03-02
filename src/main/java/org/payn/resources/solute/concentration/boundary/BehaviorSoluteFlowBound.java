package org.payn.resources.solute.concentration.boundary;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior of solute at a flow-controlled boundary
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteFlowBound extends BehaviorAbstract {

   @Override
   public void registerStates() 
   {
      registerState(
            ResourceSolute.NAME_WATER_FLOW, 
            ValueDouble.class
            );
   }

   @Override
   public void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_CONC,
            SoluteConcDownstream.class,
            ValueDouble.class
            );
   }

}
