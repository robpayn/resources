package org.payn.resources.water.channel.boundary.dynamicwave.downstream;

import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.neoch.BehaviorMatrix;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for water at the downstream end of a dynamic wave channel
 * 
 * @author robpayn
 *
 */
public class BehaviorDynamicWaveDownstream extends BehaviorMatrix {

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceWater.NAME_WATER_FLOW, WaterFlow.class, WaterFlow.getValueClass());
   }

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(ResourceWater.NAME_UPSTREAM_BOUNDARY_NAME, ValueString.class);
      addRequiredState(ResourceWater.NAME_CHEZEY, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_BED_SLOPE, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_CHEZEY_EXPONENT_VELOCITY, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_CHEZEY_EXPONENT_RADIUS, ValueDouble.class);
   }

}
