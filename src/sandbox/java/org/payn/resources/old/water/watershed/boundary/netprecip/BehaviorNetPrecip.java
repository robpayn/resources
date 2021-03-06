package org.payn.resources.old.water.watershed.boundary.netprecip;

import org.payn.resources.old.water.WaterFlow;

import neoch.behaviors.symmetric.symmdouble.BehaviorSymmetricDouble;

/**
 * Defines the behavior of net precipitation
 * 
 * @author robpayn
 *
 */
public class BehaviorNetPrecip extends BehaviorSymmetricDouble {

    @Override
    protected void addRequiredStates()
    {
    }

    @Override
    protected void addProcessors()
    {
        addProcessor(WaterFlow.class.getSimpleName(), WaterFlowNetPrecip.class);
    }

}
