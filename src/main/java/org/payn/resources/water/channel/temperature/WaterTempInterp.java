package org.payn.resources.water.channel.temperature;

import org.payn.chsm.finitediff.processors.ProcessorDoublePostauxiliaryInit;
import org.payn.chsm.io.inputters.Interpolator;

/**
 * Abstract processor for getting temperature from an interpolation file
 * 
 * @author robpayn
 *
 */
public abstract class WaterTempInterp extends ProcessorDoublePostauxiliaryInit {

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
