package org.payn.resources.particle.cell;

import org.payn.resources.particle.ResourceParticle;

public class BehaviorConcTrackerVel extends BehaviorConcTrackerAlt {

   @Override
   public ParticleConcTracker createParticle(ParticleManager particleManager,
         String currencyName) 
   {
      throw new UnsupportedOperationException();
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceParticle.NAME_MANAGER, ParticleManagerVel.class, ParticleManager.getValueClass());
   }


}
