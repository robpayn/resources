package org.payn.resources.solute.otis;

import org.payn.chsm.Resource;
import org.payn.resources.solute.otis.boundary.BehaviorSoluteFlowOTIS;
import org.payn.resources.solute.otis.cell.BehaviorSoluteConcOTIS;

public class ResourceSoluteOTIS extends Resource {

   public static final String BEHAVIOR_STORAGE_CONC = "storage";
   
   public static final String BEHAVIOR_FLOW_CONC = "flow";
   
   public static final String NAME_SOLUTE_CONC = "Conc";

   public static final String NAME_ADVECTION = "Advect";

   public static final String NAME_DISPERSION = "Dispersion";

   @Override
   protected void addBehaviors() 
   {
      addBehavior(
            BEHAVIOR_STORAGE_CONC, 
            BehaviorSoluteConcOTIS.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_FLOW_CONC, 
            BehaviorSoluteFlowOTIS.class.getCanonicalName()
            );
   }

}
