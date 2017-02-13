package org.payn.resources.solute.boundary.flow;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorLoadDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;

/**
 * Processor to calculate the advection of solute
 * 
 * @author v78h241
 *
 */
public class SoluteAdvection extends ProcessorLoadDouble {

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
   public void setUpdateDependencies() throws Exception 
   {
      HolonCell cell = ((HolonBoundary)getState().getParentHolon()).getCell();
      concLocal = (ValueDouble)createDependency(
            cell, 
            getResourceName() + ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      cell = ((HolonBoundary)getState().getParentHolon()).getAdjacentBoundary().getCell();
      concAdjacent = (ValueDouble)createDependency(
            cell, 
            getResourceName() + ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      waterFlow = (ValueDouble)createDependency(BehaviorSoluteFlow.REQ_STATE_FLOW).getValue();
   }

   @Override
   public void update() 
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
