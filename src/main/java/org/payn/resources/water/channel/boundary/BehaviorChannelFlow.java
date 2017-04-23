package org.payn.resources.water.channel.boundary;

import org.payn.chsm.io.InitialConditionTable;
import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for channel flow
 * 
 * @author robpayn
 *
 */
public abstract class BehaviorChannelFlow extends BehaviorAbstract {

   @Override
   protected void addProcessors()
   {
      addProcessor(ResourceWater.DEFAULT_NAME_HYDR_RADIUS, HydraulicRadius.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_DEPTH, Depth.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_WETTED_WIDTH, WettedWidth.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFUALT_NAME_LENGTH_FRACTION, LengthFraction.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_BED_ELEV, BedElevation.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_ACTIVE_CHANNEL_BOTTOM_WIDTH, BottomWidth.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_WETTED_WIDTH_CHANGE, WettedWidthChange.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_BED_SLOPE, BedSlope.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_HYDR_GRAD, HydraulicGradient.class, ValueDouble.class);
      addProcessor(ResourceWater.DEFAULT_NAME_LENGTH_BOUND, LengthBound.class, ValueDouble.class);
   }
   
   /**
    * Add the base required states
    */
   protected void addRequiredStatesChannelFlow()
   {
      registerState(ResourceWater.DEFAULT_NAME_ACTIVE_CHANNEL_WIDTH_AVG, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_LENGTH_LOCAL, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_LENGTH_ADJACENT, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_BANK_SLOPE, ValueDouble.class);
      registerState(ResourceWater.DEFAULT_NAME_ACTIVE_CHANNEL_DEPTH, ValueDouble.class);
      registerState(InitialConditionTable.NAME_INITIAL_CONDITION_PATH, ValueString.class);
      registerState(InitialConditionTable.NAME_INITIAL_CONDITION_DELIMITER, ValueString.class);
   }

}
