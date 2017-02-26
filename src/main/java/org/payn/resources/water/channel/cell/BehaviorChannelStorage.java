package org.payn.resources.water.channel.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for channel storage
 * 
 * @author robpayn
 *
 */
public class BehaviorChannelStorage extends BehaviorMatrix {

   /**
    * Name of the state for bank elevation
    */
   public static final String NAME_BANK_ELEVATION = "BankElevation";

   /**
    * Name of the state for channel bottom area
    */
   public static final String NAME_BOTTOM_AREA = BottomArea.class.getSimpleName();

   /**
    * Name of the state for change in wetted area with depth
    */
   public static final String NAME_WETTED_AREA_CHANGE = WettedAreaChange.class.getSimpleName();

   /**
    * Name of the state for the maximum wetted area
    */
   public static final String NAME_WETTED_AREA_MAX = WettedAreaMax.class.getSimpleName();

   /**
    * Name of the state for length
    */
   public static final String NAME_LENGTH = "Length";

   /**
    * Name of the state for the wetted area
    */
   private static final String NAME_WETTED_AREA = WettedArea.class.getSimpleName();

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceWater.NAME_WATER_VOLUME, WaterVolume.class, WaterVolume.getValueClass());
      addProcessor(ResourceWater.NAME_WATER_HEAD, WaterHead.class, WaterHead.getValueClass());
      addProcessor(ResourceWater.NAME_DEPTH, Depth.class, Depth.getValueClass());
      addProcessor(NAME_BOTTOM_AREA, BottomArea.class, BottomArea.getValueClass());
      addProcessor(NAME_WETTED_AREA, WettedArea.class, WettedArea.getValueClass());
      addProcessor(NAME_WETTED_AREA_CHANGE, WettedAreaChange.class, WettedAreaChange.getValueClass());
      addProcessor(NAME_WETTED_AREA_MAX, WettedAreaMax.class, WettedAreaMax.getValueClass());
   }

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(ResourceWater.NAME_X, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_Y, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_BED_ELEVATION, ValueDouble.class);
      addRequiredState(NAME_BANK_ELEVATION, ValueDouble.class);
      addRequiredState(NAME_LENGTH, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_ACTIVE_WIDTH_AVG, ValueDouble.class);
   }

}
