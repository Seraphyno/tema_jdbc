package com.sda;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DbConnectionTest {

    Persoana persoana = new Persoana("Sera", 28, "1910416295899");

    @BeforeClass
    public static void setup() {
        DbConnection.connect();
    }

    @Before
    public void init() {
        DbConnection.deletePersoana(persoana);
    }

    @Test
    public void a_testInsert() {
        // when
        boolean a = DbConnection.insertPersoana(persoana);
        Persoana persoanaExtrasa = DbConnection.selectPersoana(persoana);

        // then
        assertTrue(a);
        assertEquals(persoanaExtrasa.getCnp(), persoana.getCnp());
        assertEquals(persoanaExtrasa.getNume(), persoana.getNume());
        assertEquals(persoanaExtrasa.getVarsta(), persoana.getVarsta());
    }

    @Test
    public void b_testDelete() {
        boolean a = DbConnection.insertPersoana(persoana);
        assertTrue(a);

        boolean b = DbConnection.deletePersoana(persoana);
        assertTrue(b);

        Persoana persoanaExtrasa = DbConnection.selectPersoana(persoana);
        assertNull(persoanaExtrasa);
    }

    @Test
    public void c_testUpdate() {
        boolean initialInsert = DbConnection.insertPersoana(persoana);
        assertTrue(initialInsert);

        Persoana update = new Persoana("String", 12, persoana.getCnp());
        boolean updateQuery = DbConnection.updatePersoana(update);
        assertTrue(updateQuery);

        Persoana persoanaExtrasa = DbConnection.selectPersoana(update);
        assertEquals(persoanaExtrasa.getVarsta(), update.getVarsta());
        assertEquals(persoanaExtrasa.getCnp(), update.getCnp());
        assertEquals(persoanaExtrasa.getNume(), update.getNume());
    }

    @Test
    public void d_testSelect() throws SQLException {
        boolean initialInsert = DbConnection.insertPersoana(persoana);
        assertTrue(initialInsert);

        List<Persoana> persoane = DbConnection.selectPersoane();
        assertNotNull(persoane);
        assertEquals(1, persoane.size());

        Persoana persoanaExtrasa = persoane.get(0);
        assertEquals(persoanaExtrasa.getNume(), persoana.getNume());
        assertEquals(persoanaExtrasa.getCnp(), persoana.getCnp());
        assertEquals(persoanaExtrasa.getVarsta(), persoana.getVarsta());

    }

    @AfterClass
    public static void tearDown() {
        DbConnection.disconnect();
    }
}