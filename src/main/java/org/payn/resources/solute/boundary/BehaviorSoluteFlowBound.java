package org.payn.resources.solute.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteFlowBound;

/**
 * Behavior of solute at a flow-controlled boundary
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteFlowBound extends BehaviorMatrix {

   /**
    * Name for required state of water flow
    */
   public static String REQ_STATE_FLOW = "WaterFlow";
   
   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(REQ_STATE_FLOW, ValueDouble.class);
      addRequiredState(resource.getName() + ResourceSolute.NAME_SOLUTE_CONC, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceSolute.NAME_SOLUTE_LOAD, SoluteFlowBound.class, SoluteFlowBound.getValueClass());
   }

}
