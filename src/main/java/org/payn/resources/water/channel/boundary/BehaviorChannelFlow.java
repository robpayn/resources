package org.payn.resources.water.channel.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.symmetric.symmdouble.BehaviorSymmetricDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for channel flow
 * 
 * @author robpayn
 *
 */
public abstract class BehaviorChannelFlow extends BehaviorSymmetricDouble {

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
      addRequiredState(ResourceWater.NAME_ACTIVE_CHANNEL_WIDTH_AVERAGE, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_LENGTH_LOCAL, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_LENGTH_ADJACENT, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_BANK_SLOPE, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_ACTIVE_CHANNEL_DEPTH, ValueDouble.class);
   }

}
