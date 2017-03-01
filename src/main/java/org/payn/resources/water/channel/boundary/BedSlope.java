package org.payn.resources.water.channel.boundary;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;

/**
 * Calculate the bed slope
 * 
 * @author robpayn
 *
 */
public class BedSlope extends ProcessorDouble implements InitializerAutoSimple {

   /**
    * Link length
    */
   private ValueDouble linkLength;
   
   /**
    * Elevation of local cell
    */
   private ValueDouble cellElevationLoc;
   
   /**
    * Elevation of adjacent cell
    */
   private ValueDouble cellElevationAdj;

   @Override
   public void setInitDependencies() throws Exception 
   {
      linkLength = (ValueDouble)createDependencyOnValue(
            BehaviorDynamicWave.NAME_LINK_LENGTH
            );

      HolonBoundary parentBoundary = (HolonBoundary)getState().getParentHolon();
      HolonCell cellLoc = parentBoundary.getCell();
      HolonCell cellAdj = parentBoundary.getAdjacentBoundary().getCell();
      
      cellElevationLoc = (ValueDouble)createDependencyOnValue(
            cellLoc,
            ResourceWater.NAME_BED_ELEVATION
            );
      cellElevationAdj = (ValueDouble)createDependencyOnValue(
            cellAdj,
            ResourceWater.NAME_BED_ELEVATION
            );
   }

   @Override
   public void initialize() throws Exception 
   {
      if (linkLength.n <= 0.0) 
      {
         throw new Exception(String.format(
               "LINKLENGTH <= 0 in boundary %s", 
               state.getParentHolon().toString()
               ));
      }
      value.n = (cellElevationAdj.n - cellElevationLoc.n) / linkLength.n;
   }

}
