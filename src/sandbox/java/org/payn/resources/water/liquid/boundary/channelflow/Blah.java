package org.payn.resources.water.liquid.boundary.channelflow;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.UpdaterSimple;

import neoch.UpdaterTrade;

public class Blah extends ProcessorDouble implements UpdaterTrade, UpdaterSimple {

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      createDependency("WaterFlow");
   }

   @Override
   public void update() {
      // TODO Auto-generated method stub
      
   }

}
