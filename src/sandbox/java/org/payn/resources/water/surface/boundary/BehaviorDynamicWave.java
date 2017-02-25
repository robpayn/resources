package org.payn.resources.water.surface.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;

/**
 * Behavior of surface water according to the dynamic wave equations
 * 
 * @author v78h241
 *
 */
public class BehaviorDynamicWave extends BehaviorMatrix {

   /**
    * Maximum hydraulic radius
    */
   public static final double MAX_HYDRAULIC_RADIUS = 50.0;
   
   /**
    * Name of the state for the hydraulic radius of the channel flow
    */
   public static final String NAME_HYDRAULIC_RADIUS = HydraulicRadius.class.getSimpleName();

   /**
    * Name of the state for the depth of the channel flow
    */
   public static final String NAME_DEPTH = Depth.class.getSimpleName();

   /**
    * Name of the state for cross-sectional area of channel flow
    */
   public static final String NAME_XSECT_AREA = XSectAreaCurrent.class.getSimpleName();

   /**
    * Name of the state for the wetted with of channel flow
    */
   public static final String NAME_WETTED_WIDTH = WettedWidth.class.getSimpleName();

   /**
    * Name of the state for the fraction of the length between cells
    */
   public static final String NAME_LENGTH_FRACTION = LengthFraction.class.getSimpleName();

   /**
    * Name of the state for the bed elevation
    */
   public static final String NAME_BED_ELEVATION = BedElevation.class.getSimpleName();

   /**
    * Name of the state for the previous cross-sectional area
    */
   public static final String NAME_XSECT_AREA_PREV = XSectAreaPrevious.class.getSimpleName();

   /**
    * Name of the state for the width of the bottom of the channel
    */
   public static final String NAME_BOTTOM_WIDTH = BottomWidth.class.getSimpleName();

   /**
    * Name of the state for the change in the wetted width with depth
    */
   public static final String NAME_WETTED_WIDTH_CHANGE = WettedWidthChange.class.getSimpleName();

   /**
    * Name of the state for the average width of the active channel
    */
   public static final String REQ_STATE_ACTIVE_WIDTH_AVG = "ActiveWidthAverage";

   /**
    * Name of optional state for specifying the length within the associated cell
    */
   public static final String REQ_STATE_LENGTH_LOC = "LengthLoc";

   /**
    * Name of the optional state for specifying the length within the adjacent cell
    */
   public static final String REQ_STATE_LENGTH_ADJ = "LengthAdj";

   /**
    * Name of the optional state for bank slope
    */
   public static final String REQ_STATE_BANK_SLOPE = "BankSlope";

   /**
    * Name fo the state for the depth of the active channel
    */
   public static final String REQ_STATE_ACTIVE_DEPTH = "ActiveDepth";

   @Override
   protected void addProcessors() 
   {
      addProcessor(NAME_HYDRAULIC_RADIUS, HydraulicRadius.class, ValueDouble.class);
      addProcessor(NAME_DEPTH, Depth.class, ValueDouble.class);
      addProcessor(NAME_XSECT_AREA, XSectAreaCurrent.class, ValueDouble.class);
      addProcessor(NAME_WETTED_WIDTH, WettedWidth.class, ValueDouble.class);
      addProcessor(NAME_LENGTH_FRACTION, LengthFraction.class, ValueDouble.class);
      addProcessor(NAME_BED_ELEVATION, BedElevation.class, ValueDouble.class);
      addProcessor(NAME_XSECT_AREA_PREV, BedElevation.class, ValueDouble.class);
      addProcessor(NAME_BOTTOM_WIDTH, BedElevation.class, ValueDouble.class);
      addProcessor(NAME_WETTED_WIDTH_CHANGE, BedElevation.class, ValueDouble.class);
   }

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(REQ_STATE_ACTIVE_WIDTH_AVG, ValueDouble.class);
      addRequiredState(REQ_STATE_LENGTH_LOC, ValueDouble.class);
      addRequiredState(REQ_STATE_LENGTH_ADJ, ValueDouble.class);
      addRequiredState(REQ_STATE_BANK_SLOPE, ValueDouble.class);
      addRequiredState(REQ_STATE_ACTIVE_DEPTH, ValueDouble.class);
   }

}
