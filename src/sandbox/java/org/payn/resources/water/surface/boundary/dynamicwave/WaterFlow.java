package org.payn.resources.water.surface.boundary.dynamicwave;

import org.payn.chsm.Holon;
import org.payn.chsm.State;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.processors.interfaces.InitializerSimple;
import org.payn.chsm.resources.time.BehaviorTime;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorLoadDouble;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the flow of water
 * 
 * @author robpayn
 *
 */
public class WaterFlow extends ProcessorLoadDouble implements InitializerAutoSimple {

   /**
    * Chezey coefficient
    */
   private ValueDouble chezey;
   
   /**
    * Cross-sectional area of channel flow
    */
   private ValueDouble xSectionArea;
   
   /**
    * Waver velocity
    */
   private ValueDouble velocity;
   
   /**
    * Exponent for velocity in the Chezey equation
    */
   private ValueDouble velocityExponent;
   
   /**
    * Exponent for the radius in the Chezey equation
    */
   private ValueDouble radiusExponent;
   
   /**
    * Hydraulic radius
    */
   private ValueDouble hydraulicRadius;
   
   /**
    * Velocity exponent number
    */
   private double velocityExp;
   
   /**
    * Radius exponent number
    */
   private double radiusExp;
   
   /**
    * Cross-sectional area of the channel flow from the previous time step
    */
   private ValueDouble xSectionAreaPrev;
   
   /**
    * Time step
    */
   private ValueDouble timeStep;
   
   /**
    * Wetted width of the channel flow
    */
   private ValueDouble wettedWidth;
   
   /**
    * Bed slope
    */
   private ValueDouble bedSlope;
   
   /**
    * Hydraulic gradient
    */
   private ValueDouble hydraulicGradient;
   
   /**
    * Processor for velocity
    */
   private Velocity velocityProc;

   @Override
   public void setInitDependencies() throws Exception 
   {
      setUpdateDependencies();
   }

   @Override
   public void initialize() throws Exception 
   {
      if (velocityExponent == null)
      {
          velocityExp = 2.0;
          radiusExp = 4.0 / 3.0;
      }
      else
      {
          velocityExp = velocityExponent.n;
          radiusExp = radiusExponent.n;
      }
      ((InitializerSimple)velocityProc).initialize();
      value.n = calculate(value.n);
   }
   
   @Override
   public void setUpdateDependencies() throws Exception 
   {
      timeStep = (ValueDouble)((Holon)controller.getState()).getState(
            BehaviorTime.ITERATION_INTERVAL
            ).getValue();
      State velocityState = createDependency(
            BehaviorDynamicWave.NAME_VELOCITY
            );
      velocity = (ValueDouble)velocityState.getValue();
      velocityProc = (Velocity)velocityState.getProcessor();
      velocityProc.setDependencies(this);
      chezey = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_CHEZEY
            ).getValue();
      xSectionArea = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_XSECT_AREA
            ).getValue();
      xSectionAreaPrev = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_XSECT_AREA_PREV
            ).getValue();
      hydraulicRadius = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_HYDRAULIC_RADIUS
            ).getValue();
      wettedWidth = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_WETTED_WIDTH
            ).getValue();
      bedSlope = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_BED_SLOPE
            ).getValue();
      hydraulicGradient = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_HYDRAULIC_GRADIENT
            ).getValue();
      try
      {
         velocityExponent = (ValueDouble)createDependency(
               BehaviorDynamicWave.REQ_STATE_VELOCITY_EXP
               ).getValue();
         try
         {
            radiusExponent = (ValueDouble)createDependency(
                  BehaviorDynamicWave.REQ_STATE_RADIUS_EXP
                  ).getValue();
         }
         catch (Exception e)
         {
            throw new Exception(String.format(
                  "Radius exponent not available when velocity exponent provided in boundary %s", 
                  state.getParentHolon().toString()
                  ));
         }
      }
      catch (Exception e)
      {
         try
         {
            radiusExponent = (ValueDouble)createDependency(
                  BehaviorDynamicWave.REQ_STATE_RADIUS_EXP
                  ).getValue();
            throw new Exception(String.format(
                  "Radius exponent provided but velocity exponent not available in boundary %s", 
                  state.getParentHolon().toString()
                  ));
         }
         catch (Exception e2)
         {
            velocityExponent = null;
            radiusExponent = null;
         }
      }
   }

   @Override
   public void update() throws Exception 
   {
      velocityProc.update();
      value.n = calculate(value.n);
   }

   /**
    * Calculate the water flow
    * 
    * @param flow
    * @return
    * @throws Exception
    */
   private double calculate(double flow) throws Exception
   {
      double a, b;

      // short-circuit test
      if (xSectionArea.n == 0)
      {
          return 0.;
      }

      double absVelocity = Math.abs(velocity.n);

      if (velocityExponent == null)
      {
         a = absVelocity;
      }
      else
      {
         a = Math.pow(absVelocity, velocityExp - 1.0);
      }
      b = Math.pow(hydraulicRadius.n, radiusExp);
 
      flow = (flow 
            + (2 * velocity.n * (xSectionArea.n - xSectionAreaPrev.n))
            + (timeStep.n * velocity.n * velocity.n * wettedWidth.n * (bedSlope.n - hydraulicGradient.n)) 
            + (timeStep.n * ResourceWater.GRAVITY_ACC * (xSectionArea.n * hydraulicGradient.n)))
            / (1. + (timeStep.n * ResourceWater.GRAVITY_ACC * chezey.n * a / b));
      
      if (Double.isNaN(flow))
      {
         throw new Exception(String.format(
               "Severe model instability.  Flow is not a number in boundary %s.", 
               state.getParentHolon().toString()
               ));
      }
      return flow;
   }

}
