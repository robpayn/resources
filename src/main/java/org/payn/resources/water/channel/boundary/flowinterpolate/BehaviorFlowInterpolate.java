package org.payn.resources.water.channel.boundary.flowinterpolate;

import org.payn.chsm.io.file.interpolate.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueString;
import org.payn.neoch.BehaviorMatrix;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for water at the downstream end of a dynamic wave channel
 * 
 * @author robpayn
 *
 */
public class BehaviorFlowInterpolate extends BehaviorMatrix {

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceWater.NAME_WATER_FLOW, WaterFlow.class, WaterFlow.getValueClass());
   }

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(
            InterpolatorSnapshotTable.REQ_STATE_DELIMITER, 
            ValueString.class
            );
      addRequiredState(
            InterpolatorSnapshotTable.REQ_STATE_PATH, 
            ValueString.class
            );
      addRequiredState(
            InterpolatorSnapshotTable.REQ_STATE_TYPE, 
            ValueString.class
            );
   }

}
