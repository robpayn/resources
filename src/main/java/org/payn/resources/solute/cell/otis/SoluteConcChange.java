package org.payn.resources.solute.cell.otis;

import org.payn.chsm.State;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.neoch.processors.ProcessorDoubleStorage;
import org.payn.resources.solute.ResourceSolute;

/**
 * An abstract processor for calculating a change in concentration
 * based on upstream and downstream concentrations
 * 
 * @author robpayn
 *
 */
public abstract class SoluteConcChange extends ProcessorDoubleLoad {

   /**
    * Upstream concentration
    */
   protected ValueDouble upstreamConc;
   
   /**
    * Downstream concentration
    */
   protected ValueDouble downstreamConc;

   @Override
   public void setUpdateDependencies() throws Exception
   {
      setUpdateDependenciesLoad();
      State storage = ((HolonCell)state.getParentHolon()).getStorage(
            state.getBehavior().getResource());
      storageProcessor = (ProcessorDoubleStorage)storage.getProcessor();
   }

   /**
    * Set the upstream and downstream concentrations
    * 
    * @throws Exception
    */
   protected void setConcentrations() throws Exception 
   {
      HolonCell holon = (HolonCell)getState().getParentHolon();
      for (HolonBoundary boundary: holon.getBoundaryMap().values())
      {
         ValueDouble flow = (ValueDouble)boundary.getState(
               ResourceSolute.NAME_WATER_FLOW
               ).getValue();
         if (flow.n > 0)
         {
            if (boundary.getAdjacentBoundary() != null)
            {
               upstreamConc = (ValueDouble)createAbstractDependency(
                     boundary.getAdjacentBoundary().getCell(), 
                     ResourceSolute.NAME_SOLUTE_CONC
                     ).getValue();
            }
            else
            {
               upstreamConc = (ValueDouble)createAbstractDependency(
                     boundary, 
                     ResourceSolute.NAME_SOLUTE_CONC
                     ).getValue();
            }
         }
         else
         {
            if (boundary.getAdjacentBoundary() != null)
            {
               downstreamConc = (ValueDouble)createAbstractDependency(
                     boundary.getAdjacentBoundary().getCell(), 
                     ResourceSolute.NAME_SOLUTE_CONC
                     ).getValue();
            }
            else
            {
               downstreamConc = (ValueDouble)createAbstractDependency(
                     boundary, 
                     ResourceSolute.NAME_SOLUTE_CONC
                     ).getValue();
            }
         }
      }
   }

}
