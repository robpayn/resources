package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerSimple;
import org.payn.chsm.processors.interfaces.UpdaterSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

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

   /**
    * Set the dependencies
    * 
    * @param xSectAreaCurrent
    *       xSectArea processor controlling this processor
    * @throws Exception
    *       if error in creating dependencies
    */
   public void setDependencies(XSectAreaCurrent xSectAreaCurrent) 
         throws Exception 
   {
      xSectionArea = (ValueDouble)xSectAreaCurrent.getValue();
      depth = (ValueDouble)xSectAreaCurrent.createDependency(
            ResourceWater.DEFAULT_NAME_DEPTH
            ).getValue();
   }

   @Override
   public void initialize() throws Exception 
   {
      update();
   }

   @Override
   public void update() throws Exception 
   {
      if (depth.n > 0.0)
      {
         value.n = xSectionArea.n;
      }
      else
      {
         value.n = 0.0;
      }
   }

}
