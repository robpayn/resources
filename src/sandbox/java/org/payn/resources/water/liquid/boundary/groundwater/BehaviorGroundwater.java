package org.payn.resources.water.liquid.boundary.groundwater;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.WaterFlow;

import neoch.behaviors.symmetric.symmdouble.BehaviorSymmetricDouble;

/**
 * Behavior of water moving horizontally in an unconfined porous medium
 * 
 * @author robpayn
 *
 */
public class BehaviorGroundwater extends BehaviorSymmetricDouble {

   /**
    * Hydraulic conductivity of the boundary
    */
   public static final String REQ_STATE_COND = "HydraulicConductivity";
   
   /**
    * Cross-sectional area of the boundary
    */
   public static final String REQ_STATE_XSECT_AREA = "XSectArea";
   
   @Override
   protected void addRequiredStates()
   {
       reqStateMap.put(REQ_STATE_COND, ValueDouble.class);
       reqStateMap.put(REQ_STATE_XSECT_AREA, ValueDouble.class);
   }

   @Override
   protected void addProcessors()
   {
       processorMap.put(WaterFlow.class.getSimpleName(), WaterFlowGroundwater.class);
       processorMap.put(HydraulicGradient.class.getSimpleName(), HydraulicGradient.class);
   }

}
