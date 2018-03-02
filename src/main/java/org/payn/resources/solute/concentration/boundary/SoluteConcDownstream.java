package org.payn.resources.solute.concentration.boundary;

import org.payn.chsm.processors.finitedifference.ProcessorDoubleInfoInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.resources.solute.ResourceSolute;

public class SoluteConcDownstream extends ProcessorDoubleInfoInit {

   private ValueDouble upstreamConc;

   @Override
   public void setInitDependencies() throws Exception 
   {
      setUpdateDependencies();
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      HolonCell upstreamCell = 
            ((HolonBoundary)getState().getParentHolon()).getCell();
      upstreamConc = (ValueDouble)createAbstractDependency(
            upstreamCell, 
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
   }

   @Override
   public void initialize() throws Exception 
   {
      update();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = upstreamConc.n;
   }

}
