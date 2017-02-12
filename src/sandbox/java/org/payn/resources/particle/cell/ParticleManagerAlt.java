package org.payn.resources.particle.cell;

import org.payn.chsm.values.ValueLong;

public class ParticleManagerAlt extends ParticleManager {

   protected long releaseCount;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      super.setUpdateDependencies();
      releaseCount = ((ValueLong)createDependency(
            BehaviorConcTrackerAlt.REQ_STATE_RELEASE_COUNT
            ).getValue()).n;
   }
   
   @Override
   protected void releaseParticleConditional() throws Exception 
   {
      if (tick.n == releaseInterval.n)
      {
         for(long count = 1; count <= releaseCount; count++)
         {
            releaseParticle();
         }
      }
   }

}
