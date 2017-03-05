package org.payn.resources.solute.cell.otis;

import org.payn.chsm.State;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.neoch.processors.ProcessorDoubleStorage;
import org.payn.resources.solute.ResourceSolute;

/**
 * Calculate the change in solute concentration from uptake 
 * of a solute based on a saturating
 * hyperbolic (e.g., Michaelis-Menten) kinetic
 * 
 * @author robpayn
 *
 */
public class SoluteUptake extends ProcessorDoubleLoad {

   /**
    * Concentration in the cell
    */
   private ValueDouble conc;
   
   /**
    * Maximum uptake
    */
   private ValueDouble umax;
   
   /**
    * Concentration at which half the maximum uptake occurs
    */
   private ValueDouble chalf;
   
   /**
    * Depth of water
    */
   private ValueDouble depth;

   @Override
   public void setUpdateDependencies() throws Exception
   {
      setUpdateDependenciesLoad();
      State storage = ((HolonCell)state.getParentHolon()).getStorage(
            state.getBehavior().getResource());
      storageProcessor = (ProcessorDoubleStorage)storage.getProcessor();
   }

   @Override
   public void setUpdateDependenciesLoad() throws Exception 
   {
      conc = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      umax = (ValueDouble)createDependency(
            ResourceSolute.NAME_UPTAKE_MAX
            ).getValue();
      chalf = (ValueDouble)createDependency(
            ResourceSolute.NAME_CONC_HALF_SAT
            ).getValue();
      depth = (ValueDouble)createDependency(
            ResourceSolute.NAME_DEPTH
            ).getValue();
   }

   @Override
   public void updateLoad() throws Exception 
   {
      value.n = ((umax.n * conc.n) / (chalf.n + conc.n)) / depth.n;
   }

}
