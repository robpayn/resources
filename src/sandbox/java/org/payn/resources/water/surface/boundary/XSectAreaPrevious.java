package org.payn.resources.water.surface.boundary;

import org.payn.chsm.State;
import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerSimple;
import org.payn.chsm.processors.interfaces.UpdaterSimple;
import org.payn.chsm.values.ValueDouble;

/**
 * Stores the previous cross sectional area for calculations during the next time step
 * 
 * @author v78h241
 *
 */
public class XSectAreaPrevious extends ProcessorDouble implements UpdaterSimple, InitializerSimple {

   /**
    * Depth of the channel flow
    */
   private ValueDouble depth;
   
   /**
    * Current cross-sectional area of the channel flow
    */
   private ValueDouble xSectionArea;

   @Override
   public void initialize() throws Exception 
   {
      State xSectionAreaState = state.getParentHolon().getState(
            BehaviorDynamicWave.NAME_XSECT_AREA
            );
      xSectionArea = (ValueDouble)xSectionAreaState.getValue();
      depth = ((XSectAreaCurrent)xSectionAreaState.getProcessor()).getDepthValue();
      update();
   }

   @Override
   public void update() throws Exception 
   {
      if (depth.n > 0)
      {
         value.n = xSectionArea.n;
      }
      else
      {
         value.n = 0.0;
      }
   }

}
