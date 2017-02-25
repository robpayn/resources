package org.payn.resources.water.surface.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.behavior.BehaviorMatrix;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.surface.boundary.BedElevation;

public class BehaviorChannelStorage extends BehaviorMatrix {

   /**
    * Name of the state for channel elevation
    */
   public static final String NAME_ELEVATION = BedElevation.class.getSimpleName();

   @Override
   protected void addProcessors() 
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(ResourceWater.NAME_X, ValueDouble.class);
      addRequiredState(ResourceWater.NAME_Y, ValueDouble.class);
   }

}
