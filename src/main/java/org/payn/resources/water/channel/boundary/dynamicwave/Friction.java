package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.finitediff.processors.ProcessorDoublePreauxiliaryInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Determines the friction factor based on the Wiele model for
 * friction as a function of hydraulic radius
 * 
 * @author v78h241
 *
 */
public class Friction extends ProcessorDoublePreauxiliaryInit {

   /**
    * Hydraulic radius
    */
   private ValueDouble hydraulicRadius;
   
   /**
    * Intercept for the the Wiele model
    */
   private ValueDouble wieleInt;

   /**
    * Slope for the Wiele model
    */
   private ValueDouble wieleSlope;

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

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      wieleInt = (ValueDouble)createDependency(
            ResourceWater.DEFAULT_NAME_WIELE_MODEL_INTERCEPT
            ).getValue();
      wieleSlope = (ValueDouble)createDependency(
            ResourceWater.DEFAULT_NAME_WIELE_MODEL_SLOPE
            ).getValue();
      hydraulicRadius = (ValueDouble)createDependency(
            ResourceWater.DEFAULT_NAME_HYDR_RADIUS
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = wieleInt.n + wieleSlope.n * Math.log(hydraulicRadius.n);
   }

}
