package org.payn.resources.solute.otis.boundary;

import org.payn.chsm.processors.auto.ProcessorDoubleInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public class SoluteConc extends ProcessorDoubleInit {

   private ValueDouble adjacentConc;

   @Override
   public void setInitDependencies() throws Exception 
   {
      adjacentConc = (ValueDouble)createDependency(
            ((HolonBoundary)getState().getParentHolon()).getAdjacentBoundary().getCell(),
            getResourceName() + ResourceSoluteOTIS.NAME_SOLUTE_CONC
            );
   }

   @Override
   public void initialize() throws Exception 
   {
      value = adjacentConc;
   }

}
