package org.payn.resources.solute.boundary.flow;

import org.payn.chsm.Holon;
import org.payn.chsm.finitedifference.processors.ProcessorDoubleChange;
import org.payn.chsm.processors.interfaces.InitializerSimpleAuto;
import org.payn.chsm.resources.time.BehaviorTime;
import org.payn.chsm.resources.time.Iteration;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.resources.solute.ResourceSolute;

/**
 * Processor for calculating the concentration based on background from an input file and
 * a solute injection
 * 
 * @author robpayn
 *
 */
public class SoluteConcInject extends ProcessorDoubleChange implements InitializerSimpleAuto {

   /**
    * Value for background concentration
    */
   private ValueDouble bkgConc;
   
   /**
    * Value for the interval counter
    */
   private ValueLong tick;
   
   /**
    * Value for the time interval of an iteration
    */
   private ValueDouble iterationInterval;
   
   /**
    * Value for the total mass of solute to inject
    */
   private ValueDouble mass;
   
   /**
    * Initial time interval of the injection
    */
   private ValueLong startTick;
   
   /**
    * Duration of the injection (in intervals)
    */
   private ValueLong duration;
   
   /**
    * value for solvent flow
    */
   private ValueDouble flow;
   
   /**
    * Interval at which injection should stop
    */
   private long stopTick;
   
   /**
    * Load for injection
    */
   private double load;

   @Override
   public void setInitDependencies() throws Exception 
   {
      Holon controllerHolon = (Holon)getController().getState();
      iterationInterval = (ValueDouble)createDependency(
            controllerHolon,
            BehaviorTime.ITERATION_INTERVAL
            ).getValue();

      mass = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_INJECT_MASS
            ).getValue();
      startTick = (ValueLong)createAbstractDependency(
            ResourceSolute.NAME_INJECT_START
            ).getValue();
      duration = (ValueLong)createAbstractDependency(
            ResourceSolute.NAME_INJECT_DURATION
            ).getValue();
      setUpdateDependencies();
   }

   @Override
   public void initialize() throws Exception 
   {
      stopTick = startTick.n + duration.n;
      load = mass.n / (iterationInterval.n * (double)duration.n);
      update();
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      Holon controllerHolon = (Holon)getController().getState();
      tick = (ValueLong)createDependency(
            controllerHolon,
            Iteration.class.getSimpleName()
            ).getValue();

      flow = (ValueDouble)createDependency(ResourceSolute.NAME_WATER_FLOW).getValue();
      bkgConc = (ValueDouble)createAbstractDependency(
            ResourceSolute.NAME_BKG_CONC
            ).getValue();
   }

   @Override
   public void update() throws Exception 
   {
      double injectConc = load / flow.n;
      if (tick.n >= startTick.n && tick.n < stopTick)
      {
         value.n = bkgConc.n + injectConc;
      }
      else
      {
         value.n = bkgConc.n;
      }
      
   }

}
