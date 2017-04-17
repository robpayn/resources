package org.payn.resources.solute.gas;

import org.payn.chsm.Holon;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;
import org.payn.neoch.processors.ProcessorDoubleLoad;
import org.payn.resources.solute.ResourceSolute;
import org.payn.resources.water.ResourceWater;

public class GasAWExchange extends ProcessorDoubleLoad {

   private ValueDouble awExchangeVelocity;

   @Override
   public void setUpdateDependenciesLoad() throws Exception 
   {
      String holonName = ((ValueString)createDependencyOnValue(
            ResourceSolute.DEFAULT_NAME_AWEXCHANGE_HOLON
            )).string;
      awExchangeVelocity = (ValueDouble)createDependencyOnValue(
            (Holon)((Holon)getController().getState()).getState(holonName),
            ResourceSolute.DEFAULT_NAME_AW_EXCH_VELOCITY
            );
   }

   @Override
   public void updateLoad() throws Exception 
   {
      value.n = awExchangeVelocity.n * satDef.v * surfArea.getValue();
   }

}
