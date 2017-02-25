package org.payn.resources.old.water.liquid.boundary.channelflow;

import org.payn.resources.old.water.WaterStorage;

import neoch.HolonBoundary;
import neoch.behaviors.symmetric.BehaviorNegative;
import neoch.behaviors.symmetric.BehaviorSymmetric;
import neoch.behaviors.symmetric.symmdouble.BehaviorNegativeDouble;

public class BehaviorChannelFlow extends BehaviorSymmetric {

   @Override
   public BehaviorNegative createNegativeBehavior(HolonBoundary boundary) 
   {
      return new BehaviorNegativeDouble(this, boundary);
   }

   @Override
   protected void addRequiredStates()
   {
   }

   @Override
   protected void addProcessors()
   {
      addProcessor(WaterStorage.class.getSimpleName(), WaterFlowChannel.class);
      addProcessor(Blah.class.getSimpleName(), Blah.class);
   }

}
