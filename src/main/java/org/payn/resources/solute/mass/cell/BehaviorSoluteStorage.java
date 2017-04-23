package org.payn.resources.solute.mass.cell;

import org.payn.chsm.resources.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.cell.storage.SoluteConc;
import org.payn.resources.solute.mass.cell.storage.SoluteMass;

/**
 * Generic aqueous storage behavior for a solute in a cell
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteStorage extends BehaviorAbstract {

   @Override
   public void registerStates() 
   {
      registerState(ResourceSolute.NAME_WATER_VOLUME, ValueDouble.class);
   }

   @Override
   public void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_STORAGE, 
            SoluteMass.class, 
            SoluteMass.getValueClass()
            );
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConc.class, 
            SoluteConc.getValueClass()
            );
   }

}
