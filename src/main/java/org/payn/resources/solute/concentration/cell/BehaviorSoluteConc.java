package org.payn.resources.solute.concentration.cell;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * A behavior for calculating advection and dispersion of a conservative
 * tracer based on concentration calculations
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteConc extends BehaviorAbstract {

   @Override
   protected void registerStates() 
   {
      registerState(ResourceSolute.NAME_WATER_FLOW, ValueDouble.class);
      registerState(ResourceSolute.NAME_AREA_XSECT, ValueDouble.class);
      registerState(ResourceSolute.NAME_LENGTH, ValueDouble.class);
      registerState(ResourceSolute.NAME_DISPERSION_COEFF, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessorAbstract(ResourceSolute.NAME_SOLUTE_CONC, SoluteConc.class, SoluteConc.getValueClass());
      addProcessorAbstract(ResourceSolute.NAME_ADVECTION, SoluteAdvection.class, SoluteAdvection.getValueClass());
      addProcessorAbstract(ResourceSolute.NAME_DISPERSION, SoluteDispersion.class, SoluteDispersion.getValueClass());
   }

}
