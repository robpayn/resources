package org.payn.resources.solute.mass.gas.oxygen;

import org.payn.chsm.BehaviorAbstract;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior controlling the effect of aerobic respiration on dissolved oxygen
 * 
 * @author v78h241
 *
 */
public class BehaviorDORespiration extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(
            ResourceSolute.DEFAULT_NAME_DO_RESPIRATION, 
            DORespiration.class, 
            DORespiration.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {}

}
