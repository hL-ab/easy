package hL.easy.lang.simulation.commons.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;

class CollectionUtilsTest {

    @Test
    void isEmpty() {
        assertAll(() -> assertEquals(true, CollectionUtils.isEmpty(null)),
            () -> assertEquals(true, CollectionUtils.isEmpty(new LinkedList<>())),
            () -> assertEquals(false, CollectionUtils.isEmpty(new LinkedList() {{
                add(null);
            }}))
        );
    }
}