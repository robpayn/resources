package org.payn.resources.water.channel.temperature;

import org.payn.chsm.Holon;
import org.payn.chsm.processors.finitedifference.ProcessorDoubleInfoInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.resources.water.ResourceWater;

/**
 * Get the temperature for the holon based on an average temperature
 * calculated at the boundary
 * 
 * @author robpayn
 *
 */
public class WaterTemperature extends ProcessorDoubleInfoInit {

   /**
    * Reach average temperature calculated at the boundary
    */
   private ValueDouble reachAvgTemp;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      String holonName = ((ValueString)createDependency(
            ResourceWater.DEFAULT_NAME_AVG_TEMP_HOLON
            ).getValue()).string;
      reachAvgTemp = (ValueDouble)createDependency(
            (Holon)((Holon)getController().getState()).getState(holonName),
            ResourceWater.DEFAULT_NAME_AVG_TEMP
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = reachAvgTemp.n;
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
