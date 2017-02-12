package org.payn.resources.snow.boundary.snowmelt;

import org.payn.chsm.processors.interfaces.UpdaterSimple;

import neoch.processors.LoadDouble;

/**
 * Behavior for snow melting.  Part of asymmetric behavior with water snowmelt.
 * 
 * @author v78h241
 *
 */
public class Snowmelt extends LoadDouble implements UpdaterSimple {

    @Override
    public void setUpdateDependencies() throws Exception
    {
    }

    @Override
    public void update()
    {
    }

}
