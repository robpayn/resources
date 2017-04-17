package org.payn.resources.solute.gas;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleState;
import org.payn.resources.solute.ResourceSolute;

/**
 * Calculates the average saturated concentration over a reach
 * 
 * @author robpayn
 *
 */
public class SaturatedConcAvg extends ProcessorDoubleState {

   /**
    * Upstream saturated concentration
    */
   private ValueDouble saturatedConcUpstream;
   
   /**
    * Downstream saturated concentration
    */
   private ValueDouble saturatedConcDownstream;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      saturatedConcUpstream = (ValueDouble)createDependencyOnValue(
            ResourceSolute.DEFAULT_NAME_SAT_CONC_UPSTREAM
            );
      saturatedConcDownstream = (ValueDouble)createDependencyOnValue(
            ResourceSolute.DEFAULT_NAME_SAT_CONC_DOWNSTREAM
            );
   }

   @Override
   public void update() throws Exception 
   {
      value.n = 0.5 * (saturatedConcUpstream.n + saturatedConcDownstream.n);
   }

}
