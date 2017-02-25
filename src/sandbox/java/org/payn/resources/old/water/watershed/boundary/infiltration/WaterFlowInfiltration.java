package org.payn.resources.old.water.watershed.boundary.infiltration;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.WaterFlow;
import org.payn.resources.old.water.watershed.cell.surfacedetention.WaterTotalAvailable;

import neoch.HolonBoundary;

/**
 * Controls movement of water due to infiltration 
 * 
 * @author robpayn
 *
 */
public class WaterFlowInfiltration extends WaterFlow {

    /**
     * Total available water for infiltration value
     */
    private ValueDouble totalAvailable;
    
    /**
     * Maximum rate of infiltration
     */
    private ValueDouble maxRate;

    @Override
    public void setUpdateDependencies() throws Exception
    {
        totalAvailable = (ValueDouble)createDependency(
                ((HolonBoundary)state.getParentHolon()).getAdjacentBoundary().getCell(),
                WaterTotalAvailable.class.getSimpleName()
                ).getValue();
        maxRate = (ValueDouble)createDependency(
                BehaviorInfiltration.REQ_STATE_MAX_INF
                ).getValue();
    }

    @Override
    public void update()
    {
        if(totalAvailable.n < maxRate.n)
        {
            value.n = totalAvailable.n;
        }
        else
        {
            value.n = maxRate.n;
        }
    }

}
