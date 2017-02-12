package org.payn.resources.water.watershed.boundary.netprecip;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.water.WaterFlow;
import org.payn.resources.water.watershed.cell.interception.Throughfall;

import neoch.HolonBoundary;

/**
 * Controls the flow of water in net precipitation
 * 
 * @author v78h241
 *
 */
public class WaterFlowNetPrecip extends WaterFlow {

    /**
     * Throughfall value
     */
    private ValueDouble throughfall;

    @Override
    public void setUpdateDependencies() throws Exception
    {
        throughfall = (ValueDouble)createDependency(
                ((HolonBoundary)state.getParentHolon()).getAdjacentBoundary().getCell(),
                Throughfall.class.getSimpleName()
                ).getValue();
    }

    @Override
    public void update()
    {
        value.n = throughfall.n;
    }

}
