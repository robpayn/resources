package org.payn.resources.solute.cell.otis;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * A behavior for calculating advection and dispersion of a conservative
 * tracer based on concentration calculations
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteConcOTIS extends BehaviorAbstract {

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(ResourceSolute.NAME_WATER_FLOW, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_AREA_XSECT, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_LENGTH, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_DISPERSION_COEFF, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_UPTAKE_MAX, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_CONC_HALF_SAT, ValueDouble.class);
      addRequiredState(ResourceSolute.NAME_DEPTH, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      addAbstractProcessor(ResourceSolute.NAME_SOLUTE_CONC, SoluteConc.class, SoluteConc.getValueClass());
      addAbstractProcessor(ResourceSolute.NAME_ADVECTION, SoluteAdvection.class, SoluteAdvection.getValueClass());
      addAbstractProcessor(ResourceSolute.NAME_DISPERSION, SoluteDispersion.class, SoluteDispersion.getValueClass());
   }

}
