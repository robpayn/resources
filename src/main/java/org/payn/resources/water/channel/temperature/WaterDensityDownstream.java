package org.payn.resources.water.channel.temperature;

import org.payn.chsm.processors.finitedifference.ProcessorDoubleInfoInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the value of the state for the downstream density
 * 
 * @author robpayn
 *
 */
public class WaterDensityDownstream extends ProcessorDoubleInfoInit {

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

}
