package org.payn.resources.water.channel.temperature;

import java.io.File;
import java.sql.Time;

import org.payn.chsm.Holon;
import org.payn.chsm.io.interpolate.InterpolatorSnapshotTable;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueString;

/**
 * Processor that gets the upstream temperature from and interpolation file
 * 
 * @author robpayn
 *
 */
public class WaterTempUpstream extends WaterTempInterp {

   @Override
   public void setInitDependencies() throws Exception 
   {
      ValueString pathName = (ValueString)createDependency(
            "UpstreamTemp" + InterpolatorSnapshotTable.NAME_PATH
            ).getValue();
      ValueString type = (ValueString)createDependency(
            "Temp" + InterpolatorSnapshotTable.NAME_TYPE
            ).getValue();
      ValueString delimiter = (ValueString)createDependency(
            "Temp" + InterpolatorSnapshotTable.NAME_DELIMITER
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
            "temperature", 
            type.toString()
            );
   }

}
