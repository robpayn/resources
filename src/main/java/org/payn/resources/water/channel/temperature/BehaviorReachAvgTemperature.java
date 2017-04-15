package org.payn.resources.water.channel.temperature;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueString;
import org.payn.resources.water.ResourceWater;

/**
 * Behavior for translating a reach average temperature calculated at 
 * the upstream boundary to a reach 
 * 
 * @author robpayn
 *
 */
public class BehaviorReachAvgTemperature extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(
            ResourceWater.DEFAULT_NAME_TEMP, 
            WaterTemperature.class, 
            WaterTemperature.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {
      registerState(
            ResourceWater.DEFAULT_NAME_AVG_TEMP_HOLON, 
            ValueString.class
            );
   }

}
