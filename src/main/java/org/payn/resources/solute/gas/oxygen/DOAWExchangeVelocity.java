package org.payn.resources.solute.gas.oxygen;

import org.payn.chsm.finitedifference.processors.ProcessorDoubleChange;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Controls the temperature corrected first-order gas exchange rate times water
 * depth (piston velocity)
 * <p>
 * <b>References:</b>
 * </p>
 * <ul style="list-style-type: none"> <li
 * style="margin-bottom: 5px; margin-left: 10px; text-indent:-10px"> Jahne, B.,
 * K. O. Munnich, R. Bosinger, A. Dutzi, W. Huber, and P. Libner (1987) On
 * parameters influencing air-water gas exchange. Journal of Geophysical
 * Research 74, 456-464. </li> </ul>
 * 
 * @author robert.payn
 */
public class DOAWExchangeVelocity extends ProcessorDoubleChange {

   /**
    * Temperature of water (&deg;C)
    */
   private ValueDouble temperature;
   
   /**
    * First-order gas exchange velocity at a Schmidt number of 600 [Length
    * Time<sup><small>-1</small></sup>]
    */
   private ValueDouble k600;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      k600 = (ValueDouble)createAbstractDependency(
            ResourceSolute.DEFAULT_NAME_K600
            ).getValue();
      temperature = (ValueDouble)createDependencyOnValue(
            ResourceSolute.DEFAULT_NAME_TEMP
            );
   }

   /**
    * Calculate the temperature corrected gas exchange (Jahne et al. 1987)
    * [Length Time<sup><small>-1</small></sup>]
    */
   @Override
   public void update() throws Exception 
   {
      value.n = k600.n * Math.sqrt(
            600 / (Calculators.schmidtNumberEmpirical(temperature.n))
            );
   }

}
