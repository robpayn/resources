package org.payn.resources.water.channel.boundary;

import org.payn.chsm.processors.finitedifference.ProcessorDoubleChangeInit;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.resources.water.ResourceWater;

/**
 * Calculates the depth of water in the channel flow
 * 
 * @author v78h241
 *
 */
public class Depth extends ProcessorDoubleChangeInit {

   /**
    * Water volume in the local cell
    */
   private ValueDouble cellVolumeLoc;
   
   /**
    * Waver volume in the adjacent cell
    */
   private ValueDouble cellVolumeAdj;
   
   /**
    * Fraction of the length between adjacent cells
    */
   private ValueDouble lengthFraction;

   /**
    * Head in the local cell
    */
   private ValueDouble cellHeadLoc;
   
   /**
    * Head in the adjacent cell
    */
   private ValueDouble cellHeadAdj;

   /**
    * Bed elevation at the boundary
    */
   private ValueDouble bedElevation;

   /**
    * Water depth in the local cell
    */
   private ValueDouble cellDepthLoc;
   
   /**
    * Water depth in the adjacent cell
    */
   private ValueDouble cellDepthAdj;

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
      lengthFraction = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFUALT_NAME_LENGTH_FRACTION
            );
      bedElevation = (ValueDouble)createDependencyOnValue(
            ResourceWater.DEFAULT_NAME_BED_ELEV
            );
      
      HolonBoundary parentBoundary = (HolonBoundary)getState().getParentHolon();
      HolonCell cell = parentBoundary.getCell();
      HolonCell cellAdj = parentBoundary.getAdjacentBoundary().getCell();
      
      cellVolumeLoc = (ValueDouble)createDependencyOnValue(
            cell,
            ResourceWater.DEFAULT_NAME_VOLUME
            );
      cellVolumeAdj = (ValueDouble)createDependencyOnValue(
            cellAdj,
            ResourceWater.DEFAULT_NAME_VOLUME
            );
      
      cellHeadLoc = (ValueDouble)createDependencyOnValue(
            cell,
            ResourceWater.DEFAULT_NAME_HEAD
            );
      cellHeadAdj = (ValueDouble)createDependencyOnValue(
            cellAdj,
            ResourceWater.DEFAULT_NAME_HEAD
            );
      
      cellDepthLoc = (ValueDouble)createDependencyOnValue(
            cell,
            ResourceWater.DEFAULT_NAME_DEPTH
            );
      cellDepthAdj = (ValueDouble)createDependencyOnValue(
            cellAdj,
            ResourceWater.DEFAULT_NAME_DEPTH
            );
   }

   @Override
   public void update() throws Exception 
   {
      if (cellVolumeLoc.n <= 0.0 && cellVolumeAdj.n <= 0.0)
      {
         value.n = 0.0;
      }
      else
      {
         // interpolate depth between heads in adjacent cells
         double interpDepth = 
               ((1.0 - lengthFraction.n) * cellHeadLoc.n + lengthFraction.n * cellHeadAdj.n) 
               - bedElevation.n;

         // interpolated depth should never be deeper than depth of source
         // patch, so determine source head
         double sourceHead;
         double sourceDepth;
         double sourceLengthFraction;
         if (cellHeadLoc.n > cellHeadAdj.n)
         {
            sourceHead = cellHeadLoc.n;
            sourceDepth = cellDepthLoc.n;
            sourceLengthFraction = (1.0 - lengthFraction.n);
         }
         else
         {
            sourceHead = cellHeadAdj.n;
            sourceDepth = cellDepthAdj.n;
            sourceLengthFraction = lengthFraction.n;
         }

         // determine flow depth from source cell, and make sure interpolated
         // depth is not greater
         sourceDepth = Math.min(sourceDepth, sourceHead - bedElevation.n);
         interpDepth = Math.min(sourceDepth, interpDepth);

         // determine if source head alone is sufficient to cause flow (e.g. into dry channel).
         // If distance weighting (determined by sourceLengthFraction) is too large, model instability can
         // result, so only make this check when distance weighting is < 0.9. When distance
         // weighting is > 0.9, interpolated depth is sufficient to force flow into a dry channel.
         if (sourceLengthFraction < 0.9)
         {
            sourceDepth = sourceDepth * sourceLengthFraction;
         }
         else
         {
            sourceDepth = 0;
         }
         
         // Use the deepest of the potential depths
         value.n = Math.max(sourceDepth, Math.max(interpDepth, 0));
      }
   }

}
