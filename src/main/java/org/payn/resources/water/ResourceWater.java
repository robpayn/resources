package org.payn.resources.water;

import org.payn.chsm.Holon;
import org.payn.chsm.Processor;
import org.payn.chsm.ResourceAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.channel.boundary.BedElevation;
import org.payn.resources.water.channel.boundary.Depth;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWaveWiele;
import org.payn.resources.water.channel.boundary.dynamicwave.WaterFlow;
import org.payn.resources.water.channel.boundary.dynamicwave.downstream.BehaviorDynamicWaveDownstream;
import org.payn.resources.water.channel.boundary.flowinterpolate.BehaviorFlowInterpolate;
import org.payn.resources.water.channel.cell.BehaviorChannelStorage;
import org.payn.resources.water.channel.cell.WaterHead;
import org.payn.resources.water.channel.cell.WaterVolume;

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
   public static final double GRAVITY_ACC = 9.8067;

   /**
    * Name of state for water volume
    */
   public static final String NAME_WATER_VOLUME = WaterVolume.class.getSimpleName();

   /**
    * Name of state for water head
    */
   public static final String NAME_WATER_HEAD = WaterHead.class.getSimpleName();

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
   public static final String BEHAVIOR_DYNAMIC_WAVE_WIELE = "dynamicwavewiele";

   /**
    * Name of the behavior for the downstream boundary condition of a dynamic wave channel
    */
   public static final String BEHAVIOR_DYNAMIC_WAVE_DOWNSTREAM = "dynamicwavedownstream";

   /**
    * Behavior flow flow interpolation at boundary
    */
   public static final String BEHAVIOR_FLOW_INTERPOLATE = "flowinterpolate";

   /**
    * Name of the state for water flow
    */
   public static final String NAME_WATER_FLOW = WaterFlow.class.getSimpleName();

   /**
    * Name of the state for the bed elevation
    */
   public static final String NAME_BED_ELEVATION = BedElevation.class.getSimpleName();

   /**
    * Name of the state for the depth of the channel flow
    */
   public static final String NAME_DEPTH = Depth.class.getSimpleName();

   /**
    * Name of the optional state for bank slope
    */
   public static final String NAME_BANK_SLOPE = "BankSlope";

   /**
    * Name of the state for the average width of the active channel
    */
   public static final String NAME_ACTIVE_WIDTH_AVG = "ActiveWidthAverage";

   /**
    * X coordinate
    */
   public static final String NAME_X = "X";

   /**
    * Y coordinate
    */
   public static final String NAME_Y = "Y";

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
               BehaviorDynamicWave.REQ_STATE_VELOCITY_EXP
               );
         try
         {
            exponents[1] = (ValueDouble)processor.createDependencyOnValue(
                  holon,
                  BehaviorDynamicWave.REQ_STATE_RADIUS_EXP
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
                  BehaviorDynamicWave.REQ_STATE_RADIUS_EXP
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
      addBehavior(BEHAVIOR_CHANNEL_STORAGE, BehaviorChannelStorage.class.getCanonicalName());
      addBehavior(BEHAVIOR_DYNAMIC_WAVE, BehaviorDynamicWave.class.getCanonicalName());
      addBehavior(BEHAVIOR_DYNAMIC_WAVE_WIELE, BehaviorDynamicWaveWiele.class.getCanonicalName());
      addBehavior(BEHAVIOR_FLOW_INTERPOLATE, BehaviorFlowInterpolate.class.getCanonicalName());
      addBehavior(BEHAVIOR_DYNAMIC_WAVE_DOWNSTREAM, BehaviorDynamicWaveDownstream.class.getCanonicalName());
   }

}
