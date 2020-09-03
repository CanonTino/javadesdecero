package net.tecgurus.banco.model.dao.iface;

import java.util.List;

import net.tecgurus.banco.model.dto.Banco;

public interface BancoDao {

	Banco buscar(Banco instancia);
	Banco crear(Banco instancia);
	Banco buscarPorId(Banco id);
	List<Banco> listar(Banco instancia);
	boolean actualizar(Banco instancia);
	boolean eliminar(Banco id);

}
