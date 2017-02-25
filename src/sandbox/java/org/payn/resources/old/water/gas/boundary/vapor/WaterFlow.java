package org.payn.resources.old.water.gas.boundary.vapor;

import org.payn.chsm.processors.interfaces.UpdaterSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.gas.cell.vapor.WaterVolume;

import neoch.HolonBoundary;
import neoch.HolonCell;
import neoch.processors.LoadDouble;

public class WaterFlow extends LoadDouble implements UpdaterSimple {

   private ValueDouble localValue;
   
   private ValueDouble adjValue;

   private ValueDouble diffusionRate;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      diffusionRate = (ValueDouble)createDependency(
            BehaviorDiffusion.DIFF_RATE
            ).getValue();
      HolonCell localCell = ((HolonBoundary)state.getParentHolon()).getCell();
      localValue = (ValueDouble)createDependency(
            localCell, WaterVolume.class.getSimpleName()
            ).getValue();
      HolonCell adjCell = ((HolonBoundary)state.getParentHolon()).getAdjacentBoundary().getCell();
      adjValue = (ValueDouble)createDependency(
            adjCell, WaterVolume.class.getSimpleName()
            ).getValue();
   }

   @Override
   public void update() 
   {
      value.n = 
            diffusionRate.n * 
            (adjValue.n - localValue.n);
   }

}
