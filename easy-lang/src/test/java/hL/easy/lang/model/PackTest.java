package hL.easy.lang.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PackTest {

    @Test
    void test() {
        Number o = 1;
        Pack<Number> pack1 = new Pack<>(o);
        o = 2L;
        Pack<Number> pack2 = new Pack<>(o);

        assertAll(() -> assertEquals(false, pack1.hashCode() == pack2.hashCode()),
            () -> assertEquals(false, pack1.equals(pack2)));
        assertEquals(1, pack1.me());
        assertEquals(new Integer(1), pack1.me());
        assertNotEquals(new Double(1), pack1.me());
        pack1.me(2);
        assertEquals(2, pack1.me());
        assertEquals(new Integer(2), pack1.me());
        assertNotEquals(new Double(2), pack1.me());
        assertAll(() -> assertEquals(true, pack1.hashCode() == pack2.hashCode()),
            () -> assertEquals(false, pack1.equals(pack2)));
        assertAll(
            () -> assertEquals("Pack[ 2 ]", pack2.toString()),
            () -> assertEquals("2", pack2.toJson())
        );
        Pack<Number> pack3 = pack1.clone();
        assertAll(
            () -> assertEquals(2, pack3.me()),
            () -> assertEquals(true, pack1.equals(pack3)),
            () -> assertEquals(false, pack1 == pack3)
        );
    }

}