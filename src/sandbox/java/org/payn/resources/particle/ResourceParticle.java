package org.payn.resources.particle;

import org.payn.chsm.resources.Resource;
import org.payn.resources.particle.cell.BehaviorConcTrackerVel;

public class ResourceParticle extends Resource {

   public static final String BEHAVIOR_CONC_TRACKER_VEL = "concTrackerVel";
   public static final String NAME_MANAGER = "Manager";

   @Override
   protected void addBehaviors() 
   {
      addBehavior(
            BEHAVIOR_CONC_TRACKER_VEL, 
            BehaviorConcTrackerVel.class.getCanonicalName()
            );
   }

}
