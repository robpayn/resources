package org.payn.resources.particleold;

import org.payn.chsm.processors.ProcessorAbstract;
import org.payn.chsm.processors.interfaces.UpdaterAuto;
import org.payn.chsm.values.ValueParticleList;

import neoch.behaviors.BehaviorMatrix;

public class ParticleList extends ProcessorAbstract<ValueParticleList> implements UpdaterAuto {

   @Override
   public void setUpdateDependencies() throws Exception 
   {
      value.newParticleList();
   }

   @Override
   public void createValue() throws Exception 
   {
      setValue(new ValueParticleList());
   }

   @Override
   public void setValueFromState() throws Exception 
   {
      setValue((ValueParticleList)state.getValue());
   }

   /**
    * Get the name of the currency for the behavior of the associated state
    * 
    * @return
    *       currency name
    */
   public String getResourceName()
   {
      return ((BehaviorMatrix)getState().getBehavior()).getResource().getName();
   }

   public void addParticle(Particle particle) 
   {
      value.particleList.add(particle);
   }

}
