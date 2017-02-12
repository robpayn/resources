package org.payn.resources.snow.boundary.netprecip;

import org.payn.chsm.processors.interfaces.UpdaterSimple;

import neoch.processors.LoadDouble;

/**
 * Controls the flow of water in net precipitation
 * 
 * @author v78h241
 *
 */
public class SnowfallNetPrecip extends LoadDouble implements UpdaterSimple {

    @Override
    public void setUpdateDependencies() throws Exception
    {
    }

    @Override
    public void update()
    {
    }

}
