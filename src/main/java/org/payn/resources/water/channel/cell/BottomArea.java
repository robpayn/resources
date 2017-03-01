package org.payn.resources.water.channel.cell;

import org.payn.chsm.processors.auto.ProcessorDoubleInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Area of the bottom of a trapezoidal channel
 * 
 * @author robpayn
 *
 */
public class BottomArea extends ProcessorDoubleInit {

   /**
    * Slope angle of the bank
    */
   private ValueDouble bankSlope;
   
   /**
    * Average width of the active channel
    */
   private ValueDouble activeWidthAvg;
   
   /**
    * Length of the cell
    */
   private ValueDouble length;
   
   /**
    * Elevation of the length
    */
   private ValueDouble bankElevation;
   
   /**
    * Elevation of the bed
    */
   private ValueDouble bedElevation;

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
      activeWidthAvg = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_ACTIVE_CHANNEL_WIDTH_AVERAGE
            );
      length = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_LENGTH
            );
      bankElevation = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_BANK_ELEVATION
            );
      bedElevation = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_BED_ELEVATION
            );
   }

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
          if (bankSlope == null)
          {
              value.n = activeWidthAvg.n * length.n;
          }
          else
          {
              value.n = 
                    (activeWidthAvg.n - ((bankElevation.n - bedElevation.n) 
                          / Math.tan(bankSlope.n))) 
                    * length.n;
          }
      }
   }

}
