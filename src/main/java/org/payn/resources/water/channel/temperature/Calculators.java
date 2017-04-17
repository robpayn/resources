package org.payn.resources.water.channel.temperature;

/**
 * Calculators for temperature calculations
 * 
 * @author robpayn
 *
 */
public class Calculators {

   /**
    * Calculates the density of water at a given temperature
    * 
    * @param tempCelsius temperature (&deg;C)
    * @return density (kg L<sup><small>-1</small></sup>)
    */
   public static double densityWaterEmpirical(double tempCelsius)
   {
       return 0.999842 + tempCelsius
               * (6.7940e-5 + tempCelsius
               * (-9.0953e-6 + tempCelsius
               * (1.0017e-7 + tempCelsius
               * (-1.1201e-9 + tempCelsius
               * 6.5363e-12))));
   }
   
}
