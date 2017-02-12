package org.payn.resources.particle;

import org.payn.chsm.Processor;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueTimeSeries;

import neoch.HolonCell;

public interface Particle extends Processor {

   public abstract void update() throws Exception;

   public abstract ValueTimeSeries getValue();

   public abstract String getResourceName();

   public abstract void sample();

   public abstract void initializeTime(ValueLong tick, ValueDouble time, ValueDouble timeStep,
         ValueLong interval);

   public abstract void initializeLocation(HolonCell releaseCell,
         HolonCell endCell);

   public abstract void setCurrentCell(HolonCell parentHolon);

}
