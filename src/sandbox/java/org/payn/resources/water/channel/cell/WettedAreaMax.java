package org.payn.resources.water.channel.cell;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Calculate the maximum wetted area of the cell
 * 
 * @author robpayn
 *
 */
public class WettedAreaMax extends ProcessorDouble implements InitializerAutoSimple {

   /**
    * Average width of active channel
    */
   private ValueDouble activeWidthAvg;
   
   /**
    * Area of the bottom of a trapezoidal channel
    */
   private ValueDouble bottomArea;
   
   /**
    * Length of the cell
    */
   private ValueDouble length;

   @Override
   public void setInitDependencies() throws Exception 
   {
      activeWidthAvg = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_ACTIVE_WIDTH_AVG
            );
      bottomArea = (ValueDouble)createDependencyOnValue(
            BehaviorChannelStorage.NAME_BOTTOM_AREA
            );
      length = (ValueDouble)createDependencyOnValue(
            BehaviorChannelStorage.NAME_LENGTH
            );
   }

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
          value.n = (activeWidthAvg.n + (activeWidthAvg.n - (bottomArea.n / length.n))) 
                * length.n;
      }
   }

}
