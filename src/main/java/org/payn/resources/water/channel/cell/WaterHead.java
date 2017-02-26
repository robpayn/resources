package org.payn.resources.water.channel.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleState;
import org.payn.resources.water.ResourceWater;

/**
 * Hydraulic head of water in the cell
 * 
 * @author robpayn
 *
 */
public class WaterHead extends ProcessorDoubleState {

   /**
    * Volume of water
    */
   private ValueDouble volume;
   
   /**
    * Bed elevation
    */
   private ValueDouble bedElevation;
   
   /**
    * Depth of the channel flow
    */
   private ValueDouble depth;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      volume = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_WATER_VOLUME
            );
      bedElevation = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_BED_ELEVATION
            );
      depth = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_DEPTH
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (volume.n <= 0)
      {
         value.n = bedElevation.n;
      }
      else
      {
         value.n = bedElevation.n + depth.n;
      }
   }

}