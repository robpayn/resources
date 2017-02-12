package org.payn.resources.particle.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueParticleList;
import org.payn.resources.particle.ResourceParticle;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;

import neoch.behaviors.BehaviorMatrix;

public class BehaviorParticleMove extends BehaviorMatrix {

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_FLOW, ValueDouble.class);
      
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceParticle.NAME_LOAD, ParticleLoad.class, ValueParticleList.class);
   }

}
