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
      addProcessor(ResourceWater.NAME_WATER_FLOW, WaterFlow.class, WaterFlow.getValueClass());
   }

   @Override
   protected void registerStates() 
   {
      registerState(
            InterpolatorSnapshotTable.REQ_STATE_DELIMITER, 
            ValueString.class
            );
      registerState(
            InterpolatorSnapshotTable.REQ_STATE_PATH, 
            ValueString.class
            );
      registerState(
            InterpolatorSnapshotTable.REQ_STATE_TYPE, 
            ValueString.class
            );
   }

}
