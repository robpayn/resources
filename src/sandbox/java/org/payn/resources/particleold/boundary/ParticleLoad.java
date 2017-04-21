package org.payn.resources.particleold.boundary;

import java.util.ArrayList;

import org.payn.resources.particle.Particle;
import org.payn.resources.particle.ParticleList;

import neoch.UpdaterLoad;

public class ParticleLoad extends ParticleList implements UpdaterDelta {
   
   private ArrayList<Particle> tempParticleList;

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      super.setUpdateDependencies();
      
      tempParticleList = new ArrayList<Particle>();
   }

   @Override
   public void update() throws Exception 
   {
      value.particleList.clear();
      value.particleList.addAll(tempParticleList);
      tempParticleList.clear();
   }

   public ArrayList<Particle> getParticleList() 
   {
      return value.particleList;
   }

   @Override
   public void addParticle(Particle particle) 
   {
      tempParticleList.add(particle);
   }

}
