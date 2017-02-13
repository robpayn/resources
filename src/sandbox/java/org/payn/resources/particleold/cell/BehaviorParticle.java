package org.payn.resources.particleold.cell;

import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueString;
import org.payn.resources.particle.Particle;
import org.payn.resources.particle.ResourceParticle;

import neoch.behaviors.BehaviorMatrix;

public abstract class BehaviorParticle extends BehaviorMatrix {

   public static final String REQ_STATE_RELEASE_NAME = "releaseCellName";
   public static final String REQ_STATE_END_NAME = "endCellName";
   public static final String REQ_STATE_CURRENCY = "currency";
   public static final String REQ_STATE_INTERVAL_RECORD = "recordInterval";
   public static final String REQ_STATE_INTERVAL_RELEASE = "releaseInterval";


   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(REQ_STATE_RELEASE_NAME, ValueString.class);
      addRequiredState(REQ_STATE_END_NAME, ValueString.class);
      addRequiredState(REQ_STATE_CURRENCY, ValueString.class);
      addRequiredState(REQ_STATE_INTERVAL_RECORD, ValueLong.class);
      addRequiredState(REQ_STATE_INTERVAL_RELEASE, ValueLong.class);
   }
   
   public abstract Particle createParticle(ParticleManager particleManager,
         String currencyName);
   
}
