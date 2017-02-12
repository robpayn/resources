package org.payn.resources.solute.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.cell.storage.SoluteConc;
import org.payn.resources.solute.cell.storage.SoluteMass;

import neoch.behaviors.BehaviorMatrix;

/**
 * Generic aqueous storage behavior for a solute in a cell
 * 
 * @author v78h241
 *
 */
public class BehaviorSoluteStorage extends BehaviorMatrix {

   /**
    * Required state name for water storage in the cell
    */
   public static String REQ_STATE_VOLUME = "WaterStorage";

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(REQ_STATE_VOLUME, ValueDouble.class);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceSolute.NAME_SOLUTE_STORAGE, SoluteMass.class, SoluteMass.getValueClass());
      addProcessor(ResourceSolute.NAME_SOLUTE_CONC, SoluteConc.class, SoluteConc.getValueClass());
   }

}
