package org.payn.resources.particleold;

import org.payn.chsm.Resource;
import org.payn.resources.particle.boundary.BehaviorParticleMove;
import org.payn.resources.particle.cell.BehaviorConcTrackerAlt;
import org.payn.resources.particle.cell.BehaviorConcTrackerLagrange;
import org.payn.resources.particle.cell.BehaviorConcTrackerVel;
import org.payn.resources.particle.cell.BehaviorParticleStorage;

public class ResourceParticle extends Resource {
   
   public static final String BEHAVIOR_CONC_TRACKER = "concTrackerLagrange";
   public static final String BEHAVIOR_CONC_TRACKER_ALT = "concTrackerAlt";
   public static final String BEHAVIOR_CONC_TRACKER_VEL = "concTrackerVel";
   public static final String BEHAVIOR_PARTICLE_STORAGE = "ParticleStorage";
   public static final String BEHAVIOR_PARTICLE_MOVEMENT = "ParticleMove";
   public static final String NAME_MANAGER = "Manager";
   public static final String NAME_MEAN = "Mean";
   public static final String NAME_TIME_SERIES = "TimeSeries";
   public static final String NAME_BIN = "ParticleBin";
   public static final String NAME_LOAD = "ParticleLoad";

   @Override
   protected void addBehaviors() 
   {
      addBehavior(BEHAVIOR_CONC_TRACKER, BehaviorConcTrackerLagrange.class.getCanonicalName());
      addBehavior(BEHAVIOR_CONC_TRACKER_ALT, BehaviorConcTrackerAlt.class.getCanonicalName());
      addBehavior(BEHAVIOR_CONC_TRACKER_VEL, BehaviorConcTrackerVel.class.getCanonicalName());
      addBehavior(BEHAVIOR_PARTICLE_STORAGE, BehaviorParticleStorage.class.getCanonicalName());
      addBehavior(BEHAVIOR_PARTICLE_MOVEMENT, BehaviorParticleMove.class.getCanonicalName());
   }

}
