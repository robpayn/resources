package org.payn.resources.water.channel.cell;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.io.initialize.InitialConditionTable;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for channel storage
 * 
 * @author robpayn
 *
 */
public class BehaviorChannelStorage extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceWater.NAME_WATER_VOLUME, WaterVolume.class, WaterVolume.getValueClass());
      addProcessor(ResourceWater.NAME_WATER_HEAD, WaterHead.class, WaterHead.getValueClass());
      addProcessor(ResourceWater.NAME_WATER_DEPTH, Depth.class, Depth.getValueClass());
      addProcessor(ResourceWater.NAME_ACTIVE_CHANNEL_BOTTOM_AREA, BottomArea.class, BottomArea.getValueClass());
      addProcessor(ResourceWater.NAME_WETTED_AREA, WettedArea.class, WettedArea.getValueClass());
      addProcessor(ResourceWater.NAME_WETTED_AREA_CHANGE, WettedAreaChange.class, WettedAreaChange.getValueClass());
      addProcessor(ResourceWater.NAME_WETTED_AREA_MAX, WettedAreaMax.class, WettedAreaMax.getValueClass());
   }

   @Override
   protected void registerStates() 
   {
      registerState(ResourceWater.NAME_COORDINATE_X, ValueDouble.class);
      registerState(ResourceWater.NAME_COORDINATE_Y, ValueDouble.class);
      registerState(ResourceWater.NAME_BED_ELEVATION, ValueDouble.class);
      registerState(ResourceWater.NAME_BANK_ELEVATION, ValueDouble.class);
      registerState(ResourceWater.NAME_LENGTH, ValueDouble.class);
      registerState(ResourceWater.NAME_ACTIVE_CHANNEL_WIDTH_AVERAGE, ValueDouble.class);
      registerState(InitialConditionTable.NAME_INITIAL_CONDITION_PATH, ValueString.class);
      registerState(InitialConditionTable.NAME_INITIAL_CONDITION_DELIMITER, ValueString.class);
   }

}
