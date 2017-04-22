package org.payn.resources.solute.concentration.cell;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior of an active solute
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteConcHyper extends BehaviorAbstract {
   
   @Override
   protected void registerStates() 
   {
      registerStateAbstract(ResourceSolute.NAME_UPTAKE_MAX, ValueDouble.class);
      registerStateAbstract(ResourceSolute.NAME_CONC_HALF_SAT, ValueDouble.class);
      registerStateAbstract(ResourceSolute.NAME_BKG_CONC, ValueDouble.class);
      registerState(ResourceSolute.NAME_DEPTH, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_UPTAKE, 
            SoluteUptake.class, 
            SoluteUptake.getValueClass()
            );
   }
   
}
