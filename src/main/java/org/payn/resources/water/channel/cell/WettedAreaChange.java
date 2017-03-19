package org.payn.resources.water.channel.cell;

import org.payn.chsm.processors.auto.ProcessorDoubleInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the change in wetted area with depth
 * 
 * @author robpayn
 *
 */
public class WettedAreaChange extends ProcessorDoubleInit {

   /**
    * Slope of bank
    */
   private ValueDouble bankSlope;
   
   /**
    * Length of cell
    */
   private ValueDouble length;

   @Override
   public void setInitDependencies() throws Exception 
   {
      try
      {
         bankSlope = (ValueDouble)createDependencyOnValue(
               ResourceWater.DEFAULT_NAME_BANK_SLOPE
               );
      }
      catch (Exception e)
      {
         bankSlope = null;
      }
      
      length = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_LENGTH
            );
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
             throw new Exception(String.format(
                   "Invalid bank slope in cell %s", 
                   state.getParentHolon().toString()
                   ));
          }
          else
          {
              value.n = (2.0 / Math.tan(bankSlope.n)) 
                    * length.n;
          }
      }
   }

}
