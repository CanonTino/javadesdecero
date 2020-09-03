package net.tecgurus.banco.model.dao.iface;

import java.util.List;

import net.tecgurus.banco.model.dto.Cliente;

public interface ClienteDao {
	Cliente crear(Cliente instancia);
	Cliente buscarPorId(int id);
	List<Cliente> listar(Cliente instancia);
	List<Cliente> listarTodos();
	boolean actualizar(Cliente instancia);
	boolean eliminar(int id);

}
