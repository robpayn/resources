package org.payn.resources.solute.mass.boundary;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.boundary.active.SoluteUptakeMM;

/**
 * Behavior of solute at a flow-controlled boundary
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteActiveMM extends BehaviorAbstract {

   @Override
   public void registerStates() 
   {
      registerStateAbstract(ResourceSolute.NAME_UPTAKE_MAX, ValueDouble.class);
      registerStateAbstract(ResourceSolute.NAME_CONC_HALF_SAT, ValueDouble.class);
      registerStateAbstract(ResourceSolute.NAME_BKG_CONC, ValueDouble.class);
      
      registerState(ResourceSolute.NAME_PLANAREA, ValueDouble.class);
   }

   @Override
   public void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_LOAD, 
            SoluteUptakeMM.class, 
            SoluteUptakeMM.getValueClass()
            );
   }

}
