package org.payn.resources.solute.gas.oxygen;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.gas.AWExchangeVelocity;

/**
 * Behavior for calculating the air-water gas exchange velocity from
 * the temperature
 * 
 * @author robpayn
 *
 */
public class BehaviorDOAWExchangeReach extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.DEFAULT_NAME_AW_EXCH_VELOCITY, 
            AWExchangeVelocity.class, 
            AWExchangeVelocity.getValueClass()
            );
      addProcessor(
            ResourceSolute.DEFAULT_NAME_DO_SAT_CONC_UPSTREAM, 
            SaturatedDOConcUpstream.class, 
            SaturatedDOConcUpstream.getValueClass()
            );
      addProcessor(
            ResourceSolute.DEFAULT_NAME_DO_SAT_CONC_DOWNSTREAM, 
            SaturatedDOConcDownstream.class, 
            SaturatedDOConcDownstream.getValueClass()
            );
      addProcessor(
            ResourceSolute.DEFAULT_NAME_DO_SAT_CONC, 
            SaturatedDOConcAvg.class, 
            SaturatedDOConcAvg.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {
      registerState(
            ResourceSolute.DEFAULT_NAME_AIR_PRESSURE, 
            ValueDouble.class
            );
      registerStateAbstract(
            ResourceSolute.DEFAULT_NAME_K600, 
            ValueDouble.class
            );
   }

}