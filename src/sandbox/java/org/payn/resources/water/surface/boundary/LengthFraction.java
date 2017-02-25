package org.payn.resources.water.surface.boundary;

import org.payn.chsm.processors.ProcessorDouble;
import org.payn.chsm.processors.interfaces.InitializerAutoSimple;
import org.payn.chsm.values.ValueDouble;

/**
 * Calculates the fraction of the length between the adjacent cells
 * 
 * @author v78h241
 *
 */
public class LengthFraction extends ProcessorDouble implements InitializerAutoSimple {

   /**
    * Length in local cell
    */
   private ValueDouble lengthLoc;
   
   /**
    * Length in adjacent cell
    */
   private ValueDouble lengthAdj;

   @Override
   public void setInitDependencies() throws Exception 
   {
      try
      {
         lengthLoc = (ValueDouble)createDependency(
               BehaviorDynamicWave.REQ_STATE_LENGTH_LOC
               );
         try
         {
            lengthAdj = (ValueDouble)createDependency(
                  BehaviorDynamicWave.REQ_STATE_LENGTH_ADJ
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
            lengthAdj = (ValueDouble)createDependency(
                  BehaviorDynamicWave.REQ_STATE_LENGTH_ADJ
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
         value.n = 0.5;
      }
      else if (lengthLoc.n > 0 && lengthAdj.n > 0)
      {
         value.n = lengthLoc.n / (lengthLoc.n + lengthAdj.n);
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
