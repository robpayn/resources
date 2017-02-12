package org.payn.resources.water.watershed.boundary.precipitation;

import org.payn.resources.water.WaterFlow;

import neoch.behaviors.BehaviorMatrix;

/**
 * Boundary behavior for intercepted precipitation
 * 
 * @author robpayn
 *
 */
public class BehaviorPrecipitation extends BehaviorMatrix {

    @Override
    protected void addRequiredStates()
    {
    }

    @Override
    protected void addProcessors()
    {
        processorMap.put(WaterFlow.class.getSimpleName(), Rainfall.class);
    }

}
