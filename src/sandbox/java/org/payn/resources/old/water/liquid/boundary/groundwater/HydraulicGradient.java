package org.payn.resources.old.water.liquid.boundary.groundwater;

import org.payn.chsm.processors.interfaces.UpdaterSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.liquid.cell.HydraulicHead;

import neoch.HolonBoundary;
import neoch.processors.ProcessorDoubleTrade;

/**
 * Controls the hydraulic gradient across the boundary
 * 
 * @author robpayn
 *
 */
public class HydraulicGradient extends ProcessorDoubleTrade implements UpdaterSimple {

   /**
    * Head in the connected cell
    */
   private ValueDouble head;
   
   /**
    * Head in the adjacent cell
    */
   private ValueDouble headAdj;
   
   /**
    * Get the values necessary to calculate the hydraulic gradient
    */
   @Override
   public void setUpdateDependencies() throws Exception 
   {
      head = (ValueDouble)createDependency(
            ((HolonBoundary)state.getParentHolon()).getCell(),
            HydraulicHead.class.getSimpleName()
            ).getValue();
      headAdj = (ValueDouble)createDependency(
            ((HolonBoundary)state.getParentHolon()).getAdjacentBoundary().getCell(),
            HydraulicHead.class.getSimpleName()
            ).getValue();
   }

   /**
    * Update the value of the hydraulic gradient using traditional sign convention
    * (positive water flow along a negative gradient).
    */
   @Override
   public void update() 
   {
      value.n = headAdj.n - head.n;
   }

}
