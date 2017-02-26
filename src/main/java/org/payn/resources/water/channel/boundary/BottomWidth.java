package org.payn.resources.water.channel.boundary;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;

/**
 * Width of the bottom of a trapezoidal channel
 * 
 * @author v78h241
 *
 */
public class BottomWidth extends ProcessorDouble implements InitializerAutoSimple {

   /**
    * Slope of the channel banks in a trapezoidal channel
    */
   private ValueDouble bankSlope;
   
   /**
    * Average width of the active channel
    */
   private ValueDouble activeWidthAvg;
   
   /**
    * Depth of the active channel
    */
   private ValueDouble activeDepth;

   @Override
   public void setInitDependencies() throws Exception 
   {
      activeWidthAvg = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_ACTIVE_WIDTH_AVG
            );
      try
      {
         bankSlope = (ValueDouble)createDependencyOnValue(
               ResourceWater.NAME_BANK_SLOPE
               );
         try
         {
            activeDepth = (ValueDouble)createDependencyOnValue(
                  BehaviorDynamicWave.NAME_ACTIVE_DEPTH
                  );
         }
         catch (Exception e)
         {
            throw new Exception(String.format(
                  "Active depth not provided with a bank slope in boundary %s", 
                  getState().getParentHolon().toString()
                  ));
         }
      }
      catch (Exception e)
      {
         try
         {
            activeDepth = (ValueDouble)createDependencyOnValue(
                  BehaviorDynamicWave.NAME_ACTIVE_DEPTH
                  );
            throw new Exception(String.format(
                  "Active depth provided without a bank slope in boundary %s", 
                  getState().getParentHolon().toString()
                  ));
         }
         catch (Exception e2)
         {
            bankSlope = null;
            activeDepth = null;
         }
      }
   }

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
          if (bankSlope == null)
          {
              value.n = activeWidthAvg.n;
          }
          else
          {
              value.n = activeWidthAvg.n - ((activeDepth.n) / Math.tan(bankSlope.n));
          }
      }
   }

}
