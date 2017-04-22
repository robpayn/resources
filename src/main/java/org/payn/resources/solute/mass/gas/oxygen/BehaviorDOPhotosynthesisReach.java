package org.payn.resources.solute.mass.gas.oxygen;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.io.interpolate.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior controlling the effect of photosynthesis on oxygen for a stream reach
 * 
 * @author v78h241
 *
 */
public class BehaviorDOPhotosynthesisReach extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(
            ResourceSolute.DEFAULT_NAME_PAR, 
            PAR.class, 
            PAR.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {
      registerState(
            "PAR" + InterpolatorSnapshotTable.NAME_PATH, 
            ValueString.class
            );
      registerState(
            "PAR" + InterpolatorSnapshotTable.NAME_TYPE, 
            ValueString.class
            );
      registerState(
            "PAR" + InterpolatorSnapshotTable.NAME_DELIMITER, 
            ValueString.class
            );
      registerState(
            ResourceSolute.DEFAULT_NAME_DO_PTOPAR_RATIO,
            ValueDouble.class
            );
   }

}
