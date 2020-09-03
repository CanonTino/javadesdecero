package net.tecgurus.banco.model.service;

import java.util.Date;
import java.util.List;

import net.tecgurus.banco.model.dao.iface.ClienteDao;
import net.tecgurus.banco.model.dao.impl.ClienteDaoMysql;
import net.tecgurus.banco.model.dto.Banco;
import net.tecgurus.banco.model.dto.Cliente;

public final class ClienteService {

	private ClienteDao dao = new ClienteDaoMysql();
	
	public List<Cliente> filtrarClientes(String claveBanco, String nombre, String aPaterno, String aMaterno, boolean status) {
		Banco banco = new Banco();
		banco.setClave(claveBanco);
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellidoPaterno(aPaterno);
		cliente.setApellidoMaterno(aMaterno);
		cliente.setStatus(status);
		cliente.setBanco(banco);
		return dao.listar(cliente);
		
	}
	
	public List<Cliente> listarClientes() {
		return dao.listarTodos();
	}
	
	public Cliente buscarPorId(int id) {
		return dao.buscarPorId(id);
	}
	
	public Cliente crearCliente(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, boolean status, String claveBanco) {
		Banco banco = new Banco();
		banco.setClave(claveBanco);
		Cliente cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, status, banco);
		return dao.crear(cliente);
	}
	
	public boolean actualizarCliente(int numeroCliente, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, boolean status, String claveBanco) {
		Banco banco = new Banco();
		banco.setClave(claveBanco);
		Cliente cliente = new Cliente(numeroCliente, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, status, banco);
		return dao.actualizar(cliente);
	}
	
	public boolean eliminarClientePorId(int id) {
		return dao.eliminar(id);
	}
}
