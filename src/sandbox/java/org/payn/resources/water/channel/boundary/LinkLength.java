package org.payn.resources.water.channel.boundary;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.HolonBoundary;
import org.payn.neoch.HolonCell;
import org.payn.resources.water.ResourceWater;
import org.payn.resources.water.channel.boundary.dynamicwave.BehaviorDynamicWave;

/**
 * Calculate the length of the link
 * 
 * @author robpayn
 *
 */
public class LinkLength extends ProcessorDouble implements InitializerAutoSimple {

   /**
    * Length in local cell
    */
   private ValueDouble lengthLoc;
   
   /**
    * Length in adjacent cell
    */
   private ValueDouble lengthAdj;
   
   /**
    * X coordinate of local cell
    */
   private ValueDouble xLocal;
   
   /**
    * X coordinate in adjacent cell
    */
   private ValueDouble xAdjacent;
   
   /**
    * Y coordinate in local cell
    */
   private ValueDouble yLocal;
   
   /**
    * Y coordinate in adjacent cell
    */
   private ValueDouble yAdjacent;

   @Override
   public void setInitDependencies() throws Exception 
   {
      HolonBoundary parentBoundary = (HolonBoundary)getState().getParentHolon();
      HolonCell cellLocal = parentBoundary.getCell();
      HolonCell cellAdj = parentBoundary.getAdjacentBoundary().getCell();
      
      xLocal = (ValueDouble)createDependencyOnValue(
            cellLocal,
            ResourceWater.NAME_X
            );
      xAdjacent = (ValueDouble)createDependencyOnValue(
            cellAdj,
            ResourceWater.NAME_X
            );
      yLocal = (ValueDouble)createDependencyOnValue(
            cellLocal,
            ResourceWater.NAME_Y
            );
      yAdjacent = (ValueDouble)createDependencyOnValue(
            cellAdj,
            ResourceWater.NAME_Y
            );

      try
      {
         lengthLoc = (ValueDouble)createDependencyOnValue(
               BehaviorDynamicWave.NAME_LENGTH_LOC
               );
         try
         {
            lengthAdj = (ValueDouble)createDependencyOnValue(
                  BehaviorDynamicWave.NAME_LENGTH_ADJ
                  );
         }
         catch (Exception e)
         {
            throw new Exception(String.format(
                  "Local length is defined with adjacent length in boundary %s.",
                  getState().getParentHolon().toString()
                  ));
         }
      }
      catch(Exception e)
      {
         try
         {
            lengthAdj = (ValueDouble)createDependencyOnValue(
                  BehaviorDynamicWave.NAME_LENGTH_ADJ
                  );
            throw new Exception(String.format(
                  "Adjacent length is defined with local length in boundary %s.",
                  getState().getParentHolon().toString()
                  ));
         }
         catch (Exception e2)
         {
            lengthLoc = null;
            lengthAdj = null;
         }
      }
   }

   @Override
   public void initialize() throws Exception 
   {
      if (lengthLoc == null)
      {
         value.n = ResourceWater.getHorizontalDistance(
               xLocal.n, yLocal.n, xAdjacent.n, yAdjacent.n
               );
      }
      else if (lengthLoc.n > 0 && lengthAdj.n > 0)
      {
         value.n = lengthLoc.n + lengthAdj.n;
      }
      else
      {
         throw new Exception(String.format(
               "Adjacent length and local length improperly defined in cell %s.",
               getState().getParentHolon().toString()
               ));
      }
   }

}
