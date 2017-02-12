package org.payn.resources.water.watershed.cell.atmosphere;

import org.payn.chsm.processors.interfaces.UpdaterSimple;

import neoch.processors.ProcessorBooleanTrade;

/**
 * Controls whether precipitation is rain
 * 
 * @author v78h241
 *
 */
public class IsPrecipitationRain extends ProcessorBooleanTrade implements UpdaterSimple {

    @Override
    public void setUpdateDependencies() throws Exception
    {
    }

    @Override
    public void update()
    {
    }

}
