package net.tecgurus.banco.model.db.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import net.tecgurus.banco.model.db.ConnectionFactory;

public class FactoryTest {

	@Test
	public void conexionCorrecta() throws SQLException {
		Connection conexion = ConnectionFactory.getConnection();
		Assert.assertNotNull(conexion);
		Assert.assertFalse(conexion.isClosed());
		Assert.assertEquals("banco", conexion.getCatalog());
		conexion.close();
		assertTrue(conexion.isClosed());
	}
}
