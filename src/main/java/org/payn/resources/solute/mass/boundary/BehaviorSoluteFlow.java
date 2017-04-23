package org.payn.resources.solute.mass.boundary;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.boundary.flow.SoluteAdvection;
import org.payn.resources.solute.mass.boundary.flow.SoluteDispersion;

/**
 * Behavior for aqueous solute movement between aqueous storage cells
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteFlow extends BehaviorAbstract {

   @Override
   public void registerStates() 
   {
      registerState(ResourceSolute.NAME_DISPERSION_COEFF, ValueDouble.class);
      registerState(ResourceSolute.NAME_WATER_FLOW, ValueDouble.class);
      registerState(ResourceSolute.NAME_LENGTH, ValueDouble.class);
      registerState(ResourceSolute.NAME_AREA_XSECT, ValueDouble.class);
   }

   @Override
   public void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_ADVECTION, 
            SoluteAdvection.class, 
            SoluteAdvection.getValueClass()
            );
      addProcessorAbstract(
            ResourceSolute.NAME_DISPERSION, 
            SoluteDispersion.class, 
            SoluteDispersion.getValueClass()
            );
   }

}
