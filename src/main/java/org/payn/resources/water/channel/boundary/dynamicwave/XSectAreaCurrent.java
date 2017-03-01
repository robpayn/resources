package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.processors.interfaces.InitializerSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleTrade;
import org.payn.resources.water.ResourceWater;

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
   private XSectAreaPrevious xSectionAreaPreviousProc;
   
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
            ResourceWater.NAME_DEPTH
            ).getValue();
      bottomWidth = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_BOTTOM_WIDTH
            ).getValue();
      wettedWidthChange = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_WETTED_WIDTH_CHANGE
            ).getValue();
      xSectionAreaPreviousProc = (XSectAreaPrevious)getState().getParentHolon().getState(
            BehaviorDynamicWave.NAME_XSECT_AREA_PREV
            ).getProcessor();
      xSectionAreaPreviousProc.setDependencies(this);
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
      if (depth.n <= 0.0)
      {
         value.n = 0.0;
      }
      else
      {
         value.n = (bottomWidth.n + (wettedWidthChange.n * depth.n) / 2.0) * depth.n;
      }
   }

}
