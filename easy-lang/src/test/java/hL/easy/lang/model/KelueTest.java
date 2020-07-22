package hL.easy.lang.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import hL.easy.lang.simulation.commons.collection.CollectionUtils;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KelueTest {

    @Test
    void test() {
        Number o = 1;
        Integer i = 2;
        Kelue<Number, Number> kelue1 = new Kelue<>(o, i);
        o = 2L;
        i = new Integer(3);
        Kelue<Number, Integer> kelue2 = new Kelue<>(o, i);

        assertAll(() -> assertEquals(false, kelue1.hashCode() == kelue2.hashCode()),
            () -> assertEquals(false, kelue1.equals(kelue2)));
        assertEquals(1, kelue1.key());
        assertEquals(new Integer(1), kelue1.key());
        assertNotEquals(new Double(1), kelue1.key());
        kelue1.key(2);
        assertEquals(2, kelue1.key());
        assertEquals(2, kelue1.value());
        assertEquals(new Integer(2), kelue1.value());
        assertNotEquals(new Double(2), kelue1.value());
        kelue1.value(3);
        assertEquals(3, kelue1.value());
        assertAll(() -> assertEquals(true, kelue1.hashCode() == kelue2.hashCode()),
            () -> assertEquals(false, kelue1.equals(kelue2)));
        assertAll(
            () -> assertEquals("Kelue{ 2 : 3 }", kelue2.toString()),
            () -> assertEquals("{\"key\":2,\"value\":3}", kelue2.toJson())
        );
        Kelue<Number, Number> kelue3 = kelue1.clone();
        assertAll(
            () -> assertEquals(2, kelue3.key()),
            () -> assertEquals((Object) 3, kelue3.value()),
            () -> assertEquals(true, kelue1.equals(kelue3)),
            () -> assertEquals(false, kelue1 == kelue3)
        );
    }

}