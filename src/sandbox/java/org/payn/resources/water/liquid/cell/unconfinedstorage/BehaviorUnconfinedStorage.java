package org.payn.resources.water.liquid.cell.unconfinedstorage;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.WaterStorage;
import org.payn.resources.water.liquid.cell.HydraulicHead;

import neoch.behaviors.BehaviorMatrix;

/**
 * Unconfined storage of water
 * 
 * @author robpayn
 *
 */
public class BehaviorUnconfinedStorage extends BehaviorMatrix {

   /**
    * Plan area of the cell where water is stored
    */
   public static final String REQ_STATE_PLAN_AREA = "PlanArea";
   
   @Override
   protected void addRequiredStates()
   {
       reqStateMap.put(REQ_STATE_PLAN_AREA, ValueDouble.class);
   }

   @Override
   protected void addProcessors()
   {
       processorMap.put(WaterStorage.class.getSimpleName(), WaterVolumeUnconfined.class);
       processorMap.put(HydraulicHead.class.getSimpleName(), HydraulicHeadUnconfined.class);
   }
   
}
