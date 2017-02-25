package org.payn.resources.old.water.liquid.cell.channelstorage;

import org.payn.resources.old.water.WaterStorage;

import neoch.behaviors.BehaviorMatrix;

/**
 * Behavior for storage of water in the channel
 * 
 * @author v78h241
 *
 */
public class BehaviorChannelStorage extends BehaviorMatrix {

   @Override
   protected void addRequiredStates()
   {
       // No required states
   }

    @Override
    protected void addProcessors()
    {
        addProcessor(WaterStorage.class.getSimpleName(), WaterStorage.class);
    }

}
