package net.tecgurus.banco.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import net.tecgurus.banco.model.dao.iface.ClienteDao;
import net.tecgurus.banco.model.db.ConnectionFactory;
import net.tecgurus.banco.model.dto.Banco;
import net.tecgurus.banco.model.dto.Cliente;

public class ClienteDaoMysql implements ClienteDao {


	@Override
	public Cliente crear(Cliente instancia) {
		//String query = "insert into cliente (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, clave_banco, status) values (?, ?, ?, ?, ?, ?)";
		String query = "insert into cliente (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, clave_banco, status) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int registrosAfectados = 0;
		Connection conexion = ConnectionFactory.getConnection();
		int claveGenerada = 0;
		
		try {
			Date sqlDate = new Date(instancia.getFechaNacimiento().getTime());
			preparedStatement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, instancia.getNombre());
			preparedStatement.setString(2, instancia.getApellidoPaterno());
			preparedStatement.setString(3, instancia.getApellidoMaterno());
			preparedStatement.setDate(4, sqlDate);
			preparedStatement.setString(5, instancia.getBanco().getClave());
			preparedStatement.setBoolean(6, instancia.isStatus());

			registrosAfectados = preparedStatement.executeUpdate();
			if (registrosAfectados > 0) {
				resultSet = preparedStatement.getGeneratedKeys();//Extraemos el conjunto de llaves generadas
				if(resultSet.next()) {
					claveGenerada = resultSet.getInt(1);
					instancia.setNumeroCliente(claveGenerada);

				}
			} else
				System.out.println("Error durante la creación del cliente. Cliente no creado.");
			
			
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
		return instancia;
	}

	@Override//NO SE SI DEBERÍA EXISTIR
	public Cliente buscarPorId(int id) {
		Cliente cliente = null;
		String query = "SELECT c.numero_cliente as numeroCliente, "
				+ "c.nombre, "
				+ "c.apellido_paterno as ap,"
				+ "c.apellido_materno as am,"
				+ "c.fecha_nacimiento as fn, "
				+ "c.status,"
				+ "c.clave_banco as cb,"
				+ "b.nombre as nombreBanco,"
				+ "b.status as statusBanco "
				+ " FROM cliente as c inner join banco as b on c.clave_banco=b.clave "
				+ " WHERE c.numero_cliente = ? ";
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conexion = ConnectionFactory.getConnection();
		
		try {
			preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			Banco banco;
			if(resultSet.next()) {
				banco = new Banco(resultSet.getString("cb"), resultSet.getString("nombreBanco"), resultSet.getBoolean("statusBanco"));
				cliente = new Cliente(resultSet.getInt("numeroCliente"),
						resultSet.getString("nombre"), 
						resultSet.getString("ap"), 
						resultSet.getString("am"), 
						resultSet.getDate("fn"), 
						resultSet.getBoolean("status"), 
						banco);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return cliente;
	}

	@Override
	public List<Cliente> listar(Cliente instancia) {
		List<Cliente> lista = new ArrayList<Cliente>();
		//String query = "select numero_cliente as nc, nombre, apellido_paterno as ap, apellido_materno as am, fecha_nacimiento as fn, clave_banco as cb, status from cliente where clave_banco=? and nombre like ? and apellido_paterno like ? and apellido_materno like ? and status = ?";
		String query = "select c.numero_cliente as nc, c.nombre, c.apellido_paterno as ap, c.apellido_materno as am, c.fecha_nacimiento as fn, c.clave_banco as cb, c.status, b.clave as clave_banco, b.nombre as nombre_banco,b.status status_banco from cliente as c inner join banco as b on c.clave_banco=b.clave where c.clave_banco=? and c.nombre like ? and c.apellido_paterno like ? and c.apellido_materno like ? and c.status = ?";
		Connection conexion = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, instancia.getBanco().getClave());
			preparedStatement.setString(2, "%" + instancia.getNombre() +"%");
			preparedStatement.setString(3, "%" + instancia.getApellidoPaterno() +"%");
			preparedStatement.setString(4, "%" + instancia.getApellidoMaterno() +"%");
			preparedStatement.setBoolean(5, instancia.isStatus());
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				
				Cliente cliente;
				Banco banco;
				do {
					cliente = new Cliente();
					banco = new Banco();
					banco.setClave(resultSet.getString("cb"));
					banco.setNombre(resultSet.getString("nombre_banco"));
					banco.setStatus(resultSet.getBoolean("status_banco"));
					cliente.setBanco(banco);
					
					cliente.setNumeroCliente(resultSet.getInt("nc"));
					cliente.setNombre(resultSet.getString("nombre"));
					cliente.setApellidoPaterno(resultSet.getString("ap"));
					cliente.setApellidoMaterno(resultSet.getString("am"));
					cliente.setFechaNacimiento(resultSet.getDate("fn"));
					cliente.setStatus(resultSet.getBoolean("status"));
					lista.add(cliente);
					
				} while(resultSet.next());
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return lista;
	}

	@Override
	public boolean actualizar(Cliente instancia) {
		String query = "update cliente SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, clave_banco = ?, status = ? where numero_cliente = ?";
//		String query = "update cliente SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = fecha_nacimiento, clave_banco = ?, status = ? where numero_cliente = ?";
		PreparedStatement preparedStatement = null;
		
		int registrosAfectados = 0;
		Connection conexion = ConnectionFactory.getConnection();
		
		try {
			preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, instancia.getNombre());
			preparedStatement.setString(2, instancia.getApellidoPaterno());
			preparedStatement.setString(3, instancia.getApellidoMaterno());
			preparedStatement.setDate(4, new java.sql.Date(instancia.getFechaNacimiento().getTime()));
			preparedStatement.setString(5, instancia.getBanco().getClave());
			preparedStatement.setBoolean(6, instancia.isStatus());
			preparedStatement.setInt(7, instancia.getNumeroCliente());
			
			registrosAfectados = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return registrosAfectados > 0;
	}

	@Override
	public boolean eliminar(int id) {
		String query = "delete from cliente where numero_cliente = ?";
		PreparedStatement preparedStatement = null;
		
		int registrosAfectados = 0;
		Connection conexion = ConnectionFactory.getConnection();
		
		try {
			preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setInt(1, id);
			registrosAfectados = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return registrosAfectados > 0;
	}

	@Override
	public List<Cliente> listarTodos() {
		List<Cliente> lista = new ArrayList<Cliente>();
		String query = "select c.numero_cliente as nc, c.nombre, c.apellido_paterno as ap, c.apellido_materno as am, c.fecha_nacimiento as fn, c.clave_banco as cb, c.status, b.clave as clave_banco, b.nombre as nombre_banco,b.status status_banco from cliente as c inner join banco as b on c.clave_banco=b.clave order by c.status desc";
		Connection conexion = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = conexion.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				
				Cliente cliente;
				Banco banco;
				do {
					cliente = new Cliente();
					banco = new Banco();
					banco.setClave(resultSet.getString("cb"));
					banco.setNombre(resultSet.getString("nombre_banco"));
					banco.setStatus(resultSet.getBoolean("status_banco"));
					cliente.setBanco(banco);
					
					cliente.setNumeroCliente(resultSet.getInt("nc"));
					cliente.setNombre(resultSet.getString("nombre"));
					cliente.setApellidoPaterno(resultSet.getString("ap"));
					cliente.setApellidoMaterno(resultSet.getString("am"));
					cliente.setFechaNacimiento(resultSet.getDate("fn"));
					cliente.setStatus(resultSet.getBoolean("status"));
					lista.add(cliente);
					
				} while(resultSet.next());
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return lista;
	}

}
