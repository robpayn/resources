package org.payn.resources.solute.otis.cell;

import org.payn.chsm.values.ValueDouble;
import org.payn.neoch.processors.ProcessorStorageDouble;
import org.payn.resources.solute.otis.ResourceSoluteOTIS;

public class SoluteConc extends ProcessorStorageDouble {
   
   @Override
   public void setUpdateDependencies() 
   {
      ValueDouble value = (ValueDouble)getState().getParentHolon().getState(
            getResourceName() + ResourceSoluteOTIS.NAME_ADVECTION
            ).getValue();
      loadValues.add((ValueDouble)value);
      value = (ValueDouble)getState().getParentHolon().getState(
            getResourceName() + ResourceSoluteOTIS.NAME_DISPERSION
            ).getValue();
      loadValues.add((ValueDouble)value);
   }   

}
