package org.payn.resources.solute.cell.storage;

import org.payn.chsm.processors.interfaces.InitializerSimpleAuto;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleStorage;
import org.payn.resources.solute.ResourceSolute;

/**
 * Processor for solute mass
 * 
 * @author v78h241
 *
 */
public class SoluteMass extends ProcessorDoubleStorage implements InitializerSimpleAuto {
   
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
      concentration = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_SOLUTE_CONC
            ).getValue();
      waterVolume = (ValueDouble)createDependency(
            ResourceSolute.NAME_WATER_VOLUME
            ).getValue();
   }

   @Override
   public void initialize() 
   {
      value.n = concentration.n * waterVolume.n;
   }

}
