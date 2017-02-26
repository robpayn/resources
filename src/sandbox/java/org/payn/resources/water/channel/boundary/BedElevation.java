package org.payn.resources.water.channel.boundary;

import org.payn.chsm.State;
import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;

/**
 * Calculates the bed elevation for the boundary
 * 
 * @author v78h241
 *
 */
public class BedElevation extends ProcessorDouble implements InitializerAutoSimple {

   /**
    * Fraction of the length in the local cell
    */
   private ValueDouble lengthFraction;
   
   /**
    * State for the elevation of the local cell
    */
   private State cellElevationLocState;
   
   /**
    * State for the elevation of the adjacent cell
    */
   private State cellElevationAdjState;

   @Override
   public void setInitDependencies() throws Exception 
   {
      lengthFraction = (ValueDouble)createDependencyOnValue(
            BehaviorDynamicWave.NAME_LENGTH_FRACTION
            );

      HolonBoundary parentBoundary = (HolonBoundary)getState().getParentHolon();
      HolonCell cell = parentBoundary.getCell();
      HolonCell cellAdj = parentBoundary.getAdjacentBoundary().getCell();
      
      cellElevationLocState = createDependency(
            cell,
            ResourceWater.NAME_BED_ELEVATION
            );
      cellElevationAdjState = createDependency(
            cellAdj,
            ResourceWater.NAME_BED_ELEVATION
            );
   }

   @Override
   public void initialize() throws Exception 
   {
      ValueDouble cellElevationLoc = 
            (ValueDouble)cellElevationLocState.getValue();
      ValueDouble cellElevationAdj = 
            (ValueDouble)cellElevationAdjState.getValue();
      double defaultValue = 
            (1 - lengthFraction.n) * cellElevationLoc.n 
            + lengthFraction.n * cellElevationAdj.n;
      if (!value.isNoValue() && defaultValue > value.n)
      {
         throw new Exception(String.format(
               "Channel elevation(%s) = %f < IDW average of adjacent cell elevations: "
                     + "Local(%s) = %f, Adjacent(%s) = %f", 
               state.getParentHolon().toString(),
               value.n,
               cellElevationLocState.getParentHolon().toString(),
               cellElevationLoc.n,
               cellElevationAdjState.getParentHolon().toString(),
               cellElevationAdj.n
               ));
      }
      else
      {
         value.n = defaultValue;
      } 
   }

}
