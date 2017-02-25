package org.payn.resources.water.surface.boundary;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleTrade;

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
      depth = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_DEPTH
            );
      bottomWidth = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_BOTTOM_WIDTH
            );
      wettedWidthChange = (ValueDouble)createDependency(
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
