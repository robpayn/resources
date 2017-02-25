package org.payn.resources.old.water.watershed.cell.soilwater;

import org.payn.resources.old.water.WaterStorage;

import neoch.behaviors.BehaviorMatrix;

/**
 * Behavior controlling storage of water in soil
 * 
 * @author robpayn
 *
 */
public class BehaviorSoilWater extends BehaviorMatrix {

    @Override
    protected void addRequiredStates()
    {
    }

    @Override
    protected void addProcessors()
    {
        addProcessor(
                WaterStorage.class.getSimpleName(), 
                WaterStorageSoilWater.class
                );
    }

}
