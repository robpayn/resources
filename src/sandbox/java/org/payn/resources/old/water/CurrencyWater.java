package org.payn.resources.old.water;

import org.payn.resources.old.water.liquid.boundary.channelflow.BehaviorChannelFlow;
import org.payn.resources.old.water.liquid.boundary.groundwater.BehaviorGroundwater;
import org.payn.resources.old.water.liquid.cell.channelstorage.BehaviorChannelStorage;
import org.payn.resources.old.water.liquid.cell.unconfinedstorage.BehaviorUnconfinedStorage;

/**
 * Controls behaviors for the water currency
 * 
 * @author robpayn
 *
 */
public class CurrencyWater extends Resource {
   
   @Override
   protected void addBehaviors()
   {
       addBehavior(
               BehaviorUnconfinedStorage.class.getSimpleName(), 
               BehaviorUnconfinedStorage.class.getCanonicalName()
               );
       addBehavior(
               BehaviorGroundwater.class.getSimpleName(), 
               BehaviorGroundwater.class.getCanonicalName()
               );
       addBehavior(
               BehaviorChannelStorage.class.getSimpleName(), 
               BehaviorChannelStorage.class.getCanonicalName()
               );
       addBehavior(
               BehaviorChannelFlow.class.getSimpleName(), 
               BehaviorChannelFlow.class.getCanonicalName()
               );
       
       addBehavior(
               org.payn.resources.old.water.watershed.cell.atmosphere.BehaviorAtmosphere.class.getSimpleName(), 
               org.payn.resources.old.water.watershed.cell.atmosphere.BehaviorAtmosphere.class.getCanonicalName()
               );
       addBehavior(
               org.payn.resources.old.water.watershed.cell.interception.BehaviorInterception.class.getSimpleName(), 
               org.payn.resources.old.water.watershed.cell.interception.BehaviorInterception.class.getCanonicalName()
               );
       addBehavior(
               org.payn.resources.old.water.watershed.cell.surfacedetention.BehaviorSurfaceDetention.class.getSimpleName(), 
               org.payn.resources.old.water.watershed.cell.surfacedetention.BehaviorSurfaceDetention.class.getCanonicalName()
               );
       addBehavior(
               org.payn.resources.old.water.watershed.cell.soilwater.BehaviorSoilWater.class.getSimpleName(), 
               org.payn.resources.old.water.watershed.cell.soilwater.BehaviorSoilWater.class.getCanonicalName()
               );
       
       addBehavior(
               org.payn.resources.old.water.watershed.boundary.precipitation.BehaviorPrecipitation.class.getSimpleName(), 
               org.payn.resources.old.water.watershed.boundary.precipitation.BehaviorPrecipitation.class.getCanonicalName()
               );
       addBehavior(
               org.payn.resources.old.water.watershed.boundary.netprecip.BehaviorNetPrecip.class.getSimpleName(), 
               org.payn.resources.old.water.watershed.boundary.netprecip.BehaviorNetPrecip.class.getCanonicalName()
               );
       addBehavior(
               org.payn.resources.old.water.watershed.boundary.infiltration.BehaviorInfiltration.class.getSimpleName(), 
               org.payn.resources.old.water.watershed.boundary.infiltration.BehaviorInfiltration.class.getCanonicalName()
               );
       
   }

}
