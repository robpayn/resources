package org.payn.resources.old.water.liquid.boundary.groundwater;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.WaterFlow;

/**
 * Tracks flow of water based on hydraulic head in adjacent cells
 * and cross-sectional area of the boundary
 * 
 * @author robpayn
 *
 */
public class WaterFlowGroundwater extends WaterFlow {

   /**
    * Hydraulic conductivity of the symmetric boundary
    */
   private ValueDouble cond;

   /**
    * Cross-sectional area of the boundary
    */
   private ValueDouble xSectArea;

   /**
    * Hydraulc gradient across the symmetric boundary
    */
   private ValueDouble grad;

   /**
    * Get the values necessary to calculate flow
    */
   @Override
   public void setUpdateDependencies() throws Exception 
   {
      cond = (ValueDouble)createDependency(
            BehaviorGroundwater.REQ_STATE_COND
            ).getValue();
      xSectArea = (ValueDouble)createDependency(
            BehaviorGroundwater.REQ_STATE_XSECT_AREA
            ).getValue();
      grad = (ValueDouble)createDependency(
            HydraulicGradient.class.getSimpleName()
            ).getValue();
   }

   /**
    * Update the flow based on Darcy's equation
    */
   @Override
   public void update() 
   {
      value.n = xSectArea.n * cond.n * grad.n;
   }

}
