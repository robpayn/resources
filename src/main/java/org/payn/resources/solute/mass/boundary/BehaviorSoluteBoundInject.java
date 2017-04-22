package org.payn.resources.solute.mass.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.boundary.flow.SoluteConcInject;
import org.payn.resources.solute.mass.boundary.flow.SoluteConcInterpolate;

/**
 * A boundary condition for simulating an injection at a boundary
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteBoundInject extends BehaviorSoluteBoundInterp {

   @Override
   public void registerStates()
   {
      super.registerStates();
      registerStateAbstract(ResourceSolute.NAME_INJECT_MASS, ValueDouble.class);
      registerStateAbstract(ResourceSolute.NAME_INJECT_START, ValueLong.class);
      registerStateAbstract(ResourceSolute.NAME_INJECT_DURATION, ValueLong.class);
   }
   
   @Override
   public void addProcessors()
   {
      super.addProcessors();
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInject.class, 
            SoluteConcInject.getValueClass()
            );
      addProcessorAbstract(
            ResourceSolute.NAME_BKG_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInject.getValueClass()
            );
   }

}
