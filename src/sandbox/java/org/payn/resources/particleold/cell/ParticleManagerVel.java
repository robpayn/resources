package org.payn.resources.particleold.cell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map.Entry;

import org.payn.resources.particle.Particle;

public class ParticleManagerVel extends ParticleManagerAlt {

   @Override
   protected void releaseParticleConditional() throws Exception 
   {
      if (tick.n == releaseInterval.n)
      {
         BufferedReader reader = new BufferedReader(new FileReader(new File("./vel.txt")));
         while(reader.ready())
         {
            double velocity = Double.valueOf(reader.readLine());
            for (Entry<String, CurrencyStructure> currency: currencies.entrySet())
            {
               Particle particle = new ParticleOneDimVel(this, currency.getKey(), velocity);
               particle.initializeTime(tick, time, timeStep, recordInterval);
               particle.initializeLocation(releaseCell, endCell);
               particles.add(particle);
            }
         }
         reader.close();
      }
   }

}
