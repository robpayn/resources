package org.payn.resources.water.surface.boundary;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.resources.water.surface.boundary.dynamicwave.BehaviorDynamicWave;
import org.payn.resources.water.surface.cell.BehaviorChannelStorage;

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
      linkLength = (ValueDouble)createDependency(
            BehaviorDynamicWave.NAME_LINK_LENGTH
            ).getValue();

      HolonBoundary parentBoundary = (HolonBoundary)getState().getParentHolon();
      HolonCell cell = parentBoundary.getCell();
      HolonCell cellAdj = parentBoundary.getAdjacentBoundary().getCell();
      
      cellElevationLoc = (ValueDouble)createDependency(
            cell,
            BehaviorChannelStorage.NAME_ELEVATION
            ).getValue();
      cellElevationAdj = (ValueDouble)createDependency(
            cellAdj,
            BehaviorChannelStorage.NAME_ELEVATION
            ).getValue();
   }

   @Override
   public void initialize() throws Exception 
   {
      if (linkLength.n <= 0) 
      {
         throw new Exception(String.format(
               "LINKLENGTH <= 0 in boundary %s", 
               state.getParentHolon().toString()
               ));
      }
      value.n = (cellElevationLoc.n - cellElevationAdj.n) / linkLength.n;
   }

}
