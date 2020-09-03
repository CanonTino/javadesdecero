package net.tecgurus.banco.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.tecgurus.banco.model.dao.iface.BancoDao;
import net.tecgurus.banco.model.db.ConnectionFactory;
import net.tecgurus.banco.model.dto.Banco;

public class BancoDaoMysql implements BancoDao {

	@Override
	public Banco buscar(Banco instancia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Banco crear(Banco instancia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Banco buscarPorId(Banco id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Banco> listar(Banco instancia) {
		List<Banco> lista = new ArrayList<Banco>();
		String query = "select clave, nombre, status from banco where status = ?";
		Connection conexion = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setBoolean(1, instancia.isStatus());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Banco banco;
				do {
					banco = new Banco(
							resultSet.getString("clave"), 
							resultSet.getString("nombre"), 
							resultSet.getBoolean("status"));
					lista.add(banco);
				}while(resultSet.next());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return lista;
	}

	@Override
	public boolean actualizar(Banco instancia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Banco id) {
		// TODO Auto-generated method stub
		return false;
	}

}
