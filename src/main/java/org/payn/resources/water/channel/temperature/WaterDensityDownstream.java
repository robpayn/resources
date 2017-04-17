package org.payn.resources.water.channel.temperature;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleState;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the value of the state for the downstream density
 * 
 * @author robpayn
 *
 */
public class WaterDensityDownstream extends ProcessorDoubleState {

   /**
    * Temperature
    */
   private ValueDouble temperature;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      temperature = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_DOWNSTREAM_TEMP
            );
   }

   @Override
   public void update() throws Exception 
   {
      value.n = Calculators.densityWaterEmpirical(temperature.n);
   }

}
