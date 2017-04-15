package org.payn.resources.water.channel.temperature;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.io.interpolate.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueString;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for calcualting a reach average temperatuer from upstream
 * and downstream interpolated temperatures from input data
 * 
 * @author robpayn
 *
 */
public class BehaviorReachAvgTemperatureBound extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(
            ResourceWater.DEFAULT_NAME_UPSTREAM_TEMP,
            WaterTempUpstream.class, 
            WaterTempUpstream.getValueClass()
            );
      addProcessor(
            ResourceWater.DEFAULT_NAME_DOWNSTREAM_TEMP,
            WaterTempDownstream.class, 
            WaterTempDownstream.getValueClass()
            );
      addProcessor(
            ResourceWater.DEFAULT_NAME_AVG_TEMP,
            WaterTempAvg.class, 
            WaterTempAvg.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {
      registerState(
            "UpstreamTemp" + InterpolatorSnapshotTable.NAME_PATH, 
            ValueString.class
            );
      registerState(
            "DownstreamTemp" + InterpolatorSnapshotTable.NAME_PATH, 
            ValueString.class
            );
      registerState(
            "Temp" + InterpolatorSnapshotTable.NAME_TYPE, 
            ValueString.class
            );
      registerState(
            "Temp" + InterpolatorSnapshotTable.NAME_DELIMITER, 
            ValueString.class
            );
   }

}
