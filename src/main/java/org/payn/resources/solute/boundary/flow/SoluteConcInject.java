package org.payn.resources.solute.boundary.flow;

import org.payn.chsm.Holon;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.resources.time.BehaviorTime;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.resources.solute.boundary.BehaviorSoluteBoundInject;
import org.payn.resources.solute.boundary.BehaviorSoluteFlow;

import neoch.processors.ProcessorDoubleTrade;

/**
 * Processor for calculating the concentration based on background from an input file and
 * a solute injection
 * 
 * @author robpayn
 *
 */
public class SoluteConcInject extends ProcessorDoubleTrade implements InitializerAutoSimple {

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

      mass = (ValueDouble)createDependency(
            getResourceName() + BehaviorSoluteBoundInject.REQ_STATE_MASS
            ).getValue();
      startTick = (ValueLong)createDependency(
            getResourceName() + BehaviorSoluteBoundInject.REQ_STATE_START
            ).getValue();
      duration = (ValueLong)createDependency(
            getResourceName() + BehaviorSoluteBoundInject.REQ_STATE_DURATION
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
            BehaviorTime.DEFAULT_ITERATION_NAME
            ).getValue();

      flow = (ValueDouble)createDependency(BehaviorSoluteFlow.REQ_STATE_FLOW).getValue();
      bkgConc = (ValueDouble)createDependency(
            getResourceName() + BehaviorSoluteBoundInject.REQ_STATE_BKGCONC
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
