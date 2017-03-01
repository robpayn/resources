package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Dynamic wave behavior based on Wiele power relationship between depth and friction
 * 
 * @author v78h241
 *
 */
public class BehaviorDynamicWaveWiele extends BehaviorDynamicWave {

   @Override
   protected void addProcessors() 
   {
      super.addProcessors();
      addProcessor(ResourceWater.NAME_CHEZEY, Chezey.class, Chezey.getValueClass());
      addProcessor(ResourceWater.NAME_FRICTION_FACTOR, Friction.class, Friction.getValueClass());
   }

   @Override
   protected void addRequiredStates() 
   {
      super.addRequiredStatesChannelFlow();
      addRequiredState(ResourceWater.NAME_WIELE_MODEL_INTERCEPT, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_WIELE_MODEL_SLOPE, ValueDouble.class);
   }

}
