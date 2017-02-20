package org.payn.resources.solute.boundary;

import org.payn.chsm.values.ValueString;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.boundary.flow.SoluteConcInterpolate;

/**
 * A boundary condition based on a solute concentration read from an input file
 * 
 * @author robpayn
 *
 */
public class BehaviorSoluteBoundInterp extends BehaviorSoluteBound {

   @Override
   public void addRequiredStates() 
   {
      super.addRequiredStates();
      addAbstractRequiredState(
            SoluteConcInterpolate.REQ_STATE_TYPE, 
            ValueString.class
            );
      addAbstractRequiredState(
            SoluteConcInterpolate.REQ_STATE_PATH, 
            ValueString.class
            );
      addAbstractRequiredState(
            SoluteConcInterpolate.REQ_STATE_DELIMITER, 
            ValueString.class
            );
   }

   @Override
   public void addProcessors() 
   {
      super.addProcessors();
      addAbstractProcessor(
            ResourceSolute.NAME_SOLUTE_CONC, 
            SoluteConcInterpolate.class, 
            SoluteConcInterpolate.getValueClass()
            );
   }

   /**
    * Get the state name for the path
    * 
    * @return
    *       state name
    */
   public String getInterpolationPathStateName() 
   {
      return resource.getName() + SoluteConcInterpolate.REQ_STATE_PATH;
   }

   /**
    * Get the state name for the type of interpolation
    * 
    * @return
    *       state name
    */
   public String getInterpolationTypeStateName() 
   {
      return resource.getName() + SoluteConcInterpolate.REQ_STATE_TYPE;
   }

   /**
    * Get the state name for the delimiter
    * 
    * @return
    *       state name
    */
   public String getInterpolationDelimiterStateName() 
   {
      return resource.getName() + SoluteConcInterpolate.REQ_STATE_DELIMITER;
   }

   public String getConcStateName() 
   {
      return resource.getName() + ResourceSolute.NAME_SOLUTE_CONC;
   }

}
