package org.payn.resources.water.channel.boundary;

import org.payn.chsm.processors.auto.ProcessorDoubleChangeInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.resources.water.ResourceWater;

/**
 * Calcualte the hydraulic gradient 
 * 
 * @author robpayn
 *
 */
public class HydraulicGradient extends ProcessorDoubleChangeInit {

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
            ResourceWater.DEFAULT_NAME_DEPTH
            );
      linkLength = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_LENGTH_BOUND
            );
      
      HolonBoundary parentBoundary = (HolonBoundary)getState().getParentHolon();
      HolonCell cellLoc = parentBoundary.getCell();
      HolonCell cellAdj = parentBoundary.getAdjacentBoundary().getCell();

      headLocal = (ValueDouble)createDependencyOnValue(
            cellLoc,
            ResourceWater.DEFAULT_NAME_HEAD
            );
      headAdjacent = (ValueDouble)createDependencyOnValue(
            cellAdj,
            ResourceWater.DEFAULT_NAME_HEAD
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (depth.n <= 0.0)
      {
         value.n = 0.0;
      }
      else
      {
         value.n = (headAdjacent.n - headLocal.n) / linkLength.n;
      }
   }

}
