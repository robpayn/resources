package org.payn.resources.solute.boundary.flow;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.BehaviorSoluteFlowBound;

import neoch.HolonBoundary;
import neoch.HolonCell;
import neoch.processors.ProcessorLoadDouble;

/**
 * Advection of solute at a flow-controlled boundary
 * 
 * @author v78h241
 *
 */
public class SoluteFlowBound extends ProcessorLoadDouble {

   /**
    * Value for volumetric water flow
    */
   private ValueDouble waterFlow;
   
   /**
    * Value for local concentration
    */
   private ValueDouble concLocal;

   /**
    * Value for external concentration
    */
   private ValueDouble extConc;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      extConc = (ValueDouble)createDependency(
            getResourceName() + ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      HolonCell cell = ((HolonBoundary)getState().getParentHolon()).getCell();
      concLocal = (ValueDouble)createDependency(
            cell, 
            getResourceName() + ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      waterFlow = (ValueDouble)createDependency(BehaviorSoluteFlowBound.REQ_STATE_FLOW).getValue();
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
         value.n = extConc.n * waterFlow.n;
      }
   }

}
