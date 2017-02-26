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

   /**
    * Maximum hydraulic radius
    */
   public static final double MAX_HYDRAULIC_RADIUS = 50.0;
   
   /**
    * Maximum velocity
    */
   public static final double MAX_VELOCITY = 50.0;
   
   /**
    * Name of the state for the hydraulic radius of the channel flow
    */
   public static final String NAME_HYDRAULIC_RADIUS = HydraulicRadius.class.getSimpleName();

   /**
    * Name of the state for the wetted with of channel flow
    */
   public static final String NAME_WETTED_WIDTH = WettedWidth.class.getSimpleName();

   /**
    * Name of the state for the fraction of the length between cells
    */
   public static final String NAME_LENGTH_FRACTION = LengthFraction.class.getSimpleName();

   /**
    * Name of the state for the width of the bottom of the channel
    */
   public static final String NAME_BOTTOM_WIDTH = BottomWidth.class.getSimpleName();

   /**
    * Name of the state for the change in the wetted width with depth
    */
   public static final String NAME_WETTED_WIDTH_CHANGE = WettedWidthChange.class.getSimpleName();

   /**
    * Name of optional state for specifying the length within the associated cell
    */
   public static final String NAME_LENGTH_LOC = "LengthLoc";

   /**
    * Name of the optional state for specifying the length within the adjacent cell
    */
   public static final String NAME_LENGTH_ADJ = "LengthAdj";

   /**
    * Name fo the state for the depth of the active channel
    */
   public static final String NAME_ACTIVE_DEPTH = "ActiveDepth";

   /**
    * Name of the state for the bed slope
    */
   public static final String NAME_BED_SLOPE = BedSlope.class.getSimpleName();

   /**
    * Name of the state for hydraulic gradient
    */
   public static final String NAME_HYDRAULIC_GRADIENT = HydraulicGradient.class.getSimpleName();

   /**
    * Name of the state for the link length
    */
   public static final String NAME_LINK_LENGTH = LinkLength.class.getSimpleName();

   @Override
   protected void addProcessors()
   {
      addProcessor(NAME_HYDRAULIC_RADIUS, HydraulicRadius.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_DEPTH, Depth.class, ValueDouble.class);
      addProcessor(NAME_WETTED_WIDTH, WettedWidth.class, ValueDouble.class);
      addProcessor(NAME_LENGTH_FRACTION, LengthFraction.class, ValueDouble.class);
      addProcessor(ResourceWater.NAME_BED_ELEVATION, BedElevation.class, ValueDouble.class);
      addProcessor(NAME_BOTTOM_WIDTH, BottomWidth.class, ValueDouble.class);
      addProcessor(NAME_WETTED_WIDTH_CHANGE, WettedWidthChange.class, ValueDouble.class);
      addProcessor(NAME_BED_SLOPE, BedSlope.class, ValueDouble.class);
      addProcessor(NAME_HYDRAULIC_GRADIENT, HydraulicGradient.class, ValueDouble.class);
      addProcessor(NAME_LINK_LENGTH, LinkLength.class, ValueDouble.class);
   }
   
   /**
    * Add the base required states
    */
   protected void addRequiredStatesChannelFlow()
   {
      addRequiredState(ResourceWater.NAME_ACTIVE_WIDTH_AVG, ValueDouble.class);
      addRequiredState(NAME_LENGTH_LOC, ValueDouble.class);
      addRequiredState(NAME_LENGTH_ADJ, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_BANK_SLOPE, ValueDouble.class);
      addRequiredState(NAME_ACTIVE_DEPTH, ValueDouble.class);
   }

}
