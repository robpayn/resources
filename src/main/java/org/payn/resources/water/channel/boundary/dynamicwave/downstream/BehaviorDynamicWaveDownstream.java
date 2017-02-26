package org.payn.resources.water.channel.boundary.dynamicwave.downstream;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.BehaviorChannelFlow;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;

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
      addRequiredState(BehaviorDynamicWave.NAME_CHEZEY, ValueDouble.class);
      addRequiredState(BehaviorChannelFlow.NAME_BED_SLOPE, ValueDouble.class);
      addRequiredState(BehaviorDynamicWave.REQ_STATE_VELOCITY_EXP, ValueDouble.class);
      addRequiredState(BehaviorDynamicWave.REQ_STATE_RADIUS_EXP, ValueDouble.class);
   }

}
