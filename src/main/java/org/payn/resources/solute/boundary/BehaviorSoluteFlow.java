package org.payn.resources.solute.boundary;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteAdvection;
import org.payn.resources.solute.boundary.flow.SoluteDispersion;

/**
 * Behavior for aqueous solute movement between aqueous storage cells
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteFlow extends BehaviorAbstract {

   @Override
   public void addRequiredStates() 
   {
      addRequiredState(ResourceSolute.NAME_DISPERSION_COEFF, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_WATER_FLOW, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_LENGTH, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_AREA_XSECT, ValueDouble.class);
   }

   @Override
   public void addProcessors() 
   {
      addAbstractProcessor(
            ResourceSolute.NAME_ADVECTION, 
            SoluteAdvection.class, 
            SoluteAdvection.getValueClass()
            );
      addAbstractProcessor(
            ResourceSolute.NAME_DISPERSION, 
            SoluteDispersion.class, 
            SoluteDispersion.getValueClass()
            );
   }

}
