package org.payn.resources.solute.cell.storage;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.cell.BehaviorSoluteStorage;

import neoch.processors.ProcessorStorageDouble;

/**
 * Processor for solute mass
 * 
 * @author v78h241
 *
 */
public class SoluteMass extends ProcessorStorageDouble  implements InitializerAutoSimple {
   
   /**
    * Concentration value
    */
   private ValueDouble concentration;
   
   /**
    * Water volume value
    */
   private ValueDouble waterVolume;

   @Override
   public void setInitDependencies() throws Exception 
   {
      concentration = (ValueDouble)createDependency(
            getResourceName() + ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      waterVolume = (ValueDouble)createDependency(
            BehaviorSoluteStorage.REQ_STATE_VOLUME
            ).getValue();
   }

   @Override
   public void initialize() 
   {
      value.n = concentration.n * waterVolume.n;
   }

}
