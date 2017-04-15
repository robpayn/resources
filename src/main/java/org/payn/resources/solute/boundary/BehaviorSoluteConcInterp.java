package org.payn.resources.solute.boundary;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.io.interpolate.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueString;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteConcInterpolate;

/**
 * A boundary condition based on a solute concentration read from an input file
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteConcInterp extends BehaviorAbstract {

   @Override
   public void registerStates() 
   {
      registerStateAbstract(
            InterpolatorSnapshotTable.NAME_TYPE, 
            ValueString.class
            );
      registerStateAbstract(
            InterpolatorSnapshotTable.DEFAULT_NAME_HEADER,
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
   }

   @Override
   public void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInterpolate.getValueClass()
            );
   }

}
