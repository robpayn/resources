package org.payn.resources.solute;

import org.payn.chsm.ResourceAbstract;
import org.payn.resources.solute.boundary.BehaviorSoluteActiveMM;
import org.payn.resources.solute.boundary.BehaviorSoluteBoundInject;
import org.payn.resources.solute.boundary.BehaviorSoluteBoundInterp;
import org.payn.resources.solute.boundary.BehaviorSoluteConcInterp;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;
import org.payn.resources.solute.boundary.BehaviorSoluteFlowBound;
import org.payn.resources.solute.cell.BehaviorSoluteStorage;

/**
 * The solute currency
 * 
 * @author v78h241
 *
 */
public class ResourceSolute extends ResourceAbstract {
   
   /**
    * Name for state with total mass of solute to be injected
    */
   public static String NAME_INJECT_MASS = "SoluteMass";
   
   /**
    * Name for state with duration (number of intervals) of injection
    */
   public static String NAME_INJECT_DURATION = "DurationTicks";
   
   /**
    * Name for state with the first interval of the injection 
    */
   public static String NAME_INJECT_START = "StartTick";
   
   /**
    * Name for the water volume
    */
   public static String NAME_WATER_VOLUME = "WaterVolume";
   
   /**
    * Name for the water depth
    */
   public static String NAME_DEPTH = "Depth";
   
   /**
    * Name for required state of maximum uptake
    */
   public static String NAME_UPTAKE_MAX = "UMax";
   
   /**
    * Name for required state of half saturation concentration
    */
   public static String NAME_CONC_HALF_SAT = "HalfSatConc";
   
   /**
    * Name for required state of plan area
    */
   public static String NAME_PLANAREA = "WettedArea";
   
   /**
    * Name of concentration states
    */
   public static final String NAME_SOLUTE_CONC = "Conc";

   /**
    * Name for the background concentration to maintain
    */
   public static String NAME_BKG_CONC = "Bkg" + NAME_SOLUTE_CONC;
   
   /**
    * Name of required state for dispersion coefficient
    */
   public static String NAME_DISPERSION_COEFF = "DispCoeff";
   
   /**
    * Name of required state for length of flow path
    */
   public static String NAME_LENGTH = "Length";
   
   /**
    * Name of required state for cross-sectional area of flow path
    */
   public static String NAME_AREA_XSECT = "AreaXSect";
   
   /**
    * Name of required state for water flow
    */
   public static String NAME_WATER_FLOW = "WaterFlow";

   /**
    * Name of the solute load state
    */
   public static final String NAME_SOLUTE_LOAD = "Load";
   
   /**
    * Name of the solute storage state
    */
   public static final String NAME_SOLUTE_STORAGE = "Storage";
   
   /**
    * Name of advection states
    */
   public static final String NAME_ADVECTION = "Advect";

   /**
    * Name of dispersion states
    */
   public static final String NAME_DISPERSION = "Dispersion";
   
   /**
    * Name of the state for uptake
    */
   public static final String NAME_UPTAKE = "Uptake";

   /**
    * Name of storage behavior
    */
   public static final String BEHAVIOR_STORAGE = "storage";
   
   /**
    * Storage behavior with saturating uptake kinetic (hyperbolic)
    */
   public static final String BEHAVIOR_STORAGE_HYPER = "storagehyper";
   
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
   
   /**
    * Behavior for interpolating concentration from a file
    */
   public static final String BEHAVIOR_CONC_INTERP = "concinterp";


   @Override
   public void addBehaviors() 
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
      addBehavior(
            BEHAVIOR_CONC_INTERP,
            BehaviorSoluteConcInterp.class.getCanonicalName()
            );
   }

}
