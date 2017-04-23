package org.payn.resources.water.channel.boundary.dynamicwave.downstream;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for water at the downstream end of a dynamic wave channel
 * 
 * @author robpayn
 *
 */
public class BehaviorDynamicWaveDownstream extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceWater.DEFAULT_NAME_FLOW, WaterFlow.class, WaterFlow.getValueClass());
   }

   @Override
   protected void registerStates() 
   {
      registerState(ResourceWater.DEFAULT_NAME_UPSTREAM_BOUNDARY_NAME, ValueString.class);
      registerState(ResourceWater.DEFAULT_NAME_CHEZEY, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_BED_SLOPE, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_CHEZEY_EXP_VELOCITY, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_CHEZEY_EXP_RADIUS, ValueDouble.class);
   }

}
