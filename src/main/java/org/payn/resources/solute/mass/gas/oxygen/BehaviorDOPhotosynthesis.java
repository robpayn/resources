package org.payn.resources.solute.mass.gas.oxygen;

import org.payn.chsm.BehaviorAbstract;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior controlling the effects of photosynthesis on dissolved oxygen
 * 
 * @author v78h241
 *
 */
public class BehaviorDOPhotosynthesis extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(
            ResourceSolute.DEFAULT_NAME_DO_PHOTOSYNTHESIS, 
            DOPhotosynthesis.class, 
            DOPhotosynthesis.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {}

}
