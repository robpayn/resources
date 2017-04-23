package org.payn.resources.water;

import org.payn.chsm.Holon;
import org.payn.chsm.processors.Processor;
import org.payn.chsm.resources.ResourceAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.channel.boundary.BedElevation;
import org.payn.resources.water.channel.boundary.BedSlope;
import org.payn.resources.water.channel.boundary.BottomWidth;
import org.payn.resources.water.channel.boundary.Depth;
import org.payn.resources.water.channel.boundary.HydraulicGradient;
import org.payn.resources.water.channel.boundary.HydraulicRadius;
import org.payn.resources.water.channel.boundary.LengthFraction;
import org.payn.resources.water.channel.boundary.LengthBound;
import org.payn.resources.water.channel.boundary.WettedWidth;
import org.payn.resources.water.channel.boundary.WettedWidthChange;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorWieleFriction;
import org.payn.resources.water.channel.boundary.dynamicwave.Chezey;
import org.payn.resources.water.channel.boundary.dynamicwave.Friction;
import org.payn.resources.water.channel.boundary.dynamicwave.Velocity;
import org.payn.resources.water.channel.boundary.dynamicwave.WaterFlow;
import org.payn.resources.water.channel.boundary.dynamicwave.XSectAreaCurrent;
import org.payn.resources.water.channel.boundary.dynamicwave.XSectAreaPrevious;
import org.payn.resources.water.channel.boundary.dynamicwave.downstream.BehaviorDynamicWaveDownstream;
import org.payn.resources.water.channel.boundary.flowinterpolate.BehaviorFlowInterpolate;
import org.payn.resources.water.channel.cell.BehaviorChannelStorage;
import org.payn.resources.water.channel.cell.BottomArea;
import org.payn.resources.water.channel.cell.WaterHead;
import org.payn.resources.water.channel.cell.WaterVolume;
import org.payn.resources.water.channel.cell.WettedArea;
import org.payn.resources.water.channel.cell.WettedAreaChange;
import org.payn.resources.water.channel.cell.WettedAreaMax;
import org.payn.resources.water.channel.temperature.BehaviorReachAvgTemperature;
import org.payn.resources.water.channel.temperature.BehaviorReachAvgTemperatureBound;

/**
 * Controls behaviors for the water currency
 * 
 * @author robpayn
 *
 */
public class ResourceWater extends ResourceAbstract {
   
   /**
    * Acceleration of gravity on Earth
    */
   public static final double CONSTANT_GRAVITY_ACC = 9.8067;

   /**
    * Maximum hydraulic radius
    */
   public static final double CONSTANT_MAX_HYDRAULIC_RADIUS = 50.0;
   
   /**
    * Maximum velocity
    */
   public static final double CONSTANT_MAX_VELOCITY = 50.0;
   
   /**
    * Name of behavior for the channel storage
    */
   public static final String BEHAVIOR_CHANNEL_STORAGE = "channel";

   /**
    * Name of behavior for dynamic wave routing
    */
   public static final String BEHAVIOR_DYNAMIC_WAVE = "dynamicwave";

   /**
    * Name of behavior for dynamic wave routing with the Wiele model for variable friction
    */
   public static final String BEHAVIOR_WIELE_FRICTION = "wielefriction";

   /**
    * Name of the behavior for the downstream boundary condition of a dynamic wave channel
    */
   public static final String BEHAVIOR_DYNAMIC_WAVE_DOWNSTREAM = "dynamicwavedownstream";

   /**
    * Behavior flow flow interpolation at boundary
    */
   public static final String BEHAVIOR_FLOW_INTERPOLATE = "flowinterpolate";

   /**
    * Behavior for calculating reach average temperature
    */
   public static final String BEHAVIOR_REACH_AVG_TEMP_BOUND = "reachavgtemperaturebound";
   
   /**
    * Behavior for translating reach average temperature to cells
    */
   public static final String BEHAVIOR_REACH_AVG_TEMP = "reachavgtemperature";

   /**
    * Name of state for water volume
    */
   public static final String DEFAULT_NAME_VOLUME = WaterVolume.class.getSimpleName();

   /**
    * Name of state for water head
    */
   public static final String DEFAULT_NAME_HEAD = WaterHead.class.getSimpleName();

   /**
    * Name of the state for water flow
    */
   public static final String DEFAULT_NAME_FLOW = WaterFlow.class.getSimpleName();

   /**
    * Name of the state for the bed elevation
    */
   public static final String DEFAULT_NAME_BED_ELEV = BedElevation.class.getSimpleName();

