package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerSimple;
import org.payn.chsm.processors.interfaces.UpdaterSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Calculate the velocity of the channel flow
 * 
 * @author robpayn
 *
 */
public class Velocity extends ProcessorDouble implements InitializerSimple, UpdaterSimple {

   /**
    * Channel flow
    */
   private ValueDouble waterFlow;
   
   /**
    * Cross-sectional area of the channel flow
    */
   private ValueDouble xSectionArea;
   
   /**
    * Cross-sectional area of the channel flow from the previous time step
    */
   private ValueDouble xSectionAreaPrev;

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
         update();
      }
   }
   
   /**
    * Set the update dependencies based on the governing water flow processor
    * 
    * @param waterFlowProc
    *       water flow processor controlling this velocity
    * @throws Exception
    *       if error in creating dependencies
    */
   public void setDependencies(WaterFlow waterFlowProc) throws Exception
   {
      waterFlow = (ValueDouble)waterFlowProc.getState().getValue();
      xSectionArea = (ValueDouble)waterFlowProc.createDependency(
            ResourceWater.DEFAULT_NAME_WETTED_XSECT_AREA
            ).getValue();
      xSectionAreaPrev = (ValueDouble)waterFlowProc.createDependency(
            ResourceWater.DEFAULT_NAME_WETTER_XSECT_AREA_PREV
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      // compute velocity based on last time step
      double v;
      if (xSectionArea.n > 0.0)
      {
         v = 2.0 * (waterFlow.n / (xSectionArea.n + xSectionAreaPrev.n));
      }
      else
      {
         v = 0.0;
      }
      if (Math.abs(v) > ResourceWater.CONSTANT_MAX_VELOCITY)
      {
         throw new Exception(String.format(
               "Velocity > MaxVelocity in boundary %s", 
               state.getParentHolon().toString()
               ));
      }
      value.n = v;
   }

}
