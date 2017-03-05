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

   @Override
   public void addRequiredStates()
   {
      super.addRequiredStates();
      addAbstractRequiredState(ResourceSolute.NAME_INJECT_MASS, ValueDouble.class);
      addAbstractRequiredState(ResourceSolute.NAME_INJECT_START, ValueLong.class);
      addAbstractRequiredState(ResourceSolute.NAME_INJECT_DURATION, ValueLong.class);
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
            ResourceSolute.NAME_BKG_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInject.getValueClass()
            );
   }

}