   /**
    * Name of the state for the depth of the channel flow
    */
   public static final String DEFAULT_NAME_DEPTH = Depth.class.getSimpleName();

   /**
    * Name of the optional state for bank slope
    */
   public static final String DEFAULT_NAME_BANK_SLOPE = "BankSlope";

   /**
    * Name of the state for the average width of the active channel
    */
   public static final String DEFAULT_NAME_ACTIVE_CHANNEL_WIDTH_AVG = "ActiveWidthAverage";

   /**
    * X coordinate
    */
   public static final String DEFAULT_NAME_COORD_X = "X";

   /**
    * Y coordinate
    */
   public static final String DEFAULT_NAME_COORD_Y = "Y";

   /**
    * Name of the state for bank elevation
    */
   public static final String DEFAULT_NAME_BANK_ELEV = "BankElevation";

   /**
    * Name of the state for channel bottom area
    */
   public static final String DEFAULT_NAME_ACTIVE_CHANNEL_BOTTOM_AREA = BottomArea.class.getSimpleName();

   /**
    * Name of the state for change in wetted area with depth
    */
   public static final String DEFAULT_NAME_WETTED_AREA_CHANGE = WettedAreaChange.class.getSimpleName();

   /**
    * Name of the state for the maximum wetted area
    */
   public static final String DEFAULT_NAME_WETTED_AREA_MAX = WettedAreaMax.class.getSimpleName();

   /**
    * Name of the state for length
    */
   public static final String DEFAULT_NAME_LENGTH = "Length";

   /**
    * Name of the state for the wetted area
    */
   public static final String DEFAULT_NAME_WETTED_AREA = WettedArea.class.getSimpleName();

   /**
    * Name of the state for the hydraulic radius of the channel flow
    */
   public static final String DEFAULT_NAME_HYDR_RADIUS = HydraulicRadius.class.getSimpleName();

   /**
    * Name of the state for the wetted with of channel flow
    */
   public static final String DEFAULT_NAME_WETTED_WIDTH = WettedWidth.class.getSimpleName();

   /**
    * Name of the state for the fraction of the length between cells
    */
   public static final String DEFUALT_NAME_LENGTH_FRACTION = LengthFraction.class.getSimpleName();

   /**
    * Name of the state for the width of the bottom of the channel
    */
   public static final String DEFAULT_NAME_ACTIVE_CHANNEL_BOTTOM_WIDTH = BottomWidth.class.getSimpleName();

   /**
    * Name of the state for the change in the wetted width with depth
    */
   public static final String DEFAULT_NAME_WETTED_WIDTH_CHANGE = WettedWidthChange.class.getSimpleName();

   /**
    * Name of optional state for specifying the length within the associated cell
    */
   public static final String DEFAULT_NAME_LENGTH_LOCAL = "LengthLoc";

   /**
    * Name of the optional state for specifying the length within the adjacent cell
    */
   public static final String DEFAULT_NAME_LENGTH_ADJACENT = "LengthAdj";

   /**
    * Name of the state for the depth of the active channel
    */
   public static final String DEFAULT_NAME_ACTIVE_CHANNEL_DEPTH = "ActiveDepth";

   /**
    * Name of the state for the bed slope
    */
   public static final String DEFAULT_NAME_BED_SLOPE = BedSlope.class.getSimpleName();

   /**
    * Name of the state for hydraulic gradient
    */
   public static final String DEFAULT_NAME_HYDR_GRAD = HydraulicGradient.class.getSimpleName();

   /**
    * Name of the state for the boundary length between cell centroids
    */
   public static final String DEFAULT_NAME_LENGTH_BOUND = LengthBound.class.getSimpleName();

   /**
    * Name of the state for cross-sectional area of channel flow
    */
   public static final String DEFAULT_NAME_WETTED_XSECT_AREA = XSectAreaCurrent.class.getSimpleName();

   /**
    * Name of the state for the previous cross-sectional area
    */
   public static final String DEFAULT_NAME_WETTED_XSECT_AREA_PREV = XSectAreaPrevious.class.getSimpleName();

   /**
    * Name of the state for velocity
    */
   public static final String DEFAULT_NAME_VELOCITY = Velocity.class.getSimpleName();

   /**
    * Name of the state for the Chezey coefficient
    */
   public static final String DEFAULT_NAME_CHEZEY = Chezey.class.getSimpleName();

   /**
    * Name of the state for the velocity exponent
    */
   public static final String DEFAULT_NAME_CHEZEY_EXP_VELOCITY = "VelocityExp";

   /**
    * Name of the state for the radius exponent
    */
   public static final String DEFAULT_NAME_CHEZEY_EXP_RADIUS = "RadiusExp";

