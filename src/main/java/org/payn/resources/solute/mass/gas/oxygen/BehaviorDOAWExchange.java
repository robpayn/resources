package org.payn.resources.solute.mass.gas.oxygen;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior for dissolved oxygen in river water
 * 
 * @author v78h241
 *
 */
public class BehaviorDOAWExchange extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(
            ResourceSolute.DEFAULT_NAME_DO_AW_EXCH, 
            DOAWExchange.class, 
            DOAWExchange.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {}

}
