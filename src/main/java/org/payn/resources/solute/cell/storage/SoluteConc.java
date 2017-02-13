package org.payn.resources.solute.cell.storage;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleState;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.cell.BehaviorSoluteStorage;

/**
 * Processor for solute concentration
 * 
 * @author v78h241
 *
 */
public class SoluteConc extends ProcessorDoubleState {

   /**
    * Water volume value
    */
   private ValueDouble waterVolume;
   
   /**
    * Solute mass value
    */
   private ValueDouble mass;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      mass = (ValueDouble)createDependency(
            getResourceName() + ResourceSolute.NAME_SOLUTE_STORAGE
            ).getValue();
      waterVolume = (ValueDouble)createDependency(
            BehaviorSoluteStorage.REQ_STATE_VOLUME
            ).getValue();
   }

   @Override
   public void update() 
   {
      value.n = mass.n / waterVolume.n ;
   }

}
