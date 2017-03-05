package org.payn.resources.solute.boundary.otis;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior of solute at a flow-controlled boundary
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteFlowBoundOTIS extends BehaviorAbstract {

   @Override
   public void addRequiredStates() 
   {
      addRequiredState(
            ResourceSolute.NAME_WATER_FLOW, 
            ValueDouble.class
            );
      addAbstractRequiredState(
            ResourceSolute.NAME_SOLUTE_CONC, 
            ValueDouble.class
            );
   }

   @Override
   public void addProcessors() 
   {}

}
