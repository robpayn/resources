package org.payn.resources.solute.cell.otis;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Behavior of an active solute
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteConcHyperOTIS extends BehaviorSoluteConcOTIS {
   
   @Override
   protected void addRequiredStates() 
   {
      super.addRequiredStates();
      addAbstractRequiredState(ResourceSolute.NAME_UPTAKE_MAX, ValueDouble.class);
      addAbstractRequiredState(ResourceSolute.NAME_CONC_HALF_SAT, ValueDouble.class);
      addAbstractRequiredState(ResourceSolute.NAME_BKG_CONC, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_DEPTH, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      super.addProcessors();
      addAbstractProcessor(
            ResourceSolute.NAME_UPTAKE, 
            SoluteUptake.class, 
            SoluteUptake.getValueClass()
            );
   }
   
}
