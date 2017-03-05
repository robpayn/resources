package org.payn.resources.solute.cell.otis;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Calculates the change in concentration in a cell due to 
 * one-dimensional transport
 * 
 * @author robpayn
 *
 */
public class SoluteAdvection extends SoluteConcChange {

   /**
    * Water flow
    */
   private ValueDouble waterFlow;
   
   /**
    * Cross-sectional area of the flow
    */
   private ValueDouble xSectionArea;
   
   /**
    * Length of the cell
    */
   private ValueDouble length;

   @Override
   public void setUpdateDependenciesLoad() throws Exception 
   {
      waterFlow = (ValueDouble)createDependency(
            ResourceSolute.NAME_WATER_FLOW
            ).getValue();
      xSectionArea = (ValueDouble)createDependency(
            ResourceSolute.NAME_AREA_XSECT
            ).getValue();
      length = (ValueDouble)createDependency(
            ResourceSolute.NAME_LENGTH
            ).getValue();
      
      setConcentrations();
      
   }

   @Override
   public void updateLoad() throws Exception 
   {
      value.n = (waterFlow.n / xSectionArea.n) * ((upstreamConc.n - downstreamConc.n) / (2.0 * length.n));
   }

}
