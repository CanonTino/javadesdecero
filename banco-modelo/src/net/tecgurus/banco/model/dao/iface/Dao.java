package net.tecgurus.banco.model.dao.iface;

import java.util.List;

public interface Dao {
	
	public Object buscar(Object instancia);
	public Object crear(Object instancia);
	public Object buscarPorId(Object id);
	public List<Object> listar(Object instancia);
	public boolean actualizar(Object instancia);
	public boolean eliminar(Object id);

}
