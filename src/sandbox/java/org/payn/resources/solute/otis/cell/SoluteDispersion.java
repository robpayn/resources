package org.payn.resources.solute.otis.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public class SoluteDispersion extends SoluteLoad {

   private ValueDouble length;
   private ValueDouble dispersion;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      dispersion = (ValueDouble)createDependency(
            ResourceSoluteOTIS.NAME_DISPERSION_COEFF
            ).getValue();
      length = (ValueDouble)createDependency(
            ResourceSoluteOTIS.NAME_LENGTH
            ).getValue();
      setConcentrations();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = (dispersion.n * (downstreamConc.n + upstreamConc.n)) / (length.n * length.n);
   }

}
