package org.payn.resources.solute.cell;

import org.payn.chsm.BehaviorAbstract;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.cell.storage.SoluteConc;
import org.payn.resources.solute.cell.storage.SoluteMass;

/**
 * Generic aqueous storage behavior for a solute in a cell
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteStorage extends BehaviorAbstract {

   /**
    * Required state name for water storage in the cell
    */
   public static String REQ_STATE_VOLUME = "WaterStorage";

   @Override
   public void addRequiredStates() 
   {
      addRequiredState(REQ_STATE_VOLUME, ValueDouble.class);
   }

   @Override
   public void addProcessors() 
   {
      addAbstractProcessor(
            ResourceSolute.NAME_SOLUTE_STORAGE, 
            SoluteMass.class, 
            SoluteMass.getValueClass()
            );
      addAbstractProcessor(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConc.class, 
            SoluteConc.getValueClass()
            );
   }

}
