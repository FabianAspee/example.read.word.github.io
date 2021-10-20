package example.com.akkaimpl.commonimpl;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import example.com.akkaimpl.exceptiongestion.ExceptionGestion;
import example.com.akkaimpl.functionalinterfaces.ReadFile;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class Utility {
    private static final Utility instance = new Utility();
    private Utility(){}
    ReadFile<InputStream,String> readFile = this::readFromInputStream;
    public Config getConfig(final String config){
        return ConfigFactory.parseString(ExceptionGestion.execFunction(readFile,Optional.ofNullable(getClass()
                .getResourceAsStream(config))
                .orElse(new ByteArrayInputStream("notFound".getBytes(StandardCharsets.UTF_8)))));
    }
    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static Utility getInstance(){
        return instance;
    }
}
