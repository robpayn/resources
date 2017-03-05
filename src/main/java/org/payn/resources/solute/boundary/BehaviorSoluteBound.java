package org.payn.resources.solute.boundary;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteAdvectionBound;
import org.payn.resources.solute.boundary.flow.SoluteDispersionBound;

/**
 * Basic advection-dispersion boundary based on interpolated concentration input data
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteBound extends BehaviorAbstract {

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
            SoluteAdvectionBound.class, 
            SoluteAdvectionBound.getValueClass()
            );
      addAbstractProcessor(
            ResourceSolute.NAME_DISPERSION, 
            SoluteDispersionBound.class, 
            SoluteDispersionBound.getValueClass()
            );
   }

}
