package org.payn.resources.water.channel.cell;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorStorageDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Calculate the volume of water in a cell
 * 
 * @author v78h241
 *
 */
public class WaterVolume extends ProcessorStorageDouble implements InitializerAutoSimple {

   /**
    * Hydraulic head in the cell
    */
   private ValueDouble head;
   
   /**
    * Elevation of the bank
    */
   private ValueDouble elevationBank;
   
   /**
    * Elevation of the bed
    */
   private ValueDouble elevationBed;
   
   /**
    * Area of the bottom of the channel
    */
   private ValueDouble bottomArea;
   
   /**
    * Change in wetted area with depth
    */
   private ValueDouble wettedAreaChange;
   
   /**
    * Maximum wetted area
    */
   private ValueDouble wettedAreaMax;

   @Override
   public void setInitDependencies() throws Exception 
   {
      head = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_WATER_HEAD
            );
      elevationBank = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_BANK_ELEVATION
            );
      elevationBed = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_BED_ELEVATION
            );
      bottomArea = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_ACTIVE_CHANNEL_BOTTOM_AREA
            );
      wettedAreaChange = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_WETTED_AREA_CHANGE
            );
      wettedAreaMax = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_WETTED_AREA_MAX
            );
   }

   @Override
   public void initialize() throws Exception 
   {
      double depth;
      
      // get depth of water in channel with a maximum depth of bankfull flow
      if (head.n > elevationBank.n)
      {
          // flooded, depth is maximum channel depth
          depth = elevationBank.n - elevationBed.n;
      }
      else
      {
          // not flooded, depth is water in channel
          depth = head.n - elevationBed.n;
      }
      
      // calculate volume of h2o in channel
      value.n = (depth * bottomArea.n) 
            + (depth * depth * wettedAreaChange.n / 2.0);
      
      // if flooded, add h20 from flood water
      if (head.n > elevationBank.n)
      {
          value.n += (head.n - elevationBank.n) * wettedAreaMax.n;
      }
   }

}
