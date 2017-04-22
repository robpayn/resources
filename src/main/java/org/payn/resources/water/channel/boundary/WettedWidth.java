package org.payn.resources.water.channel.boundary;

import org.payn.chsm.finitedifference.processors.ProcessorDoubleChangeInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the wetted width
 * 
 * @author v78h241
 *
 */
public class WettedWidth extends ProcessorDoubleChangeInit {

   /**
    * Depth of channel flow
    */
   private ValueDouble depth;
   
   /**
    * Width of the bottom of the channel
    */
   private ValueDouble bottomWidth;
   
   /**
    * Change in width with depth
    */
   private ValueDouble wettedWidthChange;

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
      depth = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_DEPTH
            );
      bottomWidth = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_ACTIVE_CHANNEL_BOTTOM_WIDTH
            );
      wettedWidthChange = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_WETTED_WIDTH_CHANGE
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (depth.n > 0.0)
      {
         value.n = bottomWidth.n + (depth.n * wettedWidthChange.n);
      }
      else
      {
         value.n = 0.0;
      }
   }

}
