package org.payn.resources.solute.gas.oxygen;

import org.payn.chsm.Holon;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.ResourceSolute;

/**
 * Calculates air-water gas exchange for dissolved oxygen
 * 
 * @author v78h241
 *
 */
public class DOAWExchange extends ProcessorDoubleLoad {

   /**
    * Exchange velocity
    */
   private ValueDouble awExchangeVelocity;
   
   /**
    * Saturated oxygen concentration
    */
   private ValueDouble satConc;
   
   /**
    * Concentration in local cell
    */
   private ValueDouble conc;
   
   /**
    * Plan area of local cell
    */
   private ValueDouble planArea;

   @Override
   public void setUpdateDependenciesDelta() throws Exception 
   {
      Holon holon = (Holon)getController().getState();
      awExchangeVelocity = (ValueDouble)createDependencyOnValue(
            holon,
            ResourceSolute.DEFAULT_NAME_DO_AW_EXCH_VELOCITY
            );
      satConc = (ValueDouble)createDependencyOnValue(
            holon,
            ResourceSolute.DEFAULT_NAME_DO_SAT_CONC
            );
      
      holon = ((HolonBoundary)state.getParentHolon()).getCell();
      conc = (ValueDouble)createAbstractDependency(
            holon,
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      planArea = (ValueDouble)createDependencyOnValue(
            holon,
            ResourceSolute.NAME_PLANAREA
            );
   }

   @Override
   public void updateDelta() throws Exception 
   {
      value.n = awExchangeVelocity.n * 
            (satConc.n - conc.n) *
            planArea.n;
   }

}
