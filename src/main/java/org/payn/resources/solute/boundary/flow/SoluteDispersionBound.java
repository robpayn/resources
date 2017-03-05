package org.payn.resources.solute.boundary.flow;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.ResourceSolute;

/**
 * Processor to calculate dispersive transport of solute
 * 
 * @author v78h241
 *
 */
public class SoluteDispersionBound extends ProcessorDoubleLoad {

   /**
    * Value of concentration in local cell
    */
   private ValueDouble concLocal;
   
   /**
    * Value of external concentration
    */
   private ValueDouble extConc;

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
   private ValueDouble xSectionArea;

   @Override
   public void setUpdateDependenciesLoad() throws Exception 
   {
      extConc = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      HolonCell cell = ((HolonBoundary)getState().getParentHolon()).getCell();
      concLocal = (ValueDouble)createAbstractDependency(
            cell, 
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      dispCoeff = (ValueDouble)createDependency(
            ResourceSolute.NAME_DISPERSION_COEFF
            ).getValue();
      length = (ValueDouble)createDependency(
            ResourceSolute.NAME_LENGTH
            ).getValue();
      xSectionArea = (ValueDouble)createDependency(
            ResourceSolute.NAME_AREA_XSECT
            ).getValue();
   }

   @Override
   public void updateLoad() 
   {
      double grad = (extConc.n - concLocal.n) / length.n;
      value.n = xSectionArea.n * dispCoeff.n * grad;
   }

}
