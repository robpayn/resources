package org.payn.resources.solute.boundary.flow;

import org.payn.chsm.io.file.interpolate.Interpolator;
import org.payn.chsm.io.file.interpolate.InterpolatorSnapshotTable;
import org.payn.neoch.processors.ProcessorDoubleTradeInit;

/**
 * Solute concentration processor based on interpolation from input data
 * 
 * @author robpayn
 *
 */
public class SoluteConcInterpolate extends ProcessorDoubleTradeInit {

   /**
    * Interpolator
    */
   private Interpolator interp;

   @Override
   public void setInitDependencies() throws Exception 
   {
      interp = InterpolatorSnapshotTable.getInterpolatorInstanceAbstract(this);
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
   public void setUpdateDependencies() throws Exception 
   {}

   @Override
   public void update() throws Exception 
   {
      value.n = interp.interpolate();
   }

}
