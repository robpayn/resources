package org.payn.resources.solute.cell.otis;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Calculates the change in concentration due to dispersion
 * in a one dimensional model
 * 
 * @author robpayn
 *
 */
public class SoluteDispersion extends SoluteConcChange {

   /**
    * Length of the cell
    */
   private ValueDouble length;
   
   /**
    * Dispersion coefficient
    */
   private ValueDouble dispersionCoeff;
   
   /**
    * Concentration in the cell
    */
   private ValueDouble conc;

   @Override
   public void setUpdateDependenciesLoad() throws Exception 
   {
      conc = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      dispersionCoeff = (ValueDouble)createDependency(
            ResourceSolute.NAME_DISPERSION_COEFF
            ).getValue();
      length = (ValueDouble)createDependency(
            ResourceSolute.NAME_LENGTH
            ).getValue();
      setConcentrations();
   }

   @Override
   public void updateLoad() throws Exception 
   {
      value.n = (dispersionCoeff.n * (downstreamConc.n - (2.0 * conc.n) + upstreamConc.n)) 
            / (length.n * length.n);
   }

}
