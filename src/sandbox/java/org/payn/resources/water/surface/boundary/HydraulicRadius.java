package org.payn.resources.water.surface.boundary;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleTrade;

/**
 * Calculates the hydraulic radius of channel flow
 * 
 * @author v78h241
 *
 */
public class HydraulicRadius extends ProcessorDoubleTrade implements InitializerAutoSimple {

   /**
    * Depth of the channel flow
    */
   private ValueDouble depth;

   /**
    * Cross-sectional area of the channel flow
    */
   private ValueDouble xSectionArea;

   /**
    * Wetted width of the channel flow
    */
   private ValueDouble wettedWidth;

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
            ).getValue();
      xSectionArea = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_XSECT_AREA
            ).getValue();
      wettedWidth = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_WETTED_WIDTH
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      if (depth.n <= 0.0)
      {
         value.n = 0.0;
      }
      else
      {
         double hydraulicRadius = xSectionArea.n / wettedWidth.n;
         if (hydraulicRadius > BehaviorDynamicWave.MAX_HYDRAULIC_RADIUS)
         {
            throw new Exception(String.format(
                  "Hydraulic radius is larger than maximum allowed by the behavior in boundary %s",
                  getState().getParentHolon().toString()
                  ));
         }
         value.n = hydraulicRadius;
      }
   }

}
