package org.payn.resources.solute.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.active.SoluteUptakeMM;

/**
 * Behavior of solute at a flow-controlled boundary
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteActiveMM extends BehaviorMatrix {

   /**
    * Name for required state of maximum uptake
    */
   public static String REQ_STATE_UMAX = "UMax";
   
   /**
    * Name for required state of half saturation concentration
    */
   public static String REQ_STATE_HALFSAT = "HalfSatConc";
   
   /**
    * Name for required state of plan area
    */
   public static String REQ_STATE_PLANAREA = "PlanArea";
   
   /**
    * Name for the background concentration to maintain
    */
   public static String REQ_STATE_BKG_CONC = "Bkg" + ResourceSolute.NAME_SOLUTE_CONC;
   
   @Override
   public void addRequiredStates() 
   {
      addAbstractRequiredState(REQ_STATE_UMAX, ValueDouble.class);
      addAbstractRequiredState(REQ_STATE_HALFSAT, ValueDouble.class);
      addAbstractRequiredState(REQ_STATE_BKG_CONC, ValueDouble.class);
      
      addRequiredState(REQ_STATE_PLANAREA, ValueDouble.class);
   }

   @Override
   public void addProcessors() 
   {
      addAbstractProcessor(
            ResourceSolute.NAME_SOLUTE_LOAD, 
            SoluteUptakeMM.class, 
            SoluteUptakeMM.getValueClass()
            );
   }

}
