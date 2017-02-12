package org.payn.resources.solute.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteAdvection;
import org.payn.resources.solute.boundary.flow.SoluteDispersion;

import neoch.behaviors.symmetric.symmdouble.BehaviorSymmetricDouble;

/**
 * Behavior for aqueous solute movement between aqueous storage cells
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteFlow extends BehaviorSymmetricDouble {

   /**
    * Name of required state for dispersion coefficient
    */
   public static String REQ_STATE_DISP = "DispCoeff";
   
   /**
    * Name of required state for water flow
    */
   public static String REQ_STATE_FLOW = "WaterFlow";
   
   /**
    * Name of required state for length of flow path
    */
   public static String REQ_STATE_LENGTH = "Length";
   
   /**
    * Name of required state for cross-sectional area of flow path
    */
   public static String REQ_STATE_AREA_XSECT = "AreaXSect";
   
   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(REQ_STATE_DISP, ValueDouble.class);
      addRequiredState(REQ_STATE_FLOW, ValueDouble.class);
      addRequiredState(REQ_STATE_LENGTH, ValueDouble.class);
      addRequiredState(REQ_STATE_AREA_XSECT, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceSolute.NAME_ADVECTION, SoluteAdvection.class, SoluteAdvection.getValueClass());
      addProcessor(ResourceSolute.NAME_DISPERSION, SoluteDispersion.class, SoluteDispersion.getValueClass());
   }

}
