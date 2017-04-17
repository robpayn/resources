package org.payn.resources.solute.gas;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior for calculating the air-water gas exchange velocity from
 * the temperature
 * 
 * @author robpayn
 *
 */
public class BehaviorAWExchangeBound extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessor(
            ResourceSolute.DEFAULT_NAME_AW_EXCH_VELOCITY, 
            AWExchangeVelocity.class, 
            AWExchangeVelocity.getValueClass()
            );
      addProcessor(
            ResourceSolute.DEFAULT_NAME_SAT_CONC_UPSTREAM, 
            SaturatedConcUpstream.class, 
            SaturatedConcUpstream.getValueClass()
            );
      addProcessor(
            ResourceSolute.DEFAULT_NAME_SAT_CONC_DOWNSTREAM, 
            SaturatedConcDownstream.class, 
            SaturatedConcDownstream.getValueClass()
            );
      addProcessor(
            ResourceSolute.DEFAULT_NAME_SAT_CONC, 
            SaturatedConcAvg.class, 
            SaturatedConcAvg.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {
      registerState(
            ResourceSolute.DEFAULT_NAME_AIR_PRESSURE, 
            ValueDouble.class
            );
      registerState(
            ResourceSolute.DEFAULT_NAME_K600, 
            ValueDouble.class
            );
   }

}
