package org.payn.resources.old.water.gas.cell.vapor;

import java.util.HashMap;

import org.payn.chsm.Processor;
import org.payn.chsm.Value;

import neoch.behaviors.BehaviorMatrix;

public class BehaviorVapor extends BehaviorMatrix {

   public static HashMap<String, Class<? extends Processor>> BEHAVIOR_MAP = 
         new HashMap<String, Class<? extends Processor>>();
   static
   {
      BEHAVIOR_MAP.put(WaterVolume.class.getSimpleName(), WaterVolume.class);
   }
   
   @Override
   public HashMap<String, Class<? extends Processor>> createProcessorList() 
   {
      return BEHAVIOR_MAP;
   }

   @Override
   public HashMap<String, Class<? extends Value>> getReqStateVars() 
   {
      return null;
   }

}
