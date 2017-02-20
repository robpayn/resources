package org.payn.resources.solute.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteConcInject;
import org.payn.resources.solute.boundary.flow.SoluteConcInterpolate;

/**
 * A boundary condition for simulating an injection at a boundary
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteBoundInject extends BehaviorSoluteBoundInterp {

   /**
    * Name for state with total mass of solute to be injected
    */
   public static String REQ_STATE_MASS = "SoluteMass";
   
   /**
    * Name for state with duration (number of intervals) of injection
    */
   public static String REQ_STATE_DURATION = "DurationTicks";
   
   /**
    * Name for state with the first interval of the injection 
    */
   public static String REQ_STATE_START = "StartTick";
   
   /**
    * Name for state with background concentration
    */
   public static final String NAME_BKG_CONC = "Bkg" + ResourceSolute.NAME_SOLUTE_CONC;
   
   @Override
   public void addRequiredStates()
   {
      super.addRequiredStates();
      addAbstractRequiredState(REQ_STATE_MASS, ValueDouble.class);
      addAbstractRequiredState(REQ_STATE_START, ValueLong.class);
      addAbstractRequiredState(REQ_STATE_DURATION, ValueLong.class);
   }
   
   @Override
   public void addProcessors()
   {
      super.addProcessors();
      addAbstractProcessor(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInject.class, 
            SoluteConcInject.getValueClass()
            );
      addAbstractProcessor(
            NAME_BKG_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInject.getValueClass()
            );
   }

   public String getInjectMassStateName() 
   {
      return resource.getName() + REQ_STATE_MASS;
   }

   public String getDurationStateName() 
   {
      return resource.getName() + REQ_STATE_DURATION;
   }

   public String getStartIterationStateName() 
   {
      return resource.getName() + REQ_STATE_START;
   }

}
