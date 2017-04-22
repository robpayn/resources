package org.payn.resources.solute.gas.oxygen;

import org.payn.chsm.finitedifference.processors.ProcessorDoubleInfoInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;

/**
 * Controls the saturated dissolved oxygen concentration at a given water
 * temperature and atmospheric pressure
 * <p>
 * <b>References:</b>
 * </p>
 * <ul style="list-style-type: none"> <li
 * style="margin-bottom: 5px; margin-left: 10px; text-indent:-10px">García, H.
 * E., and L. I. Gordon (1992) Oxygen solubility in seawater: better fitting
 * equations. Limnology and Oceanography 37, 1307-1312.</li> </ul>
 * 
 * @author Rob Payn
 */
public class SaturatedDOConcUpstream extends ProcessorDoubleInfoInit {

   /**
    * Water temperature
    */
   private ValueDouble temperature;
   
   /**
    * Water density
    */
   private ValueDouble density;
   
   /**
    * Air pressure
    */
   private ValueDouble airpressure;
   
   /**
    * Conversion factor
    */
   double convert;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      temperature = (ValueDouble)createDependencyOnValue(
            ResourceSolute.DEFAULT_NAME_UPSTREAM_TEMP
            );
      density = (ValueDouble)createDependencyOnValue(
            ResourceSolute.DEFAULT_NAME_WATER_DENSITY_UPSTREAM
            );
      airpressure = (ValueDouble)createDependencyOnValue(
            ResourceSolute.DEFAULT_NAME_AIR_PRESSURE
            );
      try
      {
         convert = ((ValueDouble)createDependencyOnValue(
               ResourceSolute.DEFAULT_NAME_SAT_CONC_CONV 
               )).n;
      }
      catch (Exception e)
      {
         convert = 1;
      }
      // calculate aggregated constant multiplier
      // note this integrates all constants needed for calculate method
      // including the molecular weight of oxygen gas (0.032 g/mmol)
      // and standard atmospheric pressure (760 mm Hg)
      convert = convert * (0.032 / 760);
   }

   /**
    * Calculates the initial saturated DO concentration using an empirical
    * relationship with temperature and barometric pressure.
    * <p>
    * Optional unit conversion factor can be provided in the input 
    * if this is used with a model running with units of mass other than grams
    * or units of length other than meters.
    * </p>
    * [Mass Length<sup><small>-3</small></sup>]
    */
   @Override
   public void update() throws Exception 
   {
      value.n = convert * density.n * airpressure.n
            * Calculators.satDOEmpirical(temperature.n);
   }

   @Override
   public void setInitDependencies() throws Exception 
   {
      setUpdateDependencies();
   }

   @Override
   public void initialize() throws Exception 
   {
      update();
   }

}
