package example.com.akkaimpl;

import akka.actor.typed.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import example.com.akkaimpl.actorguardian.ActorGuardianMain;
import example.com.akkaimpl.actorguardian.MessageActorGuardianMain;
import example.com.akkaimpl.actorlistener.ActorListenerCluster;
import example.com.akkaimpl.actorpatch.ActorPatchMain;
import example.com.akkaimpl.actorpatch.MessageActorPatchMain;
import example.com.akkaimpl.actorsensor.ActorSensorMain;
import example.com.akkaimpl.actorsensor.MessageActorSensorMain;
import example.com.akkaimpl.configproperties.ApplicationProperties;

import java.util.Arrays;
import java.util.Optional;

public class ClusterMain {
    private static final int NUMBER_SENSOR = 5;
    public static void main(final String[] args){

        Optional.ofNullable(args)
                .ifPresent(argument-> Arrays.stream(argument)
                        .forEach(arg->{
                            switch (arg) {
                                case "sensor" -> createSensor(ApplicationProperties.getInstance().getClusterConfigPath(),9090);
                                case "guardian" -> createGuardian(ApplicationProperties.getInstance().getClusterConfigPath(),9091);
                                case "patch" -> createPatch(ApplicationProperties.getInstance().getClusterConfigPath(),3,4);
                                default ->System.out.println("actor not exist");
                            }
                        }));
    }

    private static void createClusterListener(final Config config){
        System.out.println("Init");
        ActorSystem.create(
                ActorListenerCluster.getInstance(),
                ApplicationProperties.getInstance().getClusterName(),
                config);
        System.out.println("Finish");
    }

    private static void createSensor(final Config config, int port){
        createClusterListener(config);
        System.out.println("Init");
        ActorSystem.create(
                ActorSensorMain.getInstance(),
                ApplicationProperties.getInstance().getClusterName(),
                changePortInConfigFile(config,port).resolve())
                .tell(new MessageActorSensorMain.CreateSensor(NUMBER_SENSOR));
        System.out.println("Finish");
    }
    private static void createGuardian(final Config config, int port){
        System.out.println("Init");
        ActorSystem.create(
                ActorGuardianMain.getInstance(),
                ApplicationProperties.getInstance().getClusterName(),
                changePortInConfigFile(config,port).resolve())
                .tell(new MessageActorGuardianMain.CreateSensor(NUMBER_SENSOR));
        System.out.println("Finish");
    }
    private static void createPatch(final Config config, final int numberPatchX, final int numberPatchY){
        System.out.println("Init");
        ActorSystem.create(
                ActorPatchMain.getInstance(),
                ApplicationProperties.getInstance().getClusterName(),
                config)
                .tell(new MessageActorPatchMain.CreateSensor(numberPatchX,numberPatchY));
        System.out.println("Finish");
    }
    private static Config changePortInConfigFile(final Config config, int port){
        return config.withValue("akka.remote.artery.canonical.port", ConfigValueFactory.fromAnyRef(port));
    }
}
