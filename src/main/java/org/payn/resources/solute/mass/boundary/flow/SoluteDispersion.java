package org.payn.resources.solute.mass.boundary.flow;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorDoubleLoadSymmetric;
import org.payn.resources.solute.ResourceSolute;

/**
 * Processor to calculate dispersive transport of solute
 * 
 * @author v78h241
 *
 */
public class SoluteDispersion extends ProcessorDoubleLoadSymmetric {

   /**
    * Value of concentration in local cell
    */
   private ValueDouble concLocal;
   
   /**
    * Value of concentration in adjacent cell
    */
   private ValueDouble concAdjacent;

   /**
    * Value of dispersion coefficient
    */
   private ValueDouble dispCoeff;

   /**
    * Value of length of flow path
    */
   private ValueDouble length;

   /**
    * Value of the cross-sectional area of the flow path
    */
   private ValueDouble area;

   @Override
   public void setUpdateDependenciesDelta() throws Exception 
   {
      HolonCell cell = ((HolonBoundary)getState().getParentHolon()).getCell();
      concLocal = (ValueDouble)createAbstractDependency(
            cell, 
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      cell = ((HolonBoundary)getState().getParentHolon()).getAdjacentBoundary().getCell();
      concAdjacent = (ValueDouble)createAbstractDependency(
            cell, 
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      dispCoeff = (ValueDouble)createDependency(
            ResourceSolute.NAME_DISPERSION_COEFF
            ).getValue();
      length = (ValueDouble)createDependency(
            ResourceSolute.NAME_LENGTH
            ).getValue();
      area = (ValueDouble)createDependency(
            ResourceSolute.NAME_AREA_XSECT
            ).getValue();
   }

   @Override
   public void updateDelta() 
   {
      double grad = (concAdjacent.n - concLocal.n) / length.n;
      value.n = area.n * dispCoeff.n * grad;
   }

}
