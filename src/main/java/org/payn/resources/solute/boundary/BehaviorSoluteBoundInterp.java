package org.payn.resources.solute.boundary;

import org.payn.chsm.io.file.interpolate.InterpolatorSnapshotTable;
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
   public void addRequiredStates() 
   {
      super.addRequiredStates();
      addAbstractRequiredState(
            InterpolatorSnapshotTable.REQ_STATE_TYPE, 
            ValueString.class
            );
      addAbstractRequiredState(
            InterpolatorSnapshotTable.REQ_STATE_PATH, 
            ValueString.class
            );
      addAbstractRequiredState(
            InterpolatorSnapshotTable.REQ_STATE_DELIMITER, 
            ValueString.class
            );
   }

   @Override
   public void addProcessors() 
   {
      super.addProcessors();
      addAbstractProcessor(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInterpolate.getValueClass()
            );
   }

}
