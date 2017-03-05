package org.payn.resources.solute.boundary.otis;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.io.file.interpolate.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueString;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteConcInject;
import org.payn.resources.solute.boundary.flow.SoluteConcInterpolate;

/**
 * Behavior for an artificial tracer solute injection for a one-dimensional
 * model based on concentration calculations
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteInjectOTIS extends BehaviorAbstract {

   @Override
   public void addProcessors() 
   {
      addAbstractProcessor(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInject.class, 
            SoluteConcInject.getValueClass()
            );
      addAbstractProcessor(
            ResourceSolute.NAME_BKG_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInject.getValueClass()
            );
   }

   @Override
   public void addRequiredStates() 
   {
      addRequiredState(
            ResourceSolute.NAME_WATER_FLOW, 
            ValueDouble.class
            );
      addAbstractRequiredState(
            ResourceSolute.NAME_INJECT_MASS, 
            ValueDouble.class
            );
      addAbstractRequiredState(
            ResourceSolute.NAME_INJECT_START, 
            ValueLong.class
            );
      addAbstractRequiredState(
            ResourceSolute.NAME_INJECT_DURATION, 
            ValueLong.class
            );
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

}
