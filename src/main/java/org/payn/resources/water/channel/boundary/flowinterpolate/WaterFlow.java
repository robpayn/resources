package org.payn.resources.water.channel.boundary.flowinterpolate;

import org.payn.chsm.io.file.interpolate.ProcessorInterpolateSnapshotTable;
import org.payn.neoch.UpdaterLoad;

/**
 * Calculates the water flow at an input boundary by interpolating from
 * table data
 * 
 * @author robpayn
 *
 */
public class WaterFlow extends ProcessorInterpolateSnapshotTable 
implements UpdaterLoad {}
