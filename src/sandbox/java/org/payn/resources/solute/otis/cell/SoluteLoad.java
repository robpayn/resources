package org.payn.resources.solute.otis.cell;

import java.util.ArrayList;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorLoadDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public abstract class SoluteLoad extends ProcessorLoadDouble {

   protected ValueDouble upstreamConc;
   protected ValueDouble downstreamConc;

   protected void setConcentrations() throws Exception 
   {
      HolonCell holon = (HolonCell)getState().getParentHolon();
      ArrayList<HolonBoundary> boundaries = holon.getBoundaries(
            getResourceName() + "." + ResourceSoluteOTIS.BEHAVIOR_STORAGE_CONC
            );
      for (HolonBoundary boundary: boundaries)
      {
         ValueDouble flow = (ValueDouble)boundary.getState(
               BehaviorSoluteConcOTIS.REQ_STATE_FLOW
               ).getValue();
         if (flow.n > 0)
         {
            upstreamConc = (ValueDouble)createDependency(
                  boundary, 
                  getResourceName() + ResourceSolute.NAME_SOLUTE_CONC
                  ).getValue();
         }
         else
         {
            downstreamConc = (ValueDouble)createDependency(
                  boundary, 
                  getResourceName() + ResourceSolute.NAME_SOLUTE_CONC
                  ).getValue();
         }
      }
   }

}
