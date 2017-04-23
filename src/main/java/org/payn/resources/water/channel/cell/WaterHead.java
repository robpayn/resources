package org.payn.resources.water.channel.cell;

import org.payn.chsm.processors.finitedifference.ProcessorDoubleInfoInitRequired;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Hydraulic head of water in the cell
 * 
 * @author robpayn
 *
 */
public class WaterHead extends ProcessorDoubleInfoInitRequired  {

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
            ResourceWater.DEFAULT_NAME_VOLUME
            );
      bedElevation = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_BED_ELEV
            );
      depth = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_DEPTH
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (volume.n <= 0.0)
      {
         value.n = bedElevation.n;
      }
      else
      {
         value.n = bedElevation.n + depth.n;
      }
   }

}
