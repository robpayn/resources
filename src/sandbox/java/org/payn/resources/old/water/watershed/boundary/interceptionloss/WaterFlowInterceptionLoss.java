package org.payn.resources.old.water.watershed.boundary.interceptionloss;

import org.payn.chsm.values.ValueDouble;
import org.payn.resources.old.water.WaterFlow;

public class WaterFlowInterceptionLoss extends WaterFlow {

    private ValueDouble immediate;
    
    private ValueDouble delayed;
    
    @Override
    public void update()
    {
        value.n = immediate.n + delayed.n;
    }

    @Override
    public void setUpdateDependencies() throws Exception
    {
        immediate = (ValueDouble)createDependency(
                LossImmediate.class.getSimpleName()
                ).getValue();
        delayed = (ValueDouble)createDependency(
                LossDelayed.class.getSimpleName()
                ).getValue();
    }

}
