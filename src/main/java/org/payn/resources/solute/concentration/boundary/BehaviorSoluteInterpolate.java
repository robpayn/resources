package org.payn.resources.solute.concentration.boundary;

import org.payn.chsm.io.inputters.InterpolatorSnapshotTable;
import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.boundary.flow.SoluteConcInterpolate;

/**
 * A behavior for boundary concentrations based on interpolated
 * data from a table
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteInterpolate extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInterpolate.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {
      registerState(
            ResourceSolute.NAME_WATER_FLOW, 
            ValueDouble.class
            );
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

}
