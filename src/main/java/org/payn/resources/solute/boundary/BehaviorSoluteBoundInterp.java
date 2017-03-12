package org.payn.resources.solute.boundary;

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
public class BehaviorSoluteBoundInterp extends BehaviorSoluteBound {

   @Override
   public void registerStates() 
   {
      super.registerStates();
      registerStateAbstract(
            InterpolatorSnapshotTable.REQ_STATE_TYPE, 
            ValueString.class
            );
      registerStateAbstract(
            InterpolatorSnapshotTable.REQ_STATE_PATH, 
            ValueString.class
            );
      registerStateAbstract(
            InterpolatorSnapshotTable.REQ_STATE_DELIMITER, 
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
