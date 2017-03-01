package org.payn.resources.water.channel.boundary;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.auto.ProcessorDoubleInit;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the change in the width of the wetted channel with depth for a trapezoidal channel
 * 
 * @author v78h241
 *
 */
public class WettedWidthChange extends ProcessorDoubleInit {

   /**
    * Angle of the bank slope
    */
   private ValueDouble bankSlope;

   @Override
   public void setInitDependencies() throws Exception 
   {
      try
      {
         bankSlope = (ValueDouble)createDependencyOnValue(
               ResourceWater.NAME_BANK_SLOPE
               );
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
         if (bankSlope == null)
         {
            value.n = 0.0;
         }
         else if (bankSlope.n > Math.PI / 2.0)
         {
            value.setToNoValue();
            throw new Exception(String.format(
                  "Invalid bank slope provided for boundary %s", 
                  state.getParentHolon().toString()
                  ));
         }
         else
         {
            value.n = (2.0 / Math.tan(bankSlope.n));
         }
      }
   }

}
