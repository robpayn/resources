package org.payn.resources.water.channel.cell;

import org.payn.chsm.io.InitialConditionTable;
import org.payn.chsm.resources.BehaviorAbstract;
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
      addProcessor(ResourceWater.DEFAULT_NAME_VOLUME, WaterVolume.class, WaterVolume.getValueClass());
      addProcessor(ResourceWater.DEFAULT_NAME_HEAD, WaterHead.class, WaterHead.getValueClass());
      addProcessor(ResourceWater.DEFAULT_NAME_DEPTH, Depth.class, Depth.getValueClass());
      addProcessor(ResourceWater.DEFAULT_NAME_ACTIVE_CHANNEL_BOTTOM_AREA, BottomArea.class, BottomArea.getValueClass());
      addProcessor(ResourceWater.DEFAULT_NAME_WETTED_AREA, WettedArea.class, WettedArea.getValueClass());
      addProcessor(ResourceWater.DEFAULT_NAME_WETTED_AREA_CHANGE, WettedAreaChange.class, WettedAreaChange.getValueClass());
      addProcessor(ResourceWater.DEFAULT_NAME_WETTED_AREA_MAX, WettedAreaMax.class, WettedAreaMax.getValueClass());
   }

   @Override
   protected void registerStates() 
   {
      registerState(ResourceWater.DEFAULT_NAME_COORD_X, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_COORD_Y, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_BED_ELEV, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_BANK_ELEV, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_LENGTH, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_ACTIVE_CHANNEL_WIDTH_AVG, ValueDouble.class);
      registerState(InitialConditionTable.NAME_INITIAL_CONDITION_PATH, ValueString.class);
      registerState(InitialConditionTable.NAME_INITIAL_CONDITION_DELIMITER, ValueString.class);
   }

}
