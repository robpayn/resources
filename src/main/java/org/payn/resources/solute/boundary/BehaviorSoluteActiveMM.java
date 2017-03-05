package org.payn.resources.solute.boundary;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.active.SoluteUptakeMM;

/**
 * Behavior of solute at a flow-controlled boundary
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteActiveMM extends BehaviorAbstract {

   @Override
   public void addRequiredStates() 
   {
      addAbstractRequiredState(ResourceSolute.NAME_UPTAKE_MAX, ValueDouble.class);
      addAbstractRequiredState(ResourceSolute.NAME_CONC_HALF_SAT, ValueDouble.class);
      addAbstractRequiredState(ResourceSolute.NAME_BKG_CONC, ValueDouble.class);
      
      addRequiredState(ResourceSolute.NAME_PLANAREA, ValueDouble.class);
   }

   @Override
   public void addProcessors() 
   {
      addAbstractProcessor(
            ResourceSolute.NAME_SOLUTE_LOAD, 
            SoluteUptakeMM.class, 
            SoluteUptakeMM.getValueClass()
            );
   }

}
