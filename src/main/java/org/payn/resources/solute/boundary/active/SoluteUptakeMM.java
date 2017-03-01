package org.payn.resources.solute.boundary.active;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.BehaviorSoluteActiveMM;

/**
 * Simulates solute uptake as a boundary condition based on Michaelis Menten kinetics
 * 
 * @author v78h241
 *
 */
public class SoluteUptakeMM 
extends ProcessorDoubleLoad {

   /**
    * Maximum uptake
    */
   private ValueDouble maxUptake;
   
   /**
    * Half saturation concentration
    */
   private ValueDouble halfSat;

   /**
    * Solute concentration in local cell
    */
   private ValueDouble conc;

   /**
    * Solute background concentration to maintain
    */
   private ValueDouble bkgConc;

   /**
    * Value of the plan area of the boundary
    */
   private ValueDouble planArea;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      planArea = (ValueDouble)createDependency(
            BehaviorSoluteActiveMM.REQ_STATE_PLANAREA
            ).getValue();
      bkgConc = (ValueDouble)createAbstractDependency(
            BehaviorSoluteActiveMM.REQ_STATE_BKG_CONC
            ).getValue();
      maxUptake = (ValueDouble)createAbstractDependency(
            BehaviorSoluteActiveMM.REQ_STATE_UMAX
            ).getValue();
      halfSat = (ValueDouble)createAbstractDependency(
            BehaviorSoluteActiveMM.REQ_STATE_HALFSAT
            ).getValue();
      conc = (ValueDouble)createAbstractDependency(
            ((HolonBoundary)getState().getParentHolon()).getCell(),
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
   }

   @Override
   public void update() 
   {
      value.n = planArea.n * (
            ((maxUptake.n * bkgConc.n) / (halfSat.n + bkgConc.n)) 
            - ((maxUptake.n * conc.n) / (halfSat.n + conc.n))
            );
   }

}
