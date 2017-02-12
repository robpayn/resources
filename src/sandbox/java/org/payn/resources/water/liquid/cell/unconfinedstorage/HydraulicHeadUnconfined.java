package org.payn.resources.water.liquid.cell.unconfinedstorage;

import org.payn.chsm.processors.interfaces.UpdaterSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.WaterStorage;
import org.payn.resources.water.liquid.cell.HydraulicHead;

/**
 * Tracks the total hydraulic head in an unconfined cell with straight sides
 * 
 * @author robpayn
 *
 */
public class HydraulicHeadUnconfined extends HydraulicHead implements UpdaterSimple {

   /**
    * Volume of water in the cell
    */
   private ValueDouble volume;
   
   /**
    * Plan area of the cell
    */
   private ValueDouble planArea;

   /**
    * Get the values necessary to calculate hydraulic head
    */
   @Override
   public void setUpdateDependencies() throws Exception 
   {
      planArea = (ValueDouble)createDependency(
            BehaviorUnconfinedStorage.REQ_STATE_PLAN_AREA
            ).getValue();
      volume = (ValueDouble)createDependency(
            WaterStorage.class.getSimpleName()
            ).getValue();
   }

   /**
    * Updates the value assuming that total hydraulic head is directly proportional to volume of water.
    */
   @Override
   public void update() 
   {
      value.n = volume.n / planArea.n;
   }

}
