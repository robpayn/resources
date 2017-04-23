package org.payn.resources.solute.concentration.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.mass.boundary.flow.SoluteConcInject;
import org.payn.resources.solute.mass.boundary.flow.SoluteConcInterpolate;

/**
 * Behavior for an artificial tracer solute injection for a one-dimensional
 * model based on concentration calculations
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteInject extends BehaviorSoluteInterpolate {

   @Override
   public void addProcessors() 
   {
      addProcessorAbstract(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInject.class, 
            SoluteConcInject.getValueClass()
            );
      addProcessorAbstract(
            ResourceSolute.NAME_BKG_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInterpolate.getValueClass()
            );
   }

   @Override
   public void registerStates() 
   {
      super.registerStates();
      registerStateAbstract(
            ResourceSolute.NAME_INJECT_MASS, 
            ValueDouble.class
            );
      registerStateAbstract(
            ResourceSolute.NAME_INJECT_START, 
            ValueLong.class
            );
      registerStateAbstract(
            ResourceSolute.NAME_INJECT_DURATION, 
            ValueLong.class
            );
   }

}
