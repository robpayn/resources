package org.payn.resources.solute.mass.cell;

import org.payn.chsm.finitediff.processors.ProcessorDoublePostauxiliaryInitRequired;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Processor for solute concentration
 * 
 * @author v78h241
 *
 */
public class SoluteConc extends ProcessorDoublePostauxiliaryInitRequired {

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
      mass = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_SOLUTE_STORAGE
            ).getValue();
      waterVolume = (ValueDouble)createDependency(
            ResourceSolute.NAME_WATER_VOLUME
            ).getValue();
   }

   @Override
   public void update() 
   {
      value.n = mass.n / waterVolume.n ;
   }

}
