package org.payn.resources.solute.otis.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public class SoluteAdvection extends SoluteLoad {

   private ValueDouble waterFlow;
   private ValueDouble area;
   private ValueDouble length;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      waterFlow = (ValueDouble)createDependency(
            ResourceSoluteOTIS.NAME_WATER_FLOW
            ).getValue();
      area = (ValueDouble)createDependency(
            ResourceSoluteOTIS.NAME_AREA_XSECT
            ).getValue();
      length = (ValueDouble)createDependency(
            ResourceSoluteOTIS.NAME_LENGTH
            ).getValue();
      
      setConcentrations();
      
   }

   @Override
   public void update() throws Exception 
   {
      value.n = (waterFlow.n / area.n) * ((downstreamConc.n - upstreamConc.n) / (2 * length.n));
   }

}
