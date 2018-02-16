package org.payn.resources.solute.mass.boundary.flow;

import org.payn.chsm.finitediff.processors.ProcessorDoublePreauxiliaryInit;
import org.payn.chsm.io.inputters.Interpolator;
import org.payn.chsm.io.inputters.InterpolatorSnapshotTable;

/**
 * Solute concentration processor based on interpolation from input data
 * 
 * @author robpayn
 *
 */
public class SoluteConcInterpolate extends ProcessorDoublePreauxiliaryInit {

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
