package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Dynamic wave behavior based on Wiele power relationship between depth and friction
 * 
 * @author v78h241
 *
 */
public class BehaviorWieleFriction extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceWater.DEFAULT_NAME_CHEZEY, Chezey.class, Chezey.getValueClass());
      addProcessor(ResourceWater.DEFAULT_NAME_FRICTION_FACTOR, Friction.class, Friction.getValueClass());
   }

   @Override
   protected void registerStates() 
   {
      registerState(ResourceWater.DEFAULT_NAME_WIELE_MODEL_INTERCEPT, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_WIELE_MODEL_SLOPE, ValueDouble.class);
   }

}
