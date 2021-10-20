package example.com.akkaimpl.configproperties;

import com.typesafe.config.Config;
import example.com.akkaimpl.commonimpl.Utility;
import example.com.akkaimpl.functionalinterfaces.ReadFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;

public class ApplicationProperties{
    private final String clusterName;
    private final String sensorName;
    private final String guardianName;
    private final String patchName;
    private final String senderName;
    private final String clusterConfigPath;
    private final String actorSensorConfigPath;
    private final String actorGuardianConfigPath;

    private ApplicationProperties(ApplicationPropertiesBuilder applicationPropertiesBuilder) {
        this.clusterName = applicationPropertiesBuilder.clusterName;
        this.sensorName = applicationPropertiesBuilder.sensorName;
        this.guardianName = applicationPropertiesBuilder.guardianName;
        this.patchName = applicationPropertiesBuilder.patchName;
        this.senderName = applicationPropertiesBuilder.senderName;
        this.clusterConfigPath = applicationPropertiesBuilder.clusterConfigPath;
        this.actorSensorConfigPath = applicationPropertiesBuilder.actorSensorConfigPath;
        this.actorGuardianConfigPath = applicationPropertiesBuilder.actorGuardianConfigPath;
    }

    public String getClusterName() {
        return clusterName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public String getPatchName() {
        return patchName;
    }

    public String getSenderName() {
        return senderName;
    }


    public Config getClusterConfigPath() {
        return Utility.getInstance().getConfig(clusterConfigPath);
    }

    public Config getActorSensorConfigPath() {
        return Utility.getInstance().getConfig(actorSensorConfigPath);
    }

    public Config getActorGuardianConfigPath() {
        return Utility.getInstance().getConfig(actorGuardianConfigPath);
    }

    private static class ApplicationPropertiesBuilder{
        private String clusterName;
        private String sensorName;
        private String guardianName;
        private String patchName;
        private String senderName;
        private String clusterConfigPath;
        private String actorSensorConfigPath;
        private String actorGuardianConfigPath;
        private Properties properties;
        private ApplicationPropertiesBuilder(){
        }
        private ApplicationPropertiesBuilder setProperties(){
            InputStream s =getClass().getClassLoader().getResourceAsStream("application.properties");;
            properties = new Properties();
            try {
                properties.load(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }
        private ApplicationPropertiesBuilder setClusterName() {
            this.clusterName = readProperty("akka.cluster.name");
            return this;
        }

        private ApplicationPropertiesBuilder setSensorName() {
            this.sensorName = readProperty("akka.sensor.manager.name");
            return this;
        }

        private ApplicationPropertiesBuilder setGuardianName() {
            this.guardianName = readProperty("akka.guardian.manager.name");
            return this;
        }

        private ApplicationPropertiesBuilder setPatchName() {
            this.patchName = readProperty("akka.patch.manager.name");
            return this;
        }

        private ApplicationPropertiesBuilder setSenderName() {
            this.senderName = readProperty("akka.sender.manager.name");
            return this;
        }

        private ApplicationPropertiesBuilder setClusterConfigPath() {
            this.clusterConfigPath = readProperty("akka.cluster.root_config");
            return this;
        }

        private ApplicationPropertiesBuilder setActorSensorConfigPath() {
            this.actorSensorConfigPath = readProperty("akka.sender.manager.root_config");
            return this;
        }

        private ApplicationPropertiesBuilder setActorGuardianConfigPath() {
            this.actorGuardianConfigPath = readProperty("akka.guardian.manager.root_config");
            return this;
        }
        private ApplicationProperties build(){
            return new ApplicationProperties(this);
        }
        private String readProperty(String key){
            return Optional.ofNullable(properties.getProperty(key)).orElse("");
        }
    }

    public static ApplicationProperties getInstance(){
        return new ApplicationPropertiesBuilder()
                .setProperties()
                .setClusterName()
                .setSensorName()
                .setGuardianName()
                .setPatchName()
                .setSenderName()
                .setClusterConfigPath()
                .setActorSensorConfigPath()
                .setActorGuardianConfigPath()
                .build();
    }

}
