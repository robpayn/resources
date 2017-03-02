package org.payn.resources.solute.otis.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public class BehaviorSoluteConcOTIS extends BehaviorMatrix {

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(ResourceSoluteOTIS.NAME_WATER_FLOW, ValueDouble.class);
      addRequiredState(ResourceSoluteOTIS.NAME_AREA_XSECT, ValueDouble.class);
      addRequiredState(ResourceSoluteOTIS.NAME_LENGTH, ValueDouble.class);
      addRequiredState(ResourceSoluteOTIS.NAME_DISPERSION_COEFF, ValueDouble.class);
      addRequiredState(ResourceSoluteOTIS.NAME_UPTAKE_MAX, ValueDouble.class);
      addRequiredState(ResourceSoluteOTIS.NAME_CONC_HALF_SAT, ValueDouble.class);
      addRequiredState(ResourceSoluteOTIS.NAME_DEPTH, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceSoluteOTIS.NAME_SOLUTE_CONC, SoluteConc.class, SoluteConc.getValueClass());
      addProcessor(ResourceSoluteOTIS.NAME_ADVECTION, SoluteAdvection.class, SoluteConc.getValueClass());
      addProcessor(ResourceSoluteOTIS.NAME_DISPERSION, SoluteDispersion.class, SoluteConc.getValueClass());
   }

}
