package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.BehaviorChannelFlow;

/**
 * Behavior of surface water according to the dynamic wave equations
 * 
 * @author v78h241
 *
 */
public class BehaviorDynamicWave extends BehaviorChannelFlow {

   @Override
   protected void addProcessors() 
   {
      super.addProcessors();
      addProcessor(ResourceWater.DEFAULT_NAME_FLOW, WaterFlow.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_WETTED_XSECT_AREA, XSectAreaCurrent.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_WETTER_XSECT_AREA_PREV, XSectAreaPrevious.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_VELOCITY, Velocity.class, ValueDouble.class);
   }

   @Override
   protected void registerStates() 
   {
      addRequiredStatesChannelFlow();
      registerState(ResourceWater.DEFAULT_NAME_CHEZEY, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_CHEZEY_EXP_VELOCITY, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_CHEZEY_EXP_RADIUS, ValueDouble.class);
   }
   
}
