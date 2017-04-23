package org.payn.resources.solute.mass.boundary;

import org.payn.chsm.io.inputters.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueString;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.boundary.flow.SoluteConcInterpolate;

/**
 * A boundary condition based on a solute concentration read from an input file
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteBoundInterp extends BehaviorSoluteBound {

   @Override
   public void registerStates() 
   {
      super.registerStates();
      registerStateAbstract(
            InterpolatorSnapshotTable.NAME_TYPE, 
            ValueString.class
            );
      registerStateAbstract(
            InterpolatorSnapshotTable.NAME_PATH, 
            ValueString.class
            );
      registerStateAbstract(
            InterpolatorSnapshotTable.NAME_DELIMITER, 
            ValueString.class
            );
      registerStateAbstract(
            InterpolatorSnapshotTable.DEFAULT_NAME_HEADER,
            ValueString.class
            );
   }

   @Override
   public void addProcessors() 
   {
      super.addProcessors();
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInterpolate.getValueClass()
            );
   }

}
