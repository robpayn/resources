package org.payn.resources.water.channel.boundary;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.io.initialize.InitialConditionTable;
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
      addProcessor(ResourceWater.NAME_HYDRAULIC_RADIUS, HydraulicRadius.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_WATER_DEPTH, Depth.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_WETTED_WIDTH, WettedWidth.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_LENGTH_FRACTION, LengthFraction.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_BED_ELEVATION, BedElevation.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_ACTIVE_CHANNEL_BOTTOM_WIDTH, BottomWidth.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_WETTED_WIDTH_CHANGE, WettedWidthChange.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_BED_SLOPE, BedSlope.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_HYDRAULIC_GRADIENT, HydraulicGradient.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_LENGTH_BOUND, LengthBound.class, ValueDouble.class);
   }
   
   /**
    * Add the base required states
    */
   protected void addRequiredStatesChannelFlow()
   {
      registerState(ResourceWater.NAME_ACTIVE_CHANNEL_WIDTH_AVERAGE, ValueDouble.class);
      registerState(ResourceWater.NAME_LENGTH_LOCAL, ValueDouble.class);
      registerState(ResourceWater.NAME_LENGTH_ADJACENT, ValueDouble.class);
      registerState(ResourceWater.NAME_BANK_SLOPE, ValueDouble.class);
      registerState(ResourceWater.NAME_ACTIVE_CHANNEL_DEPTH, ValueDouble.class);
      registerState(InitialConditionTable.NAME_INITIAL_CONDITION_PATH, ValueString.class);
      registerState(InitialConditionTable.NAME_INITIAL_CONDITION_DELIMITER, ValueString.class);
   }

}
