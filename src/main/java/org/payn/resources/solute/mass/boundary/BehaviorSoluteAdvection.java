package org.payn.resources.solute.mass.boundary;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.boundary.flow.SoluteAdvection;

/**
 * Boundary behavior for simulating advection of a solute
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteAdvection extends BehaviorAbstract {

   @Override
   protected void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_ADVECTION, 
            SoluteAdvection.class, 
            SoluteAdvection.getValueClass()
            );
   }

   @Override
   protected void registerStates() 
   {
      registerState(ResourceSolute.NAME_WATER_FLOW, ValueDouble.class);
   }

}
