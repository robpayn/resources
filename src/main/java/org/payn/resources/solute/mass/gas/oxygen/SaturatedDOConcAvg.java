package org.payn.resources.solute.mass.gas.oxygen;

import org.payn.chsm.finitedifference.processors.ProcessorDoubleInfoInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Calculates the average saturated concentration over a reach
 * 
 * @author robpayn
 *
 */
public class SaturatedDOConcAvg extends ProcessorDoubleInfoInit {

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
            ResourceSolute.DEFAULT_NAME_DO_SAT_CONC_UPSTREAM
            );
      saturatedConcDownstream = (ValueDouble)createDependencyOnValue(
            ResourceSolute.DEFAULT_NAME_DO_SAT_CONC_DOWNSTREAM
            );
   }

   @Override
   public void update() throws Exception 
   {
      value.n = 0.5 * (saturatedConcUpstream.n + saturatedConcDownstream.n);
   }

   @Override
   public void setInitDependencies() throws Exception 
   {
      setUpdateDependencies();
   }

   @Override
   public void initialize() throws Exception 
   {
      update();
   }

}
