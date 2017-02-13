package org.payn.resources.particleold.cell;

import org.payn.chsm.Controller;
import org.payn.chsm.Holon;
import org.payn.chsm.values.ValueString;
import org.payn.resources.particle.ResourceParticle;

public abstract class BehaviorConcTracker extends BehaviorParticle {

   @Override
   public void createBehaviorProcessors(Holon holon, Controller controller) throws Exception 
   {
      super.createBehaviorProcessors(holon, controller);
      ParticleManager manager = (ParticleManager)holon.getState(resource.getName() + ResourceParticle.NAME_MANAGER).getProcessor();
      String[] currencyNames = ((ValueString)holon.getState(
            BehaviorConcTrackerLagrange.REQ_STATE_CURRENCY
            ).getValue()).string.split(",");
      for (String currencyName: currencyNames)
      {
         ParticleMean meanProc = new ParticleMean();
         ParticleTimeSeries timeSeriesProc = new ParticleTimeSeries();
         installProcessor(holon, controller, resource.getName() + currencyName + ResourceParticle.NAME_MEAN, meanProc);
         installProcessor(holon, controller, resource.getName() + currencyName + ResourceParticle.NAME_TIME_SERIES, timeSeriesProc);
         manager.addCurrencyWatch(currencyName, meanProc, timeSeriesProc);
      }
   }   

}
