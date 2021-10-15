package example.com.akkaimpl;

import akka.actor.typed.ActorSystem;
import com.typesafe.config.Config;
import example.com.akkaimpl.actorguardian.ActorGuardianMain;
import example.com.akkaimpl.actorguardian.MessageActorGuardianMain;
import example.com.akkaimpl.actorpatch.ActorPatchMain;
import example.com.akkaimpl.actorpatch.MessageActorPatchMain;
import example.com.akkaimpl.actorsensor.ActorSensorMain;
import example.com.akkaimpl.actorsensor.MessageActorSensorMain;
import example.com.akkaimpl.configproperties.ApplicationProperties;

import java.util.Arrays;
import java.util.Optional;

public class ClusterMain {
    private static final int NUMBER_SENSOR = 5;
    public static void main(String[] args){

        Optional.of(new String[]{"sensor","guardian","patch"})
                .ifPresent(argument-> Arrays.stream(argument)
                        .forEach(arg->{
                            switch (arg) {
                                case "sensor" -> createSensor(ApplicationProperties.getInstance().getClusterConfigPath());
                                case "guardian" -> createGuardian(ApplicationProperties.getInstance().getClusterConfigPath());
                                case "patch" -> createPatch(ApplicationProperties.getInstance().getClusterConfigPath());
                                default ->System.out.println("actor not exist");
                            }
                        }));
    }

    private static void createSensor(Config config){
        ActorSystem.create(ActorSensorMain.getInstance(NUMBER_SENSOR),ApplicationProperties.getInstance().getClusterName(),config)
                .tell(new MessageActorSensorMain.CreateSensor());
    }
    private static void createGuardian(Config config){
        ActorSystem.create(ActorGuardianMain.getInstance(NUMBER_SENSOR),ApplicationProperties.getInstance().getClusterName(),config)
                .tell(new MessageActorGuardianMain.CreateSensor());
    }
    private static void createPatch(Config config){
        ActorSystem.create(ActorPatchMain.getInstance(NUMBER_SENSOR),ApplicationProperties.getInstance().getClusterName(),config)
                .tell(new MessageActorPatchMain.CreateSensor());
    }
}
