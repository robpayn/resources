package org.payn.resources.solute.boundary.active;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.ResourceSolute;

/**
 * Simulates solute uptake as a boundary condition based on Michaelis Menten kinetics
 * 
 * @author v78h241
 *
 */
public class SoluteUptakeMM extends ProcessorDoubleLoad {

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
   public void setUpdateDependenciesLoad() throws Exception 
   {
      planArea = (ValueDouble)createDependency(
            ResourceSolute.NAME_PLANAREA
            ).getValue();
      bkgConc = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_BKG_CONC
            ).getValue();
      maxUptake = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_UPTAKE_MAX
            ).getValue();
      halfSat = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_CONC_HALF_SAT
            ).getValue();
      conc = (ValueDouble)createAbstractDependency(
            ((HolonBoundary)getState().getParentHolon()).getCell(),
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
   }

   @Override
   public void updateLoad() 
   {
      value.n = planArea.n * (
            ((maxUptake.n * bkgConc.n) / (halfSat.n + bkgConc.n)) 
            - ((maxUptake.n * conc.n) / (halfSat.n + conc.n))
            );
   }

}
