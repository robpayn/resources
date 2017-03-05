package org.payn.resources.solute;

import org.payn.chsm.ResourceAbstract;
import org.payn.resources.solute.boundary.otis.BehaviorSoluteFlowBoundOTIS;
import org.payn.resources.solute.boundary.otis.BehaviorSoluteFlowOTIS;
import org.payn.resources.solute.boundary.otis.BehaviorSoluteInjectOTIS;
import org.payn.resources.solute.cell.otis.BehaviorSoluteConcHyperOTIS;
import org.payn.resources.solute.cell.otis.BehaviorSoluteConcOTIS;

/**
 * Solute resource for concentration-based models
 * 
 * @author robpayn
 *
 */
public class ResourceSoluteOTIS extends ResourceAbstract {

   @Override
   public void addBehaviors() 
   {
      addBehavior(
            ResourceSolute.BEHAVIOR_STORAGE, 
            BehaviorSoluteConcOTIS.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_STORAGE_HYPER, 
            BehaviorSoluteConcHyperOTIS.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_FLOW, 
            BehaviorSoluteFlowOTIS.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_CONCBOUND_INJECT,
            BehaviorSoluteInjectOTIS.class.getCanonicalName()
            );
      addBehavior(
            ResourceSolute.BEHAVIOR_FLOWBOUND,
            BehaviorSoluteFlowBoundOTIS.class.getCanonicalName()
            );
   }

}
