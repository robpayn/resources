package org.payn.resources.water.channel.boundary;

import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.neoch.processors.ProcessorDoubleTrade;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;

/**
 * Calcualte the hydraulic gradient 
 * 
 * @author robpayn
 *
 */
public class HydraulicGradient extends ProcessorDoubleTrade implements InitializerAutoSimple {

   /**
    * Depth of the channel flow
    */
   private ValueDouble depth;
   
   /**
    * Head in the local cell
    */
   private ValueDouble headLocal;
   
   /**
    * Head in the adjacent cell
    */
   private ValueDouble headAdjacent;
   
   /**
    * Length between cells
    */
   private ValueDouble linkLength;

   @Override
   public void setInitDependencies() throws Exception 
   {
      setUpdateDependencies();
   }

   @Override
   public void initialize() throws Exception 
   {
      update();
   }

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      depth = (ValueDouble)createDependencyOnValue(
            ResourceWater.NAME_DEPTH
            );
      linkLength = (ValueDouble)createDependencyOnValue(
            BehaviorDynamicWave.NAME_LINK_LENGTH
            );
      
      HolonBoundary parentBoundary = (HolonBoundary)getState().getParentHolon();
      HolonCell cellLoc = parentBoundary.getCell();
      HolonCell cellAdj = parentBoundary.getAdjacentBoundary().getCell();

      headLocal = (ValueDouble)createDependencyOnValue(
            cellLoc,
            ResourceWater.NAME_WATER_HEAD
            );
      headAdjacent = (ValueDouble)createDependencyOnValue(
            cellAdj,
            ResourceWater.NAME_WATER_HEAD
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (depth.n <= 0)
      {
         value.n = 0;
      }
      else
      {
         value.n = (headLocal.n - headAdjacent.n) / linkLength.n;
      }
   }

}
