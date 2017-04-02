package org.payn.resources.water.channel.temperature;

import org.payn.chsm.io.interpolate.Interpolator;
import org.payn.neoch.processors.ProcessorDoubleStateInit;

public abstract class WaterTempInterp extends ProcessorDoubleStateInit {

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
