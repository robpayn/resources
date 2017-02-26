package org.payn.resources.water.channel.boundary.dynamicwave;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.BehaviorChannelFlow;

/**
 * Behavior of surface water according to the dynamic wave equations
 * 
 * @author v78h241
 *
 */
public class BehaviorDynamicWave extends BehaviorChannelFlow {

   /**
    * Name of the state for cross-sectional area of channel flow
    */
   public static final String NAME_XSECT_AREA = XSectAreaCurrent.class.getSimpleName();

   /**
    * Name of the state for the previous cross-sectional area
    */
   public static final String NAME_XSECT_AREA_PREV = XSectAreaPrevious.class.getSimpleName();

   /**
    * Name of the state for velocity
    */
   public static final String NAME_VELOCITY = Velocity.class.getSimpleName();

   /**
    * Name of the state for the Chezey coefficient
    */
   public static final String NAME_CHEZEY = Chezey.class.getSimpleName();

   /**
    * Name of the state for the velocity exponent
    */
   public static final String REQ_STATE_VELOCITY_EXP = "VelocityExp";

   /**
    * Name of the state for the radius exponent
    */
   public static final String REQ_STATE_RADIUS_EXP = "RadiusExp";

   @Override
   protected void addProcessors() 
   {
      super.addProcessors();
      addProcessor(ResourceWater.NAME_WATER_FLOW, WaterFlow.class, ValueDouble.class);
      addProcessor(NAME_XSECT_AREA, XSectAreaCurrent.class, ValueDouble.class);
      addProcessor(NAME_XSECT_AREA_PREV, XSectAreaPrevious.class, ValueDouble.class);
      addProcessor(NAME_VELOCITY, Velocity.class, ValueDouble.class);
   }

   @Override
   protected void addRequiredStates() 
   {
      addRequiredStatesChannelFlow();
      addRequiredState(NAME_CHEZEY, ValueDouble.class);
      addRequiredState(REQ_STATE_VELOCITY_EXP, ValueDouble.class);
      addRequiredState(REQ_STATE_RADIUS_EXP, ValueDouble.class);
   }
   
}
