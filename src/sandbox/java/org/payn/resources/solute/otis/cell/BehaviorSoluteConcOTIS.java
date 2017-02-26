package org.payn.resources.solute.otis.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public class BehaviorSoluteConcOTIS extends BehaviorMatrix {

   /**
    * Name of required state for water flow
    */
   public static String REQ_STATE_FLOW = "WaterFlow";

   /**
    * Name of required state for cross-sectional area of flow path
    */
   public static String REQ_STATE_AREA_XSECT = "AreaXSect";

   /**
    * Name of required state for length of flow path
    */
   public static String REQ_STATE_LENGTH = "Length";
   
   /**
    * Name of required state for dispersion coefficient
    */
   public static String REQ_STATE_DISP = "DispCoeff";

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(REQ_STATE_FLOW, ValueDouble.class);
      addRequiredState(REQ_STATE_AREA_XSECT, ValueDouble.class);
      addRequiredState(REQ_STATE_LENGTH, ValueDouble.class);
      addRequiredState(REQ_STATE_DISP, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceSoluteOTIS.NAME_SOLUTE_CONC, SoluteConc.class, ValueDouble.class);
      addProcessor(ResourceSoluteOTIS.NAME_ADVECTION, SoluteAdvection.class, ValueDouble.class);
      addProcessor(ResourceSoluteOTIS.NAME_DISPERSION, SoluteDispersion.class, ValueDouble.class);
   }

}
