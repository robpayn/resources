package org.payn.resources.solute.otis.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public class SoluteUptake extends ProcessorDoubleLoad {

   private ValueDouble conc;
   private ValueDouble umax;
   private ValueDouble chalf;
   private ValueDouble depth;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      conc = (ValueDouble)createAbstractDependency(
            ResourceSoluteOTIS.NAME_SOLUTE_CONC
            ).getValue();
      umax = (ValueDouble)createDependency(
            ResourceSoluteOTIS.NAME_UPTAKE_MAX
            ).getValue();
      chalf = (ValueDouble)createDependency(
            ResourceSoluteOTIS.NAME_CONC_HALF_SAT
            ).getValue();
      depth = (ValueDouble)createDependency(
            ResourceSoluteOTIS.NAME_DEPTH
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = ((umax.n * conc.n) / (chalf.n + conc.n)) / depth.n;
   }

}
