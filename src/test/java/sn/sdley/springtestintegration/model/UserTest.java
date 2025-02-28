package sn.sdley.springtestintegration.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserWithConstructor() {
        User user = new User(1L, "prenom_test", "nom_test", 25, true);

        assertEquals(1L, user.getId());
        assertEquals("prenom_test", user.getPrenom());
        assertEquals("nom_test", user.getNom());
        assertEquals(25, user.getAge());
        assertTrue(user.isActif());
    }

    @Test
    void shouldSetAndGetValues() {
        User user = new User();

        user.setId(2L);
        user.setPrenom("Fatou");
        user.setNom("Diop");
        user.setAge(30);
        user.setActif(false);

        assertEquals(2L, user.getId());
        assertEquals("Fatou", user.getPrenom());
        assertEquals("Diop", user.getNom());
        assertEquals(30, user.getAge());
        assertFalse(user.isActif());
    }


}
