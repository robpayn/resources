package org.payn.resources.solute.mass.gas.oxygen;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior controlling the effect of aerobic respiration on dissolved oxygen
 * over a stream reach
 * 
 * @author v78h241
 *
 */
public class BehaviorDORespirationReach extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {}

   @Override
   protected void registerStates() 
   {
      registerState(
            ResourceSolute.DEFAULT_NAME_DO_RESPIRATION,
            ValueDouble.class
            );
   }

}
