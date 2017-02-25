package org.payn.resources.water.surface.boundary;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;

/**
 * Calculates the change in the width of the wetted channel with depth for a trapezoidal channel
 * 
 * @author v78h241
 *
 */
public class WettedWidthChange extends ProcessorDouble implements InitializerAutoSimple {

   /**
    * Angle of the bank slope
    */
   private ValueDouble bankSlope;

   @Override
   public void setInitDependencies() throws Exception 
   {
      try
      {
         bankSlope = (ValueDouble)createDependency(
               BehaviorDynamicWave.REQ_STATE_BANK_SLOPE
               ).getValue();
      }
      catch (Exception e)
      {
         bankSlope = null;
      }
   }

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
         if (bankSlope.n < 0)
         {
            value.n = 0;
         }
         else if (bankSlope.n > Math.PI / 2)
         {
            value.setToNoValue();
            throw new Exception(String.format(
                  "Invalid bank slope provided for boundary %s", 
                  state.getParentHolon().toString()
                  ));
         }
         else
         {
            value.n = (2 / Math.tan(bankSlope.n));
         }
      }
   }

}
