package org.payn.resources.old.water.watershed.cell.interception;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.WaterStorage;

import neoch.behaviors.BehaviorMatrix;

/**
 * Behavior controlling interception storage
 * 
 * @author robpayn
 *
 */
public class BehaviorInterception extends BehaviorMatrix {
    
    /**
     * Name of parameter for interception rate
     */
    public static final String REQ_STATE_RATE = "InterceptionRate";

    @Override
    protected void addRequiredStates()
    {
        addRequiredState(REQ_STATE_RATE, ValueDouble.class);
    }

    @Override
    protected void addProcessors()
    {
        addProcessor(WaterStorage.class.getSimpleName(), WaterStorageInterception.class);
        addProcessor(Throughfall.class.getSimpleName(), Throughfall.class);
    }

}
