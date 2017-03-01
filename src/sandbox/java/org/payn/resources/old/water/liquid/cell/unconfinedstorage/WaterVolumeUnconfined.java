package org.payn.resources.old.water.liquid.cell.unconfinedstorage;

import org.payn.chsm.processors.interfaces.InitializerSimpleAuto;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.WaterStorage;
import org.payn.resources.old.water.liquid.cell.HydraulicHead;

/**
 * Volume of water in an unconfined cell
 * 
 * @author robpayn
 *
 */
public class WaterVolumeUnconfined extends WaterStorage implements InitializerSimpleAuto {

   /**
    * Hydraulic head in the cell
    */
   private ValueDouble head;
   
   /**
    * Plan area of the cell
    */
   private ValueDouble area;

   /**
    * Get the values necessary to initialize volume from head
    */
   @Override
   public void setInitDependencies() throws Exception 
   {
      head = (ValueDouble)createDependency(HydraulicHead.class.getSimpleName()).getValue();
      area = (ValueDouble)createDependency(BehaviorUnconfinedStorage.REQ_STATE_PLAN_AREA).getValue();
   }

   /** 
    * Initialize the water volume
    */
   @Override
   public void initialize() 
   {
      value.n = head.n * area.n;
   }

}
