package org.payn.resources.solute.concentration.cell;

import org.payn.chsm.finitedifference.processors.ProcessorDoubleDelta;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Calculate the change in solute concentration from uptake 
 * of a solute based on a saturating
 * hyperbolic (e.g., Michaelis-Menten) kinetic
 * 
 * @author robpayn
 *
 */
public class SoluteUptake extends ProcessorDoubleDelta {

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

   /**
    * Background concentration
    */
   private ValueDouble background;

   @Override
   public void setUpdateDependenciesDelta() throws Exception 
   {
      conc = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      background = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_BKG_CONC
            ).getValue();
      umax = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_UPTAKE_MAX
            ).getValue();
      chalf = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_CONC_HALF_SAT
            ).getValue();
      depth = (ValueDouble)createDependency(
            ResourceSolute.NAME_DEPTH
            ).getValue();
   }

   @Override
   public void updateDelta() throws Exception 
   {
      value.n = ( ((umax.n * background.n) / (chalf.n + background.n))
            - ((umax.n * conc.n) / (chalf.n + conc.n)) )
            / depth.n;
   }

}
