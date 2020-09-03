package net.tecgurus.banco.model.dao.impl.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.tecgurus.banco.model.dao.iface.BancoDao;
import net.tecgurus.banco.model.dao.impl.BancoDaoMysql;
import net.tecgurus.banco.model.dto.Banco;

public class BancoDaoMySqlTest {
	
	@Test
	public void listarBancosActivos() {
		BancoDao dao = new BancoDaoMysql();
		List<Banco> bancos = dao.listar(new Banco(null, null, true));
		Assert.assertFalse(bancos.isEmpty());
		bancos.forEach(banco -> {
			System.out.println(banco.getNombre());
		});
	}
	
	@Test
	public void listarBancosInactivos() {
		BancoDao dao = new BancoDaoMysql();
		List<Banco> bancos = dao.listar(new Banco(null, null, false));
		Assert.assertFalse(bancos.isEmpty());
		bancos.forEach(banco -> {
			System.out.println(banco.getNombre());
		});
	}

}
