package org.payn.resources.solute.boundary;

import org.payn.chsm.io.file.interpolate.ProcessorInterpolateSnapshotTable;
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
   protected void addRequiredStates() 
   {
      super.addRequiredStates();
      addRequiredState(
            resource.getName() + ProcessorInterpolateSnapshotTable.REQ_STATE_TYPE, 
            ValueString.class
            );
      addRequiredState(
            resource.getName() + ProcessorInterpolateSnapshotTable.REQ_STATE_PATH, 
            ValueString.class
            );
      addRequiredState(
            resource.getName() + ProcessorInterpolateSnapshotTable.REQ_STATE_DELIMITER, 
            ValueString.class
            );
   }

   @Override
   protected void addProcessors() 
   {
      super.addProcessors();
      addProcessor(ResourceSolute.NAME_SOLUTE_CONC, SoluteConcInterpolate.class, SoluteConcInterpolate.getValueClass());
   }

}
