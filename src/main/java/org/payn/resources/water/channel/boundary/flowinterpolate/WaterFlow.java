package org.payn.resources.water.channel.boundary.flowinterpolate;

import org.payn.chsm.io.interpolate.Interpolator;
import org.payn.chsm.io.interpolate.InterpolatorSnapshotTable;
import org.payn.neoch.processors.ProcessorDoubleLoadInit;

/**
 * Calculates the water flow at an input boundary by interpolating from
 * table data
 * 
 * @author robpayn
 *
 */
public class WaterFlow extends ProcessorDoubleLoadInit {

   /**
    * Interpolator
    */
   private Interpolator interp;

   @Override
   public void setInitDependencies() throws Exception 
   {
      interp = InterpolatorSnapshotTable.getInterpolatorInstance(this);
   }

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
         value.n = interp.interpolate();
      }
   }
   
   @Override
   public void setUpdateDependenciesLoad() throws Exception 
   {}

   @Override
   public void updateLoad() throws Exception 
   {
      value.n = interp.interpolate();
   }

}
