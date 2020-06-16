package hL.easy.lang.model;

import java.io.IOException;
import java.io.Writer;

/**
 * @author @HL
 * @since 0.1.001
 */
public interface Jsonable {

    String toJson();

    default void write(Writer writer) throws IOException {
        writer.write(toJson());
    }

}
