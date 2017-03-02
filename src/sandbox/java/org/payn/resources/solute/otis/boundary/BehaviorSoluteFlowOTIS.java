package org.payn.resources.solute.otis.boundary;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public class BehaviorSoluteFlowOTIS extends BehaviorMatrix {

   @Override
   protected void addRequiredStates() 
   {
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(ResourceSoluteOTIS.NAME_SOLUTE_CONC, SoluteConc.class, SoluteConc.getValueClass());
   }

}
