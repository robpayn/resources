package org.payn.resources.solute.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteAdvectionBound;
import org.payn.resources.solute.boundary.flow.SoluteDispersionBound;

import neoch.behaviors.BehaviorMatrix;

/**
 * Basic advection-dispersion boundary based on interpolated concentration input data
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteBound extends BehaviorMatrix {

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_DISP, ValueDouble.class);
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_FLOW, ValueDouble.class);
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_LENGTH, ValueDouble.class);
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_AREA_XSECT, ValueDouble.class);
   }
   
   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceSolute.NAME_ADVECTION, SoluteAdvectionBound.class, SoluteAdvectionBound.getValueClass());
      addProcessor(ResourceSolute.NAME_DISPERSION, SoluteDispersionBound.class, SoluteDispersionBound.getValueClass());
   }

}
