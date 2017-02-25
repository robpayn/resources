package org.payn.resources.old.water.watershed.boundary.precipitation;

import org.payn.chsm.values.ValueBoolean;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.WaterFlow;
import org.payn.resources.old.water.watershed.cell.atmosphere.IsPrecipitationRain;
import org.payn.resources.old.water.watershed.cell.atmosphere.Precipitation;

import neoch.HolonBoundary;

/**
 * Controls the rainfall
 * 
 * @author robpayn
 *
 */
public class Rainfall extends WaterFlow {

    private ValueDouble precip;
    
    private ValueBoolean isPrecipRain;

    @Override
    public void update()
    {
        if (isPrecipRain.n)
        {
            value.n = precip.n;
        }
        else
        {
            value.n = 0;
        }
    }

    @Override
    public void setUpdateDependencies() throws Exception
    {
        precip = (ValueDouble)createDependency(
                ((HolonBoundary)state.getParentHolon()).getAdjacentBoundary().getCell(),
                Precipitation.class.getSimpleName()
                ).getValue();
        isPrecipRain = (ValueBoolean)createDependency(
                ((HolonBoundary)state.getParentHolon()).getAdjacentBoundary().getCell(),
                IsPrecipitationRain.class.getSimpleName()
                ).getValue();
    }

}
