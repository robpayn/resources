package org.payn.resources.solute;

import org.payn.chsm.ResourceAbstract;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteActiveMM;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteAdvection;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteBoundInject;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteBoundInterp;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteConcInterp;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteFlow;
import org.payn.resources.solute.mass.boundary.BehaviorSoluteFlowBound;
import org.payn.resources.solute.mass.cell.BehaviorSoluteStorage;
import org.payn.resources.solute.mass.gas.oxygen.BehaviorDOAWExchange;
import org.payn.resources.solute.mass.gas.oxygen.BehaviorDOAWExchangeReach;
import org.payn.resources.solute.mass.gas.oxygen.BehaviorDOPhotosynthesis;
import org.payn.resources.solute.mass.gas.oxygen.BehaviorDOPhotosynthesisReach;
import org.payn.resources.solute.mass.gas.oxygen.BehaviorDORespiration;
import org.payn.resources.solute.mass.gas.oxygen.BehaviorDORespirationReach;
import org.payn.resources.solute.mass.gas.oxygen.DOAWExchange;
import org.payn.resources.solute.mass.gas.oxygen.DOAWExchangeVelocity;
import org.payn.resources.solute.mass.gas.oxygen.DOPhotosynthesis;
import org.payn.resources.solute.mass.gas.oxygen.DORespiration;
import org.payn.resources.solute.mass.gas.oxygen.SaturatedDOConcDownstream;
import org.payn.resources.solute.mass.gas.oxygen.SaturatedDOConcUpstream;

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

   /**
    * Behavior for advection
    */
   public static final String BEHAVIOR_ADVECT = "advection";

   /**
    * Default state name for the Schmidt number
    * adjusted gas exchange velocity
    */
   public static final String DEFAULT_NAME_K600 = "K600";

   /**
    * Default state name for temperature
    */
   public static final String DEFAULT_NAME_TEMP = "Temperature";

   /**
    * Default state name for the air water exchange velocity
    */
   public static final String DEFAULT_NAME_DO_AW_EXCH_VELOCITY = 
         DOAWExchangeVelocity.class.getSimpleName();

   /**
    * Behavior name for air-water gas exchange at the boundary
    */
   public static final String BEHAVIOR_DO_AW_EXCHANGE_BOUND = "doawexchangereach";

   /**
    * Default state name for the upstream temperature
    */
   public static final String DEFAULT_NAME_UPSTREAM_TEMP = "UpstreamTemperature";

   /**
    * Default state name for the upstream water density
    */
   public static final String DEFAULT_NAME_WATER_DENSITY_UPSTREAM = "WaterDensityUpstream";

   /**
    * Default state name for the air pressure
    */
   public static final String DEFAULT_NAME_AIR_PRESSURE = "AirPressure";

   /**
    * Default state name for the saturated concentration conversion factor
    */
   public static final String DEFAULT_NAME_SAT_CONC_CONV = "SatConcConv";

   /**
    * Default state name for the downstream temperature
    */
   public static final String DEFAULT_NAME_DOWNSTREAM_TEMP = "DownstreamTemperature";

   /**
    * Default state name for the downstream water density
    */
   public static final String DEFAULT_NAME_WATER_DENSITY_DOWNSTREAM = "WaterDensityDownstream";

   /**
    * Default state name for the upstream saturated concentration
    */
   public static final String DEFAULT_NAME_DO_SAT_CONC_UPSTREAM = 
         SaturatedDOConcUpstream.class.getSimpleName();

   /**
    * Default state name for the downstream saturated concentration
    */
   public static final String DEFAULT_NAME_DO_SAT_CONC_DOWNSTREAM = 
         SaturatedDOConcDownstream.class.getSimpleName();

   /**
    * Default state name for the saturated concentration
    */
   public static final String DEFAULT_NAME_DO_SAT_CONC = "DOSaturatedConc";

   /**
    * Default state name for the air water gas exchange load
    */
   public static final String DEFAULT_NAME_DO_AW_EXCH = 
         DOAWExchange.class.getSimpleName();

   /**
    * Name of the air water gas exchange behavior
    */
   public static final String BEHAVIOR_DO_AW_EXCHANGE = "doawexchange";

   /**
    * Default state name for the ratio of oxygen production to PAR availability
    */
   public static final String DEFAULT_NAME_DO_PTOPAR_RATIO = "PToPARRatio";

   /**
    * Default state name for photosynthetically active radiation
    */
   public static final String DEFAULT_NAME_PAR = "PAR";

   /**
    * Default state name for DO increase due to photosynthesis
    */
   public static final String DEFAULT_NAME_DO_PHOTOSYNTHESIS = 
         DOPhotosynthesis.class.getSimpleName();

   /**
    * Name of behavior for photsynthesis
    */
   public static final String BEHAVIOR_DO_PHOTOSYNTHESIS = "dophotosynthesis";

   /**
    * Name of behavior of photosynthesis over the reach
    */
   public static final String BEHAVIOR_DO_PHOTOSYNTHESIS_REACH = "dophotosynthesisreach";

   /**
    * Name of behavior of respiration over the reach
    */
   public static final String BEHAVIOR_DO_RESPIRATION = "dorespiration";

   /**
    * Default state name for respiration in terms of oxygen consumption
    */
   public static final String DEFAULT_NAME_DO_RESPIRATION = 
         DORespiration.class.getSimpleName();

   /**
    * Name of the behavior of respiration
    */
   public static final String BEHAVIOR_DO_RESPIRATION_REACH = "dorespirationreach";

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
      addBehavior(
            BEHAVIOR_ADVECT,
            BehaviorSoluteAdvection.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_DO_AW_EXCHANGE_BOUND,
            BehaviorDOAWExchangeReach.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_DO_AW_EXCHANGE,
            BehaviorDOAWExchange.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_DO_PHOTOSYNTHESIS, 
            BehaviorDOPhotosynthesis.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_DO_PHOTOSYNTHESIS_REACH,
            BehaviorDOPhotosynthesisReach.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_DO_RESPIRATION,
            BehaviorDORespiration.class.getCanonicalName()
            );
      addBehavior(
            BEHAVIOR_DO_RESPIRATION_REACH,
            BehaviorDORespirationReach.class.getCanonicalName()
            );
   }

}
