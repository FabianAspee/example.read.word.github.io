package example.com.akkaimpl.functionalinterfaces;

import java.io.IOException;

@FunctionalInterface
public interface ReadFile<T, R> {
    R apply(T t) throws IOException;
}