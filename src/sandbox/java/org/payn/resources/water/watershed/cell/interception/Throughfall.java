package org.payn.resources.water.watershed.cell.interception;

import org.payn.chsm.processors.interfaces.UpdaterSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.resources.snow.boundary.precipitation.BehaviorPrecipitation;
import org.payn.resources.water.WaterFlow;

import neoch.HolonBoundary;
import neoch.HolonCell;
import neoch.processors.ProcessorDoubleTrade;

public class Throughfall extends ProcessorDoubleTrade implements UpdaterSimple {

    private ValueDouble precip;
    
    private ValueDouble rate;

    @Override
    public void setUpdateDependencies() throws Exception
    {
        HolonBoundary bound = ((HolonCell)state.getParentHolon()).getBoundaries(
                "water." + BehaviorPrecipitation.class.getSimpleName()
                ).get(0);
        precip = (ValueDouble)createDependency(
                bound,
                WaterFlow.class.getSimpleName()
                ).getValue();
        rate = (ValueDouble)createDependency(
                BehaviorInterception.REQ_STATE_RATE
                ).getValue();
    }

    @Override
    public void update()
    {
        value.n = precip.n * (1 - rate.n);
    }

}
