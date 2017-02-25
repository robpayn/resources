package org.payn.resources.water;

import org.payn.chsm.ResourceAbstract;
import org.payn.resources.water.surface.boundary.BedElevation;
import org.payn.resources.water.surface.boundary.BehaviorDynamicWave;
import org.payn.resources.water.surface.cell.WaterHead;
import org.payn.resources.water.surface.cell.WaterVolume;

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
    * Name of the state for channel elevation
    */
   public static final String NAME_ELEVATION = BedElevation.class.getSimpleName();

   /**
    * Name of behavior for dynamic wave routing
    */
   private static final String BEHAVIOR_DYNAMIC_WAVE = "dynamicwave";

   /**
    * Name of behavior for dynamic wave routing with the Wiele model for variable friction
    */
   private static final String BEHAVIOR_DYNAMIC_WAVE_WIELE = "dynamicwavewiele";

   @Override
   public void addBehaviors()
   {
      addBehavior(BEHAVIOR_DYNAMIC_WAVE, BehaviorDynamicWave.class.getCanonicalName());
      addBehavior(BEHAVIOR_DYNAMIC_WAVE_WIELE, BehaviorDynamicWave.class.getCanonicalName());
   }

}
