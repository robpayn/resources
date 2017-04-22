package org.payn.resources.water.channel.temperature;

import org.payn.chsm.finitedifference.processors.ProcessorDoubleInfoInit;
import org.payn.chsm.io.interpolate.Interpolator;

/**
 * Abstract processor for getting temperature from an interpolation file
 * 
 * @author robpayn
 *
 */
public abstract class WaterTempInterp extends ProcessorDoubleInfoInit {

   /**
    * Interpolator with access to the temperature data
    */
   protected Interpolator interp;

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
         value.n = interp.interpolate();
      }
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
   }

   @Override
   public void update() throws Exception 
   {
      value.n = interp.interpolate();
   }

}
