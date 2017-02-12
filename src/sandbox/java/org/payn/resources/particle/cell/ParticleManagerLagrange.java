package org.payn.resources.particle.cell;

public class ParticleManagerLagrange extends ParticleManager {
   
   @Override
   protected void releaseParticleConditional()
   {
      if (tick.n == 1 || tick.n % releaseInterval.n == 0)
      {
         releaseParticle();
      }
   }

}
