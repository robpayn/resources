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
   public static final String REQ_STATE_BKGCONC = "Bkg" + ResourceSolute.NAME_SOLUTE_CONC;
   
   @Override
   public void addRequiredStates()
   {
      super.addRequiredStates();
      addRequiredState(
            resource.getName() + REQ_STATE_MASS, 
            ValueDouble.class
            );
      addRequiredState(
            resource.getName() + REQ_STATE_START, 
            ValueLong.class
            );
      addRequiredState(
            resource.getName() + REQ_STATE_DURATION, 
            ValueLong.class
            );
   }
   
   @Override
   public void addProcessors()
   {
      super.addProcessors();
      addProcessor(ResourceSolute.NAME_SOLUTE_CONC, SoluteConcInject.class, SoluteConcInject.getValueClass());
      addProcessor(REQ_STATE_BKGCONC, SoluteConcInterpolate.class, SoluteConcInject.getValueClass());
   }

}
