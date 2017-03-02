package org.payn.resources.solute.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.BehaviorMatrix;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteAdvectionBound;
import org.payn.resources.solute.boundary.flow.SoluteDispersionBound;

/**
 * Basic advection-dispersion boundary based on interpolated concentration input data
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteBound extends BehaviorMatrix {

   @Override
   public void addRequiredStates() 
   {
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_DISP, ValueDouble.class);
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_FLOW, ValueDouble.class);
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_LENGTH, ValueDouble.class);
      addRequiredState(BehaviorSoluteFlow.REQ_STATE_AREA_XSECT, ValueDouble.class);
   }
   
   @Override
   public void addProcessors() 
   {
      addAbstractProcessor(
            ResourceSolute.NAME_ADVECTION, 
            SoluteAdvectionBound.class, 
            SoluteAdvectionBound.getValueClass()
            );
      addAbstractProcessor(
            ResourceSolute.NAME_DISPERSION, 
            SoluteDispersionBound.class, 
            SoluteDispersionBound.getValueClass()
            );
   }

}
