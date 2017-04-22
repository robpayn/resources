package org.payn.resources.solute.mass.boundary.flow;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorDoubleLoadSymmetric;
import org.payn.resources.solute.ResourceSolute;

/**
 * Processor to calculate the advection of solute
 * 
 * @author v78h241
 *
 */
public class SoluteAdvection extends ProcessorDoubleLoadSymmetric {

   /**
    * Value of volumetric water flow
    */
   private ValueDouble waterFlow;
   
   /**
    * Value of local cell concentration
    */
   private ValueDouble concLocal;

   /**
    * Value of adjacent cell concentration
    */
   private ValueDouble concAdjacent;

   @Override
   public void setUpdateDependenciesDelta() throws Exception 
   {
      HolonCell cell = ((HolonBoundary)getState().getParentHolon()).getCell();
      concLocal = (ValueDouble)createAbstractDependency(
            cell, 
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      cell = ((HolonBoundary)getState().getParentHolon()).getAdjacentBoundary().getCell();
      concAdjacent = (ValueDouble)createAbstractDependency(
            cell, 
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      waterFlow = (ValueDouble)createDependency(
            ResourceSolute.NAME_WATER_FLOW
            ).getValue();
   }

   @Override
   public void updateDelta() 
   {
      if (waterFlow.n <= 0)
      {
         value.n = concLocal.n * waterFlow.n;
      }
      else
      {
         value.n = concAdjacent.n * waterFlow.n;
      }
   }

}
