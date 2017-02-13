package org.payn.resources.particle.cell;

import org.payn.chsm.Controller;
import org.payn.chsm.Holon;
import org.payn.chsm.values.ValueLong;
import org.payn.chsm.values.ValueString;
import org.payn.resources.particle.ResourceParticle;

import neoch.behaviors.BehaviorMatrix;

public class BehaviorConcTrackerVel extends BehaviorMatrix {

   public static final String REQ_STATE_RELEASE_NAME = "releaseCellName";
   public static final String REQ_STATE_END_NAME = "endCellName";
   public static final String REQ_STATE_RESOURCE = "resource";
   public static final String REQ_STATE_INTERVAL_RECORD = "recordInterval";
   public static final String REQ_STATE_INTERVAL_RELEASE = "releaseInterval";
   public static final String REQ_STATE_VEL_FILE = "velocityFile";
   public static final String REQ_STATE_OUTPUT_LOC = "outputLocation";

   @Override
   protected void addRequiredStates() 
   {
      addRequiredState(REQ_STATE_RELEASE_NAME, ValueString.class);
      addRequiredState(REQ_STATE_END_NAME, ValueString.class);
      addRequiredState(REQ_STATE_RESOURCE, ValueString.class);
      addRequiredState(REQ_STATE_INTERVAL_RECORD, ValueLong.class);
      addRequiredState(REQ_STATE_INTERVAL_RELEASE, ValueLong.class);
      addRequiredState(REQ_STATE_VEL_FILE, ValueString.class);
      addRequiredState(REQ_STATE_OUTPUT_LOC, ValueString.class);
   }

   @Override
   protected void addProcessors() 
   {
      addProcessor(
            ResourceParticle.NAME_MANAGER, 
            ParticleManagerVel.class, 
            ParticleManagerVel.getValueClass()
            );
   }

   @Override
   public void createBehaviorProcessors(Holon holon, Controller controller) throws Exception 
   {
      super.createBehaviorProcessors(holon, controller);
      ParticleManagerVel manager = (ParticleManagerVel)holon.getState(
            resource.getName() + ResourceParticle.NAME_MANAGER).getProcessor();
      String[] resourceNames = ((ValueString)holon.getState(
            REQ_STATE_RESOURCE
            ).getValue()).string.split(",");
      for (String resourceName: resourceNames)
      {
         manager.addResource(resourceName);
      }
   }   

}
