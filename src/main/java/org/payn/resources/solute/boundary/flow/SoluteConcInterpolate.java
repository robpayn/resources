package org.payn.resources.solute.boundary.flow;

import org.payn.chsm.io.file.interpolate.ProcessorInterpolateSnapshotTable;
import org.payn.chsm.values.ValueString;
import org.payn.neoch.UpdaterTrade;

/**
 * Solute concentration processor based on interpolation from input data
 * 
 * @author robpayn
 *
 */
public class SoluteConcInterpolate extends ProcessorInterpolateSnapshotTable implements UpdaterTrade {

   @Override
   protected ValueString createPathDependency() throws Exception 
   {
      return (ValueString)createAbstractDependency(
            ProcessorInterpolateSnapshotTable.REQ_STATE_PATH
            ).getValue();
   }

   @Override
   protected ValueString createTypeDependency() throws Exception 
   {
      return (ValueString)createAbstractDependency(
            ProcessorInterpolateSnapshotTable.REQ_STATE_TYPE
            ).getValue();
   }

   @Override
   protected ValueString createDelimiterDependency() throws Exception 
   {
      return (ValueString)createAbstractDependency(
            ProcessorInterpolateSnapshotTable.REQ_STATE_DELIMITER
            ).getValue();
   }

}
