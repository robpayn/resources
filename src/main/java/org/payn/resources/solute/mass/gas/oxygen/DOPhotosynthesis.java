package org.payn.resources.solute.mass.gas.oxygen;

import org.payn.chsm.Holon;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.ResourceSolute;

/**
 * Controls the instantaneous oxygen mass flux due to production from photosynthesis,
 * based on the distribution of photosynthetically active radiation
 * through the day.
 * 
 * @author Rob Payn
 */
public class DOPhotosynthesis extends ProcessorDoubleLoad {

   /**
    * Plan area of the local river cell
    */
   private ValueDouble planArea;
   
   /**
    * Daily average ratio of oxygen production to PAR [(Mass
    * Length<sup><small>-2</small></sup> Time<sup><small>-1</small></sup>)
    * (Energy Length<sup><small>-2</small></sup>
    * Time<sup><small>-1</small></sup>)<sup><small>-1</small></sup>]
    */
   private ValueDouble pToPARRatio;

   /**
    * Instantaneous photosynthetically active radiation (PAR) energy flux
    * [Energy Length<sup><small>-2</small></sup>
    * Time<sup><small>-1</small></sup>]
    */
   private ValueDouble instPAR;

   @Override
   public void setUpdateDependenciesDelta() throws Exception 
   {
      Holon holon = (Holon)getController().getState();
      pToPARRatio = (ValueDouble)createDependencyOnValue(
            holon,
            ResourceSolute.DEFAULT_NAME_DO_PTOPAR_RATIO
            );
      instPAR = (ValueDouble)createDependencyOnValue(
            holon,
            ResourceSolute.DEFAULT_NAME_PAR
            );

      holon = ((HolonBoundary)state.getParentHolon()).getCell();
      planArea = (ValueDouble)createDependencyOnValue(
            holon,
            ResourceSolute.NAME_PLANAREA
            );
   }

   /**
    * Calculate the oxygen mass flux due to photosynthesis
    * [Mass Time<sup><small>-1</small></sup>]
    */
   @Override
   public void updateDelta() throws Exception 
   {
      value.n = instPAR.n * pToPARRatio.n * planArea.n;
   }

}
