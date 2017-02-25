package org.payn.resources.old.water.watershed.boundary.infiltration;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.WaterFlow;

import neoch.behaviors.symmetric.symmdouble.BehaviorSymmetricDouble;

/**
 * Behavior controlling infiltration of water
 * 
 * @author robpayn
 *
 */
public class BehaviorInfiltration extends BehaviorSymmetricDouble {

    /**
     * Name of parameter for maximum rate
     */
    public static final String REQ_STATE_MAX_INF = "MaxRate";

    @Override
    protected void addRequiredStates()
    {
        addRequiredState(REQ_STATE_MAX_INF, ValueDouble.class);
    }

    @Override
    protected void addProcessors()
    {
        addProcessor(
                WaterFlow.class.getSimpleName(), 
                WaterFlowInfiltration.class
                );
    }

}
