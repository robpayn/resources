package org.payn.resources.water.surface.boundary;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorLoadDouble;

public class WaterFlow extends ProcessorLoadDouble implements InitializerAutoSimple {

   private ValueDouble chezey;

   @Override
   public void setInitDependencies() throws Exception 
   {
   }

   @Override
   public void initialize() throws Exception 
   {
      
   }
   
   @Override
   public void setUpdateDependencies() throws Exception 
   {
      chezey = (ValueDouble)createDependency(
            BehaviorDynamicWaveWiele.NAME_CHEZEY
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      
   }

   private double calculate(double flow)
   {
       double a, b, frac;
       double[] vect;

       // short-circuit test
       if (xsectcur.v == 0)
       {
           return 0.;
       }

       /*** DYNAMIC WAVE CALCULATIONS *******/
       if (eqtype.v == Utility.EquationTypes.DYNAMIC_WAVE)
       {

           double posVel = Math.abs(velocity.v);

           if (Param.preciseWater || Param.WDWBM)
           {
               a = (velocityExpState == null) ? posVel : Math.pow(posVel, (velocityExp - 1.));
               b = Math.pow(hradius.v, radiusExp);
           }
           else
           {
               if (velocityExp == 2.0)
               {
                   a = posVel;
               }
               else
               {
                   if (posVel < Utility.maxLoVelocity)
                   {
                       frac = posVel / Utility.maxLoVelocity;
                       vect = loVelPows;
                   }
                   else
                   {
                       frac = posVel / Utility.maxVelocity;
                       vect = velPows;
                   }
                   a = TabledInterpolater.getValue(vect, frac);
               }
               if (hradius.v <= Utility.maxLoHydroRad)
               {
                   frac = hradius.v / Utility.maxLoHydroRad;
                   vect = loHRadPows;
               }
               else
               {
                   frac = hradius.v / Utility.maxHydroRad;
                   vect = hRadPows;
               }
               b = TabledInterpolater.getValue(vect, frac);
           }

           flow = (flow 
                   + (2 * velocity.v * (xsectcur.v - xsectpre.v))
                   + (delT * velocity.v * velocity.v * wetwth.v * (bedslope.v - hydgrad.v)) 
                   + (delT_g * (xsectcur.v * hydgrad.v)))
               / (1. + (delT_g * frictionValue * a / b));
         
           /********** DIFFUSE WAVE CALCULATION *****************/

       }
       else if (eqtype.v == Utility.EquationTypes.DIFFUSE_WAVE)
       {
           // hydgrad.v = ( -hydgrad.v) - (windShear / hradius.v);

           if (Param.preciseWater || Param.WDWBM)
           {
               if (hydgrad.v < 0)
               {
                   flow = -xsectcur.v
                           * Math.pow((-hydgrad.v * Math.pow(hradius.v, radiusExp) / frictionValue),
                                   (1. / velocityExp));
               }
               else
               {
                   flow = (xsectcur.v * Math.pow((hydgrad.v * Math.pow(hradius.v, radiusExp) / frictionValue),
                           (1. / velocityExp)));
               }
           }
           else
           {

               if (hradius.v < Utility.maxLoHydroRad)
               {
                   frac = hradius.v / (Utility.maxLoHydroRad);
                   vect = loHRadPows;
               }
               else
               {
                   frac = hradius.v / Utility.maxHydroRad;
                   vect = hRadPows;
               }

               a = Math.abs(hydgrad.v) * TabledInterpolater.getValue(vect, frac) / frictionValue;

               if (a > Utility.maxDiffuseVal)
               {
                   initError("Intermediate value 'a' ((hydGrad * hydRad ^ p2) / manN) is greater than "
                           + "MaxDiffuseVal stated in water.edge.manning.utility.");
                   a = Utility.maxDiffuseVal;
               }

               if (a < Utility.maxLoDiffuseVal)
               {
                   frac = a / (Utility.maxLoDiffuseVal);
                   vect = loDiffusePows;
               }
               else
               {
                   frac = a / Utility.maxDiffuseVal;
                   vect = diffusePows;
               }

               double vel = TabledInterpolater.getValue(vect, frac);

               if (hydgrad.v < 0)
               {
                   flow = -xsectcur.v * vel;
               }
               else
               {
                   flow = xsectcur.v * vel;
               }
           }
       }
       else
       {
           Logger.logError(this + ": Invalid equation type specified: " + eqtype.v);
           flow = 0;
       }

       if (Double.isNaN(flow))
           Logger.logError("Severe model instability.  Flow is not a number in edge " + myHolon.getUID().toString());

       return flow;
   }

}
