package org.payn.resources.water.channel.boundary;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleTrade;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;

/**
 * Calculates the wetted width
 * 
 * @author v78h241
 *
 */
public class WettedWidth extends ProcessorDoubleTrade implements InitializerAutoSimple {

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
            ResourceWater.NAME_DEPTH
            );
      bottomWidth = (ValueDouble)createDependencyOnValue(
            BehaviorDynamicWave.NAME_BOTTOM_WIDTH
            );
      wettedWidthChange = (ValueDouble)createDependencyOnValue(
            BehaviorDynamicWave.NAME_WETTED_WIDTH_CHANGE
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (depth.n > 0)
      {
         value.n = bottomWidth.n + (depth.n * wettedWidthChange.n);
      }
      else
      {
         value.n = 0;
      }
   }

}
