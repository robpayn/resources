package org.payn.resources.water.surface.boundary.dynamicwave;

import org.payn.chsm.values.ValueDouble;

/**
 * Dynamic wave behavior based on Wiele power relationship between depth and friction
 * 
 * @author v78h241
 *
 */
public class BehaviorDynamicWaveWiele extends BehaviorDynamicWave {

   /**
    * Name of the state for the friction factor
    */
   public static final String NAME_FRICTION = Friction.class.getSimpleName();

   /**
    * Name of required state for the Wiele model intercept
    */
   public static final String REQ_STATE_WIELEINT = "WieleInt";

   /**
    * Name of the required state for the Wiele model slope
    */
   public static final String REQ_STATE_WIELESLOPE = "WieleSlope";

   @Override
   protected void addProcessors() 
   {
      super.addProcessors();
      addProcessor(NAME_CHEZEY, Chezey.class, ValueDouble.class);
      addProcessor(NAME_FRICTION, Friction.class, ValueDouble.class);
   }

   @Override
   protected void addRequiredStates() 
   {
      super.addRequiredStatesChannelFlow();
      addRequiredState(REQ_STATE_WIELEINT, ValueDouble.class);
      addRequiredState(REQ_STATE_WIELESLOPE, ValueDouble.class);
   }

}
