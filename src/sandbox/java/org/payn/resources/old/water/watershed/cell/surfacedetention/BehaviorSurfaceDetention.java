package org.payn.resources.old.water.watershed.cell.surfacedetention;

import org.payn.resources.old.water.WaterStorage;

import neoch.behaviors.BehaviorMatrix;

/**
 * Behavior controlling storage of water on surface
 * 
 * @author robpayn
 *
 */
public class BehaviorSurfaceDetention extends BehaviorMatrix {

    @Override
    protected void addRequiredStates()
    {
    }

    @Override
    protected void addProcessors()
    {
        addProcessor(
                WaterStorage.class.getSimpleName(), 
                WaterStorageDetention.class
                );
        addProcessor(
                WaterTotalAvailable.class.getSimpleName(), 
                WaterTotalAvailable.class
                );
    }

}
