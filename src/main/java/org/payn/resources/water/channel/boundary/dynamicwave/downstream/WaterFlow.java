package org.payn.resources.water.channel.boundary.dynamicwave.downstream;

import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.processors.ProcessorDoubleLoadInit;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the flow of water out the downstream end of
 * a channel model
 * 
 * @author robpayn
 *
 */
public class WaterFlow extends ProcessorDoubleLoadInit {

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
   }

   @Override
   public void initialize() throws Exception 
   {
      if (value.isNoValue())
      {
         value.n = 0.0;
      }
   }

   @Override
   public void setUpdateDependenciesDelta() throws Exception 
   {
      // Get the next boundary upstream
      HolonBoundary thisBoundary = 
            ((HolonBoundary)state.getParentHolon());
      ValueString upstreamBoundaryName = (ValueString)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_UPSTREAM_BOUNDARY_NAME
            );
      HolonBoundary upstreamBoundary = thisBoundary.getCell().getBoundary(
            upstreamBoundaryName.string
            );

      hydraulicGradient = (ValueDouble)createDependencyOnValue(
            upstreamBoundary,
            ResourceWater.DEFAULT_NAME_HYDR_GRAD
            );
      xSectionArea = (ValueDouble)createDependencyOnValue(
            upstreamBoundary,
            ResourceWater.DEFAULT_NAME_WETTED_XSECT_AREA
            );
      hydraulicRadius = (ValueDouble)createDependencyOnValue(
            upstreamBoundary,
            ResourceWater.DEFAULT_NAME_HYDR_RADIUS
            );

      // Get bed slope and Chezey coefficient from this boundary
      // if available, otherwise get them from the upstream boundary
      try 
      {
         bedSlope = (ValueDouble)createDependencyOnValue(
               ResourceWater.DEFAULT_NAME_BED_SLOPE
               );
      }
      catch (Exception e)
      {
         bedSlope = (ValueDouble)createDependencyOnValue(
               upstreamBoundary,
               ResourceWater.DEFAULT_NAME_BED_SLOPE
               );
      }
      try 
      {
         chezey = (ValueDouble)createDependencyOnValue(
               ResourceWater.DEFAULT_NAME_CHEZEY
               );
      }
      catch (Exception e)
      {
         chezey = (ValueDouble)createDependencyOnValue(
               upstreamBoundary,
               ResourceWater.DEFAULT_NAME_CHEZEY
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
         velocityExponent = 2.0;
         radiusExponent = 4.0 / 3.0;
      }
      else
      {
         velocityExponent = exponents[0].n;
         radiusExponent = exponents[1].n;
      }
   }

   @Override
   public void updateDelta() throws Exception 
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
      value.n = 0.0;
      if (gradient > 0.0)
      {
          value.n = -xSectionArea.n 
                * Math.pow(
                      (Math.pow(hydraulicRadius.n, radiusExponent) * gradient / chezey.n), 
                      (1.0 / velocityExponent)
                      );
      }
   }

}
