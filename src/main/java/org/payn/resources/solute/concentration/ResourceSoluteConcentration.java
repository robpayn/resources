package org.payn.resources.solute.concentration;

import org.payn.chsm.resources.ResourceAbstract;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.solute.concentration.boundary.BehaviorSoluteFlow;
import org.payn.resources.solute.concentration.boundary.BehaviorSoluteFlowBound;
import org.payn.resources.solute.concentration.boundary.BehaviorSoluteInject;
import org.payn.resources.solute.concentration.boundary.BehaviorSoluteInterpolate;
import org.payn.resources.solute.concentration.cell.BehaviorSoluteConc;
import org.payn.resources.solute.concentration.cell.BehaviorSoluteConcHyper;

/**
 * Solute resource for concentration-based models
 * 
 * @author robpayn
 *
 */
public class ResourceSoluteConcentration extends ResourceAbstract {

   @Override
   public void addBehaviors() 
   {
      addBehavior(
            ResourceSolute.BEHAVIOR_STORAGE, 
            BehaviorSoluteConc.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_STORAGE_HYPER, 
            BehaviorSoluteConcHyper.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_FLOW, 
            BehaviorSoluteFlow.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_CONCBOUND_INJECT,
            BehaviorSoluteInject.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_CONCBOUND,
            BehaviorSoluteInterpolate.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_FLOWBOUND,
            BehaviorSoluteFlowBound.class.getCanonicalName()
            );
   }

}
