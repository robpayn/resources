package org.payn.resources.snow.boundary.precipitation;

import neoch.behaviors.BehaviorMatrix;

/**
 * Behavior that controls snow fall
 * 
 * @author robpayn
 *
 */
public class BehaviorPrecipitation extends BehaviorMatrix {

    @Override
    protected void addRequiredStates()
    {
        //FIXME: Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    protected void addProcessors()
    {
        addProcessor(Snowfall.class.getSimpleName(), Snowfall.class);
    }

}
