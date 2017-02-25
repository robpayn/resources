package org.payn.resources.water.surface.boundary;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.processors.interfaces.InitializerSimple;
import org.payn.chsm.processors.interfaces.UpdaterSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleTrade;

/**
 * Calculates the current cross-sectional area of channel flow
 * 
 * @author v78h241
 *
 */
public class XSectAreaCurrent extends ProcessorDoubleTrade implements InitializerAutoSimple {

   /**
    * Processor for the previous cross sectional area state
    */
   private UpdaterSimple xSectionAreaPreviousProc;
   
   /**
    * Depth of the channel flow
    */
   private ValueDouble depth;
   
   /**
    * Width of the bottom of a trapezoidal channel
    */
   private ValueDouble bottomWidth;
   
   /**
    * Change in the wetted width with depth
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
      calculate();
      ((InitializerSimple)xSectionAreaPreviousProc).initialize();
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
      xSectionAreaPreviousProc = (UpdaterSimple)getState().getParentHolon().getState(
            BehaviorDynamicWave.NAME_XSECT_AREA_PREV
            ).getProcessor();
   }

   @Override
   public void update() throws Exception 
   {
      xSectionAreaPreviousProc.update();
      calculate();
   }
   
   /**
    * Calculate the current value
    */
   private void calculate()
   {
      if (depth.n <= 0)
      {
         value.n = 0.0;
      }
      else
      {
         value.n = (bottomWidth.n + (wettedWidthChange.n * depth.n) / 2) * depth.n;
      }
   }

   /**
    * Get the depth value
    * 
    * @return
    *       ValueDouble object for depth
    */
   public ValueDouble getDepthValue() 
   {
      return depth;
   }

}
