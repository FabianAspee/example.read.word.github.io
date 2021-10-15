package example.com.akkaimpl.exceptiongestion;

import example.com.akkaimpl.functionalinterfaces.ReadFile;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;

public class ExceptionGestion {
    public static String execFunction(ReadFile<InputStream,String> readFile,InputStream inputStream){
        try {
            return readFile.apply(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }
}
