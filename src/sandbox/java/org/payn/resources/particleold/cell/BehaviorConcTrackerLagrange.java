package org.payn.resources.particleold.cell;

import org.payn.chsm.values.ValueLong;
import org.payn.resources.particle.ResourceParticle;

public class BehaviorConcTrackerLagrange extends BehaviorConcTracker {

   @Override
   public ParticleOneDim createParticle(ParticleManager particleManager,
         String currencyName) 
   {
      return new ParticleOneDim(particleManager, currencyName);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceParticle.NAME_MANAGER, ParticleManagerLagrange.class, ParticleManager.getValueClass());
   }

}
