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
      addProcessor(ResourceWater.NAME_WATER_FLOW, WaterFlow.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_WETTED_XSECT_AREA, XSectAreaCurrent.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_WETTER_XSECT_AREA_PREV, XSectAreaPrevious.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_WATER_VELOCITY, Velocity.class, ValueDouble.class);
   }

   @Override
   protected void addRequiredStates() 
   {
      addRequiredStatesChannelFlow();
      addRequiredState(ResourceWater.NAME_CHEZEY, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_CHEZEY_EXPONENT_VELOCITY, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_CHEZEY_EXPONENT_RADIUS, ValueDouble.class);
   }
   
}
