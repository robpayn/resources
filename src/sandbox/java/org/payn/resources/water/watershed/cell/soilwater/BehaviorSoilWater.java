package org.payn.resources.water.watershed.cell.soilwater;

import org.payn.resources.water.WaterStorage;

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
