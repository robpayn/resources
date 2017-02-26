package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleTrade;

/**
 * Determines the friction factor based on the Wiele model for
 * friction as a function of hydraulic radius
 * 
 * @author v78h241
 *
 */
public class Friction extends ProcessorDoubleTrade implements InitializerAutoSimple {

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
            BehaviorDynamicWaveWiele.REQ_STATE_WIELEINT
            ).getValue();
      wieleSlope = (ValueDouble)createDependency(
            BehaviorDynamicWaveWiele.REQ_STATE_WIELESLOPE
            ).getValue();
      hydraulicRadius = (ValueDouble)createDependency(
            BehaviorDynamicWaveWiele.NAME_HYDRAULIC_RADIUS
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = wieleInt.n + wieleSlope.n * Math.log(hydraulicRadius.n);
   }

}