   /**
    * Name of the state for the friction factor
    */
   public static final String DEFAULT_NAME_FRICTION_FACTOR = Friction.class.getSimpleName();

   /**
    * Name of required state for the Wiele model intercept
    */
   public static final String DEFAULT_NAME_WIELE_MODEL_INTERCEPT = "WieleInt";

   /**
    * Name of the required state for the Wiele model slope
    */
   public static final String DEFAULT_NAME_WIELE_MODEL_SLOPE = "WieleSlope";

   /**
    * Name for the upstream boundary name
    */
   public static final String DEFAULT_NAME_UPSTREAM_BOUNDARY_NAME = "UpstreamBoundaryName";

   /**
    * Name for upstream temperature
    */
   public static final String DEFAULT_NAME_UPSTREAM_TEMP = "UpstreamTemperature";

   /**
    * Name for downstream temperature
    */
   public static final String DEFAULT_NAME_DOWNSTREAM_TEMP = "DownstreamTemperature";

   /**
    * Name for the average temperature
    */
   public static final String DEFAULT_NAME_AVG_TEMP = "AverageTemperature";

   /**
    * Default name for the temperature
    */
   public static final String DEFAULT_NAME_TEMP = "Temperature";

   /**
    * Default name for the holon with the average temperature
    */
   public static final String DEFAULT_NAME_AVG_TEMP_HOLON = "AverageTemperatureHolon";

   /**
    * Default name for upstream water density
    */
   public static final String DEFAULT_NAME_WATER_DENSITY_UPSTREAM = "WaterDensityUpstream";

   /**
    * Default name for downstream water density
    */
   public static final String DEFAULT_NAME_WATER_DENSITY_DOWNSTREAM = "WaterDensityDownstream";
   
   /**
    * Get the Euclidian distance on a two-dimensional plane
    * 
    * @param x1
    *       First x coordinate
    * @param y1
    *       First y coordinate
    * @param x2
    *       Second x coordinate
    * @param y2
    *       Second y coordinate
    * @return
    *       Euclidian distance between first and second coordinates
    */
   public static double getHorizontalDistance(
         double x1, double y1, double x2, double y2) 
   {
      double x = x1 - x2;
      double y = y1 - y2;
      return Math.sqrt(x * x + y * y);
   }
   
   /**
    * Function for getting exponents for Chezey equation
    * 
    * @param holon 
    *       holon to search for the exponents
    * @param processor
    *       processor requesting the exponents
    * @return
    *       array of exponents: first element is velocity exponent,
    *       second element is radius exponent
    */
   public static ValueDouble[] getChezeyExponentValues(
         Holon holon, Processor processor)
   {
      ValueDouble[] exponents = new ValueDouble[2];
      try
      {
         exponents[0] = (ValueDouble)processor.createDependencyOnValue(
               holon,
               DEFAULT_NAME_CHEZEY_EXP_VELOCITY
               );
         try
         {
            exponents[1] = (ValueDouble)processor.createDependencyOnValue(
                  holon,
                  DEFAULT_NAME_CHEZEY_EXP_RADIUS
                  );
         }
         catch (Exception e)
         {
            throw new Exception(String.format(
                  "Radius exponent not available when velocity exponent provided in boundary %s", 
                  processor.getState().getParentHolon().toString()
                  ));
         }
      }
      catch (Exception e)
      {
         try
         {
            exponents[1] = (ValueDouble)processor.createDependencyOnValue(
                  holon,
                  DEFAULT_NAME_CHEZEY_EXP_RADIUS
                  );
            throw new Exception(String.format(
                  "Radius exponent provided but velocity exponent not available in boundary %s", 
                  processor.getState().getParentHolon().toString()
                  ));
         }
         catch (Exception e2)
         {
            exponents[0] = null;
            exponents[1] = null;
         }
      }
      return exponents;
   }
   
   @Override
   public void addBehaviors()
   {
      addBehavior(
            BEHAVIOR_CHANNEL_STORAGE, 
            BehaviorChannelStorage.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_DYNAMIC_WAVE, 
            BehaviorDynamicWave.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_WIELE_FRICTION, 
            BehaviorWieleFriction.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_FLOW_INTERPOLATE, 
            BehaviorFlowInterpolate.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_DYNAMIC_WAVE_DOWNSTREAM, 
            BehaviorDynamicWaveDownstream.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_REACH_AVG_TEMP_BOUND,
            BehaviorReachAvgTemperatureBound.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_REACH_AVG_TEMP,
            BehaviorReachAvgTemperature.class.getCanonicalName()
            );
   }

}
