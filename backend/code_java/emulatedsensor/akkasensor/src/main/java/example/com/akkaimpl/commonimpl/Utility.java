package example.com.akkaimpl.commonimpl;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.net.URL;
import java.util.Optional;

public class Utility {
    private static final Utility instance = new Utility();
    private Utility(){}
    public Config getConfig(final String config){
        return ConfigFactory.parseFile(new File(Optional.ofNullable(getClass().getClassLoader().getResource("ActorCluster.conf"))
                .map(URL::getPath).orElse("")));
    }
    public static Utility getInstance(){
        return instance;
    }
}
