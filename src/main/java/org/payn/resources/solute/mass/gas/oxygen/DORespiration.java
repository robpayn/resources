package org.payn.resources.solute.mass.gas.oxygen;

import org.payn.chsm.Holon;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.ResourceSolute;

/**
 * Controls the instantaneous oxygen mass flux due to aerobic respiration,
 * based on a constant respiration rate
 * 
 * @author Rob Payn
 */
public class DORespiration extends ProcessorDoubleLoad {

   /**
    * Plan area of the local river cell
    */
   private ValueDouble planArea;
   
   /**
    * Average daily oxygen mass flux due to respiration [Mass
    * Length<sup><small>-2</small></sup> Time<sup><small>-1</small></sup>]
    */
   private ValueDouble respiration;

   @Override
   public void setUpdateDependenciesDelta() throws Exception 
   {
      Holon holon = (Holon)getController().getState();
      respiration = (ValueDouble)createDependencyOnValue(
            holon,
            ResourceSolute.DEFAULT_NAME_DO_RESPIRATION
            );

      holon = ((HolonBoundary)state.getParentHolon()).getCell();
      planArea = (ValueDouble)createDependencyOnValue(
            holon,
            ResourceSolute.NAME_PLANAREA
            );
   }

   /**
    * Calculate the oxygen mass flux due to respiration
    * [Mass Time<sup><small>-1</small></sup>]
    */
   @Override
   public void updateDelta() throws Exception 
   {
      value.n = respiration.n * planArea.n;
   }

}
