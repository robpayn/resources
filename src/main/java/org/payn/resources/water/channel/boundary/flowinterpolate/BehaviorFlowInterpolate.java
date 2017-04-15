package org.payn.resources.water.channel.boundary.flowinterpolate;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.io.interpolate.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueString;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for water at the downstream end of a dynamic wave channel
 * 
 * @author robpayn
 *
 */
public class BehaviorFlowInterpolate extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceWater.DEFAULT_NAME_FLOW, WaterFlow.class, WaterFlow.getValueClass());
   }

   @Override
   protected void registerStates() 
   {
      registerState(
            InterpolatorSnapshotTable.NAME_DELIMITER, 
            ValueString.class
            );
      registerState(
            InterpolatorSnapshotTable.DEFAULT_NAME_HEADER,
            ValueString.class
            );
      registerState(
            InterpolatorSnapshotTable.NAME_PATH, 
            ValueString.class
            );
      registerState(
            InterpolatorSnapshotTable.NAME_TYPE, 
            ValueString.class
            );
   }

}
