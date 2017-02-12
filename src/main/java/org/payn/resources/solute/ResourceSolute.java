package org.payn.resources.solute;

import org.payn.chsm.Resource;
import org.payn.resources.solute.boundary.BehaviorSoluteActiveMM;
import org.payn.resources.solute.boundary.BehaviorSoluteBoundInject;
import org.payn.resources.solute.boundary.BehaviorSoluteBoundInterp;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;
import org.payn.resources.solute.boundary.BehaviorSoluteFlowBound;
import org.payn.resources.solute.cell.BehaviorSoluteStorage;

/**
 * The solute currency
 * 
 * @author v78h241
 *
 */
public class ResourceSolute extends Resource {
   
   /**
    * Name of the solute load state
    */
   public static final String NAME_SOLUTE_LOAD = "Load";
   
   /**
    * Name of the solute storage state
    */
   public static final String NAME_SOLUTE_STORAGE = "Storage";
   
   /**
    * Name of concentration states
    */
   public static final String NAME_SOLUTE_CONC = "Conc";

   /**
    * Name of advection states
    */
   public static final String NAME_ADVECTION = "Advect";

   /**
    * Name of dispersion states
    */
   public static final String NAME_DISPERSION = "Dispersion";
   
   /**
    * Name of storage behavior
    */
   public static final String BEHAVIOR_STORAGE = "storage";
   
   /**
    * Name of flow behavior
    */
   public static final String BEHAVIOR_FLOW = "flow";
   
   /**
    * Name of flow boundary condition behavior
    */
   public static final String BEHAVIOR_FLOWBOUND = "flowbound";
   
   /**
    * Name of boundary condition for concentration
    */
   public static final String BEHAVIOR_CONCBOUND = "concbound";

   /**
    * Name of boundary for injected concentration
    */
   public static final String BEHAVIOR_CONCBOUND_INJECT = "concinject";

   /**
    * Name of Michaelis Menten uptake behavior
    */
   public static final String BEHAVIOR_ACTIVEMM = "activemm";


   @Override
   protected void addBehaviors() 
   {
      addBehavior(
            BEHAVIOR_STORAGE, 
            BehaviorSoluteStorage.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_FLOW, 
            BehaviorSoluteFlow.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_FLOWBOUND, 
            BehaviorSoluteFlowBound.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_CONCBOUND, 
            BehaviorSoluteBoundInterp.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_CONCBOUND_INJECT,
            BehaviorSoluteBoundInject.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_ACTIVEMM, 
            BehaviorSoluteActiveMM.class.getCanonicalName()
            );
   }

}
