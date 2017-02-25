package org.payn.resources.old.water.gas.boundary.vapor;

import java.util.HashMap;

import org.payn.chsm.Processor;
import org.payn.chsm.Value;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.CurrencyWater;

import neoch.HolonBoundary;
import neoch.behaviors.symmetric.BehaviorNegative;
import neoch.behaviors.symmetric.BehaviorSymmetric;
import neoch.behaviors.symmetric.symmdouble.BehaviorNegativeDouble;

public class BehaviorDiffusion extends BehaviorSymmetric {

   public static final String DIFF_RATE = "diffusionRate";

   public static HashMap<String, Class<? extends Processor>> BEHAVIOR_MAP = 
         new HashMap<String, Class<? extends Processor>>();
   static
   {
      BEHAVIOR_MAP.put(WaterFlow.class.getSimpleName(), WaterFlow.class);
   }
   
   public static HashMap<String, Class<? extends Value>> REQ_STATE_MAP = 
         new HashMap<String, Class<? extends Value>>();
   static
   {
      REQ_STATE_MAP.put(DIFF_RATE, ValueDouble.class);
   }
   
   @Override
   public HashMap<String, Class<? extends Processor>> createProcessorList() 
   {
      return BEHAVIOR_MAP;
   }

   @Override
   public HashMap<String, Class<? extends Value>> getReqStateVars() 
   {
      return REQ_STATE_MAP;
   }

   @Override
   public BehaviorNegative createNegativeBehavior(HolonBoundary boundary) 
   {
      return new BehaviorNegativeDouble(this, boundary);
   }

}
