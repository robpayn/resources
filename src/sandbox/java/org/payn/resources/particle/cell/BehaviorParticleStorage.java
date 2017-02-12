package org.payn.resources.particle.cell;

import org.payn.chsm.values.ValueParticleList;
import org.payn.resources.particle.ResourceParticle;

import neoch.behaviors.BehaviorMatrix;

public class BehaviorParticleStorage extends BehaviorMatrix {

   @Override
   protected void addRequiredStates() 
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void addProcessors() 
   {
      this.addProcessor(ResourceParticle.NAME_BIN, ParticleBin.class, ValueParticleList.class);
   }

}
