package org.payn.resources.solute.otis.cell;

import org.payn.chsm.values.ValueDouble;

public class SoluteDispersion extends SoluteLoad {

   private ValueDouble length;
   private ValueDouble dispersion;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      dispersion = (ValueDouble)createDependency(
            BehaviorSoluteConcOTIS.REQ_STATE_DISP
            ).getValue();
      length = (ValueDouble)createDependency(
            BehaviorSoluteConcOTIS.REQ_STATE_LENGTH
            ).getValue();
      setConcentrations();
   }

   @Override
   public void update() throws Exception 
   {
      value.n = (dispersion.n * (downstreamConc.n + upstreamConc.n)) / (length.n * length.n);
   }

}
