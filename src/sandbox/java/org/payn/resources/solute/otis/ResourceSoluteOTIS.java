package org.payn.resources.solute.otis;

import org.payn.chsm.ResourceAbstract;
import org.payn.resources.solute.otis.boundary.BehaviorSoluteFlowOTIS;
import org.payn.resources.solute.otis.cell.BehaviorSoluteConcOTIS;

public class ResourceSoluteOTIS extends ResourceAbstract {

   public static final String BEHAVIOR_STORAGE_CONC = "storage";
   
   public static final String BEHAVIOR_FLOW_CONC = "flow";
   
   public static final String NAME_SOLUTE_CONC = "Conc";

   public static final String NAME_ADVECTION = "Advect";

   public static final String NAME_DISPERSION = "Dispersion";

   public static final String NAME_UPTAKE = "Uptake";

   public static final String NAME_UPTAKE_MAX = "UMax";

   public static final String NAME_CONC_HALF_SAT = "CHalf";

   public static final String NAME_DEPTH = "Depth";

   /**
    * Name of required state for water flow
    */
   public static String NAME_WATER_FLOW = "WaterFlow";

   /**
    * Name of required state for cross-sectional area of flow path
    */
   public static String NAME_AREA_XSECT = "AreaXSect";

   /**
    * Name of required state for length of flow path
    */
   public static String NAME_LENGTH = "Length";
   
   /**
    * Name of required state for dispersion coefficient
    */
   public static String NAME_DISPERSION_COEFF = "DispCoeff";

   @Override
   public void addBehaviors() 
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
