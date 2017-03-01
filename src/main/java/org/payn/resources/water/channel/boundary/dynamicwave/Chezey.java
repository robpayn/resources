package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorDoubleTradeInit;
import org.payn.resources.water.ResourceWater;

/**
 * Processor for calculating the Chezy coefficient
 * 
 * @author v78h241
 *
 */
public class Chezey extends ProcessorDoubleTradeInit {

   /**
    * Real value for fraction 1/3
    */
   private static final double ONE_THIRD = 1.0 / 3.0;

   /**
    * Friction factor
    */
   private ValueDouble friction;
   
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

   /**
    * Hydraulic radius
    */
   private ValueDouble hydraulicRadius;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      friction = (ValueDouble)createDependency(
            ResourceWater.NAME_FRICTION_FACTOR
            ).getValue();
      hydraulicRadius = (ValueDouble)createDependency(
            ResourceWater.NAME_HYDRAULIC_RADIUS
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = Math.pow(hydraulicRadius.n, ONE_THIRD) 
            / (ResourceWater.CONSTANT_GRAVITY_ACC * Math.pow(friction.n, 2.0));
   }

}
