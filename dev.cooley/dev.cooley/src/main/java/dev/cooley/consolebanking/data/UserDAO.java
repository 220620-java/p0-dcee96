package dev.cooley.consolebanking.data;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.cooley.consolebanking.models.Users;
import dev.cooley.consolebanking.utils.ConnectionUtil;

public class UserDAO<U> implements UserInterface<U> {
	
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	public Users create(Users user) {
		try (Connection conn = connUtil.openConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "insert into bank_data.people "
					+ "(user_id, username, passwd, per_name) "
					+ "values (default, ?, ?, ?)";
			
			String[] keys = {"user_id"};
			
			PreparedStatement state = conn.prepareStatement(sql, keys);
			state.setString(1, user.getUsername());
			state.setString(2, user.getPassword());
			state.setString(3, user.getName());
			
			int rowsChanged = state.executeUpdate();
			ResultSet resultSet = state.getGeneratedKeys();
			
			if (resultSet.next() && rowsChanged == 1) {
				user.setId(resultSet.getInt("user_id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Users getByID(int id) {
		Users user = null;
		
		try {
			
			Connection conn = connUtil.openConnection();
			
			String sql = "";
			
			PreparedStatement state = conn.prepareStatement(sql);
			state.setInt(1, id);
			
			ResultSet resultSet = state.executeQuery();
			
			if (resultSet.next()) {
				String name = resultSet.getString("per_name");
				String username = resultSet.getString("username");
				String passwd = resultSet.getString("passwd");
				
				user = new Users(username, passwd, name);
			}
			
		} catch (Exception e) {
			
		}
		return user;
	}

	@Override
	public void delete(U user) {
		
		try {
			Connection conn = connUtil.openConnection();
			
			String sql = "delete from user where user_id = ?;";
			
			PreparedStatement state = conn.prepareStatement(sql);
			state.setInt(1, ((Users) user).getId());
			
			int rowsChanged = state.executeUpdate();
			if (rowsChanged <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (Exception e) {
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Users getUsername(String username) {
		Users user = null;
		try (Connection conn = connUtil.openConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "select username, "
					+ "passwd, "
					+ "per_name, "
					+ "user_id "
					+ "from bank_data.people where username = ?";
			
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, username);
			
			ResultSet resultSet = state.executeQuery();
			
			if (resultSet.next()) {
				int user_id = resultSet.getInt("user_id");
				String passwd = resultSet.getString("passwd");
				String person_name = resultSet.getString("per_name");
				
				user = new Users(username, passwd, person_name);
				user.setId(user_id);
				return user;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return user ;
	}

}
