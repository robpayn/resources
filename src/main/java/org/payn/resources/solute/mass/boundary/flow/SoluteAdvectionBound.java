package org.payn.resources.solute.mass.boundary.flow;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.ResourceSolute;

/**
 * Advection of solute at a flow-controlled boundary
 * 
 * @author v78h241
 *
 */
public class SoluteAdvectionBound extends ProcessorDoubleLoad {

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
   public void setUpdateDependenciesDelta() throws Exception 
   {
      extConc = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      HolonCell cell = ((HolonBoundary)getState().getParentHolon()).getCell();
      concLocal = (ValueDouble)createAbstractDependency(
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
         value.n = extConc.n * waterFlow.n;
      }
   }

}
