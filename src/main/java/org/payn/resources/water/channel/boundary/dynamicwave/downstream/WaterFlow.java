package org.payn.resources.water.channel.boundary.dynamicwave.downstream;

import java.util.ArrayList;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.processors.ProcessorLoadDouble;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.BehaviorChannelFlow;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;

/**
 * Calculates the flow of water out the downstream end of
 * a channel model
 * 
 * @author robpayn
 *
 */
public class WaterFlow extends ProcessorLoadDouble 
implements InitializerAutoSimple {

   /**
    * Bed slope
    */
   private ValueDouble bedSlope;
   
   /**
    * Hydraulic gradient
    */
   private ValueDouble hydraulicGradient;
   
   /**
    * Cross-sectional area of channel flow
    */
   private ValueDouble xSectionArea;
   
   /**
    * Hydraulic radius
    */
   private ValueDouble hydraulicRadius;
   
   /**
    * Velocity exponent for Chezey equation
    */
   private double velocityExponent;
   
   /**
    * Radius exponent for Chezey equation
    */
   private double radiusExponent;
   
   /**
    * Chezey coefficient
    */
   private ValueDouble chezey;

   @Override
   public void setInitDependencies() throws Exception 
   {
      if (value.isNoValue())
      {
         setUpdateDependencies();
      }
   }

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
         update();
      }
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      // Get the next boundary upstream
      HolonBoundary thisBoundary = 
            ((HolonBoundary)state.getParentHolon());
      ArrayList<HolonBoundary> boundaries = 
            thisBoundary.getCell().getBoundaries(
                  state.getBehavior().getResource().getName() + "."
                  + ResourceWater.BEHAVIOR_DYNAMIC_WAVE_WIELE
                  );
      HolonBoundary upstreamBoundary = boundaries.get(0);

      hydraulicGradient = (ValueDouble)createDependencyOnValue(
            upstreamBoundary,
            BehaviorChannelFlow.NAME_HYDRAULIC_GRADIENT
            );
      xSectionArea = (ValueDouble)createDependencyOnValue(
            upstreamBoundary,
            BehaviorDynamicWave.NAME_XSECT_AREA
            );
      hydraulicRadius = (ValueDouble)createDependencyOnValue(
            upstreamBoundary,
            BehaviorChannelFlow.NAME_HYDRAULIC_RADIUS
            );

      // Get bed slope and Chezey coefficient from this boundary
      // if available, otherwise get them from the upstream boundary
      try 
      {
         bedSlope = (ValueDouble)createDependencyOnValue(
               BehaviorChannelFlow.NAME_BED_SLOPE
               );
      }
      catch (Exception e)
      {
         bedSlope = (ValueDouble)createDependencyOnValue(
               upstreamBoundary,
               BehaviorChannelFlow.NAME_BED_SLOPE
               );
      }
      try 
      {
         chezey = (ValueDouble)createDependencyOnValue(
               BehaviorDynamicWave.NAME_CHEZEY
               );
      }
      catch (Exception e)
      {
         chezey = (ValueDouble)createDependencyOnValue(
               upstreamBoundary,
               BehaviorDynamicWave.NAME_CHEZEY
               );
      }
      
      // Check if exponents for Chezey equation are available in
      // this boundary or the next boundary upstream.  They will
      // have null values if not available in either
      ValueDouble[] exponents = ResourceWater.getChezeyExponentValues(
            state.getParentHolon(), this);
      if (exponents[0] == null)
      {
         exponents = ResourceWater.getChezeyExponentValues(
               upstreamBoundary, this);
      }
      if (exponents[0] == null)
      {
         velocityExponent = 2;
         radiusExponent = 4 / 3;
      }
      else
      {
         velocityExponent = exponents[0].n;
         radiusExponent = exponents[1].n;
      }
   }

   @Override
   public void update() throws Exception 
   {
      double gradient;
      if (bedSlope.n < 0.01)
      {
         gradient = bedSlope.n;
      }
      else
      {
         gradient = hydraulicGradient.n;
      }
      value.n = 0.;
      if (gradient > 0)
      {
          value.n = -xSectionArea.n 
                * Math.pow(
                      (Math.pow(hydraulicRadius.n, radiusExponent) * gradient / chezey.n), 
                      (1 / velocityExponent)
                      );
      }
   }

}
