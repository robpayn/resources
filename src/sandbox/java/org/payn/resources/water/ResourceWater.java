package org.payn.resources.water;

import org.payn.chsm.ResourceAbstract;
import org.payn.resources.water.channel.boundary.BedElevation;
import org.payn.resources.water.channel.boundary.Depth;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;
import org.payn.resources.water.channel.boundary.dynamicwave.WaterFlow;
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
   
   @Override
   public void addBehaviors()
   {
      addBehavior(BEHAVIOR_CHANNEL_STORAGE, BehaviorChannelStorage.class.getCanonicalName());
      addBehavior(BEHAVIOR_DYNAMIC_WAVE, BehaviorDynamicWave.class.getCanonicalName());
      addBehavior(BEHAVIOR_DYNAMIC_WAVE_WIELE, BehaviorDynamicWave.class.getCanonicalName());
   }

}
