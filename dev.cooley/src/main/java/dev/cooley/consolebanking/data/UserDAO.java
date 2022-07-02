package dev.cooley.consolebanking.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.cooley.consolebanking.ds.ArrayList;
import dev.cooley.consolebanking.models.Users;
import dev.cooley.consolebanking.utils.ConnectionUtil;
@SuppressWarnings("unchecked")
public class UserDAO<U> implements DataAccessObject<U> {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	
	public U create(Users user) {
		try (Connection conn = connUtil.openConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "insert into bank_data.people "
					+ "(person_id, username, passwd, person_name) "
					+ "values (default, ?, ?, ?)";
			
			String[] keys = {"person_id"};
			
			PreparedStatement state = conn.prepareStatement(sql, keys);
			state.setString(1, user.getUsername());
			state.setString(2, user.getPassword());
			state.setString(3, user.getName());
			
			int rowsChanged = state.executeUpdate();
			ResultSet resultSet = state.getGeneratedKeys();
			
			if (resultSet.next() && rowsChanged == 1) {
				user.setId(resultSet.getInt("person_id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return (U) user;
	}

	public boolean findUsername(String username) {
		return false;
	}
	@Override
	public U getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<U> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public U create(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

}
