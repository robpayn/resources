package org.payn.resources.solute.boundary;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteFlowBound;

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
      registerState(ResourceSolute.NAME_WATER_FLOW, ValueDouble.class);
      registerStateAbstract(
            ResourceSolute.NAME_SOLUTE_CONC, 
            ValueDouble.class
            );
   }

   @Override
   public void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_LOAD, 
            SoluteFlowBound.class, 
            SoluteFlowBound.getValueClass()
            );
   }

}
