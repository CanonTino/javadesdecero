package net.tecgurus.banco.model.service;

import java.util.List;

import net.tecgurus.banco.model.dao.iface.BancoDao;
import net.tecgurus.banco.model.dao.impl.BancoDaoMysql;
import net.tecgurus.banco.model.dto.Banco;

public class BancoService {

	private BancoDao dao;
	
	public BancoService() {
		this.dao = new BancoDaoMysql();
	}
	
	public List<Banco> getBancosByStatus(boolean status){
		
		Banco banco = new Banco();
		banco.setStatus(status);
		return dao.listar(banco);
	}
	
	
}
