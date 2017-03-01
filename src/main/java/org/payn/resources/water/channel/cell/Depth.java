package org.payn.resources.water.channel.cell;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleState;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the depth of water in the cell
 * 
 * @author robpayn
 *
 */
public class Depth extends ProcessorDoubleState implements InitializerAutoSimple {

   /**
    * Volume of water in the cell
    */
   private ValueDouble volume;
   
   /**
    * Bank elevation
    */
   private ValueDouble bankElevation;
   
   /**
    * Bed elevation
    */
   private ValueDouble bedElevation;
   
   /**
    * Change in wetted area with depth
    */
   private ValueDouble wettedAreaChange;
   
   /**
    * Area of the bottom of a trapezoidal channel
    */
   private ValueDouble bottomArea;
   
   /**
    * Maximum wetted area
    */
   private ValueDouble wettedAreaMax;

   @Override
   public void setInitDependencies() throws Exception 
   {
      setUpdateDependencies();
   }

   @Override
   public void initialize() throws Exception 
   {
      update();
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      volume = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_WATER_VOLUME
            );
      bankElevation = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_BANK_ELEVATION
            );
      bedElevation = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_BED_ELEVATION
            );
      wettedAreaChange = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_WETTED_AREA_CHANGE
            );
      wettedAreaMax = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_WETTED_AREA_MAX
            );
      bottomArea = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_ACTIVE_CHANNEL_BOTTOM_AREA
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (volume.n == 0.0)
      {
         value.n = 0.0;
      }
      else
      {
         double maxdepth = bankElevation.n - bedElevation.n;
         if (wettedAreaChange.n == 0.0)
         {
            value.n = volume.n / bottomArea.n;
         }
         else
         {
            value.n = (Math.sqrt(bottomArea.n * bottomArea.n + 2.0 * wettedAreaChange.n * volume.n) - bottomArea.n) 
                  / wettedAreaChange.n;
         }
         
         if (value.n > maxdepth)
         {
            double vol = (maxdepth * bottomArea.n) + (maxdepth * maxdepth * wettedAreaChange.n / 2.0);
            value.n = maxdepth + (volume.n - vol) / wettedAreaMax.n;
         }
      }
   }

}
