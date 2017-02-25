package org.payn.resources.old.water.watershed.cell.atmosphere;

import neoch.behaviors.BehaviorMatrix;

/**
 * Defines atmospheric boundary conditions
 * 
 * @author robpayn
 *
 */
public class BehaviorAtmosphere extends BehaviorMatrix {

    @Override
    protected void addRequiredStates()
    {
    }

    @Override
    protected void addProcessors()
    {
        addProcessor(Precipitation.class.getSimpleName(), Precipitation.class);
        addProcessor(IsPrecipitationRain.class.getSimpleName(), IsPrecipitationRain.class);
    }

}
