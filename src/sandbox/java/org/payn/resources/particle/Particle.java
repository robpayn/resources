package org.payn.resources.particle;

import java.io.IOException;

import org.payn.chsm.Processor;
import org.payn.chsm.values.ValueDouble;
import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueTimeSeries;

import neoch.HolonCell;

public interface Particle {

   public abstract void update() throws Exception;

   public abstract void initializeTime(ValueLong tick, ValueDouble time, 
         ValueDouble timeStep, ValueLong interval);

   public abstract void initializeLocation(HolonCell releaseCell,
         HolonCell endCell);

   public abstract void initializeOutput(int particleCount, String output) throws IOException;

   public abstract String getResourceName();

   public abstract void close() throws IOException;

}
