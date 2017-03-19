package org.payn.resources.water.channel.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleStateInit;
import org.payn.resources.water.ResourceWater;

/**
 * Calculate the wetted area of water in the cell
 * 
 * @author robpayn
 *
 */
public class WettedArea extends ProcessorDoubleStateInit {

   /**
    * Volume of water in cell
    */
   private ValueDouble volume;
   
   /**
    * Head in cell
    */
   private ValueDouble head;
   
   /**
    * Elevation of banks
    */
   private ValueDouble bankElevation;
   
   /**
    * Maximum wetted area for flood conditions
    */
   private ValueDouble wettedAreaMax;
   
   /**
    * Area of the bottom of a trapezoidal channel
    */
   private ValueDouble bottomArea;
   
   /**
    * Change in wetted area with depth
    */
   private ValueDouble wettedAreaChange;
   
   /**
    * Depth of water
    */
   private ValueDouble depth;

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
            ResourceWater.DEFAULT_NAME_VOLUME
            );
      head = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_HEAD
            );
      depth = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_DEPTH
            );
      bankElevation = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_BANK_ELEV
            );
      wettedAreaMax = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_WETTED_AREA_MAX
            );
      wettedAreaChange = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_WETTED_AREA_CHANGE
            );
      bottomArea = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_ACTIVE_CHANNEL_BOTTOM_AREA
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (volume.n <= 0.0)
      {
         value.n = 0.0;
      }
      else if (head.n > bankElevation.n)
      {
         // Flooded
         value.n = wettedAreaMax.n;
      }
      else
      {
         value.n = bottomArea.n + (wettedAreaChange.n * depth.n);
      }
   }

}
