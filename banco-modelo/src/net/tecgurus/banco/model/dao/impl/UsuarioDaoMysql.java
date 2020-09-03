package net.tecgurus.banco.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.tecgurus.banco.model.dao.iface.UsuarioDao;
import net.tecgurus.banco.model.db.ConnectionFactory;
import net.tecgurus.banco.model.dto.Perfil;
import net.tecgurus.banco.model.dto.Usuario;

import org.apache.log4j.Logger;

public class UsuarioDaoMysql implements UsuarioDao {
	private static Logger log = Logger.getLogger(UsuarioDaoMysql.class);

	@Override
	public Usuario buscar(Usuario usuario) {
		Usuario usuarioEncontrado = null;
		String query = "select usuario, password, nombre, apellido_paterno, apellido_materno, id_perfil, status from usuario where usuario = ? and password= ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conexion = ConnectionFactory.getConnection();

		try {
			log.info(new StringBuilder("ejecutando query [").append(query).append("]"));
			preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, usuario.getUsuario());
			preparedStatement.setString(2, usuario.getPassword());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				usuarioEncontrado = new Usuario(resultSet.getString("usuario"), resultSet.getString("password"),
						resultSet.getString("nombre"), resultSet.getString("apellido_paterno"),
						resultSet.getString("apellido_materno"), resultSet.getBoolean("status"),
						new Perfil(resultSet.getInt("id_perfil"), ""));
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

		return usuarioEncontrado;
	}

}
