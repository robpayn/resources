package org.payn.resources.water.channel.temperature;

import org.payn.chsm.finitediff.processors.ProcessorDoublePostauxiliaryInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Average water temperature based on an upstream and downstream
 * temperature
 * 
 * @author robpayn
 *
 */
public class WaterTempAvg extends ProcessorDoublePostauxiliaryInit {

   /**
    * Upstream temperature
    */
   private ValueDouble upstreamTemp;
   
   /**
    * Downstream temperature
    */
   private ValueDouble downstreamTemp;

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

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      upstreamTemp = (ValueDouble)createDependency(
            ResourceWater.DEFAULT_NAME_UPSTREAM_TEMP
            ).getValue();
      downstreamTemp = (ValueDouble)createDependency(
            ResourceWater.DEFAULT_NAME_DOWNSTREAM_TEMP
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = 0.5 * (upstreamTemp.n + downstreamTemp.n);
   }

}
