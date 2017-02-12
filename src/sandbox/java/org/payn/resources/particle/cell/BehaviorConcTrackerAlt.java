package org.payn.resources.particle.cell;

import org.payn.chsm.values.ValueLong;
import org.payn.resources.particle.ResourceParticle;

public class BehaviorConcTrackerAlt extends BehaviorConcTracker {

   public static final String REQ_STATE_RELEASE_COUNT = "releaseCount";

   @Override
   protected void addRequiredStates() 
   {
      super.addRequiredStates();
      addRequiredState(REQ_STATE_RELEASE_COUNT, ValueLong.class);
   }

   @Override
   public ParticleConcTracker createParticle(ParticleManager particleManager,
         String currencyName) 
   {
      return new ParticleConcTracker(particleManager, currencyName);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceParticle.NAME_MANAGER, ParticleManagerAlt.class, ParticleManager.getValueClass());
   }

}
