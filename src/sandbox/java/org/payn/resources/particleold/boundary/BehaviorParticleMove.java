package org.payn.resources.particleold.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueParticleList;
import org.payn.resources.particle.ResourceParticle;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteFlow;

import neoch.behaviors.BehaviorMatrix;

public class BehaviorParticleMove extends BehaviorMatrix {

   @Override
   protected void addRequiredStates() 
   {
      registerState(BehaviorSoluteFlow.NAME_WATER_FLOW, ValueDouble.class);
      
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceParticle.NAME_LOAD, ParticleLoad.class, ValueParticleList.class);
   }

}
