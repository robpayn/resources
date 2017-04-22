package org.payn.resources.solute.mass.gas.oxygen;

import java.io.File;
import java.sql.Time;

import org.payn.chsm.Holon;
import org.payn.chsm.finitedifference.processors.ProcessorDoubleInfoInit;
import org.payn.chsm.io.interpolate.Interpolator;
import org.payn.chsm.io.interpolate.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;

/**
 * Interpolates photosynthetically active radiation from a table 
 * in an input file
 * 
 * @author v78h241
 *
 */
public class PAR extends ProcessorDoubleInfoInit {

   /**
    * Interpolator for the input file
    */
   private Interpolator interp;

   @Override
   public void setUpdateDependencies() throws Exception 
   {}

   @Override
   public void update() throws Exception 
   {
      value.n = interp.interpolate();
   }

   @Override
   public void setInitDependencies() throws Exception 
   {
      ValueString pathName = (ValueString)createDependency(
            "PAR" + InterpolatorSnapshotTable.NAME_PATH
            ).getValue();
      ValueString type = (ValueString)createDependency(
            "PAR" + InterpolatorSnapshotTable.NAME_TYPE
            ).getValue();
      ValueString delimiter = (ValueString)createDependency(
            "PAR" + InterpolatorSnapshotTable.NAME_DELIMITER
            ).getValue();
      ValueDouble time = (ValueDouble)getState(
            (Holon)getController().getState(),
            Time.class.getSimpleName()
            ).getValue();
      interp = InterpolatorSnapshotTable.getInterpolatorInstance(
            getController(), 
            new File(pathName.string), 
            time, 
            delimiter.string, 
            "par", 
            type.toString()
            );
   }

   @Override
   public void initialize() throws Exception 
   {
      update();
   }

}
