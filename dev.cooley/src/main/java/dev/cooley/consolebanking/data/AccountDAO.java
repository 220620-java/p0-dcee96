package dev.cooley.consolebanking.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.cooley.consolebanking.ds.ArrayList;
import dev.cooley.consolebanking.models.Accounts;
import dev.cooley.consolebanking.utils.ConnectionUtil;

public class AccountDAO<A> implements DataAccessObject<A> {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	public Accounts create(Accounts account) {
		try (Connection conn = connUtil.openConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "insert into bank_data.accounts "
					+ "(acc_id, amount, user_id, acc_name) "
					+ "values (default, ?, ?, ?)";
			
			String[] keys = {"acc_id"};
			
			PreparedStatement state = conn.prepareStatement(sql, keys);
			state.setDouble(1, account.getBalance());
			state.setInt(2, account.getUser_id());
			state.setString(3, account.getName());
			
			int rowsChanged = state.executeUpdate();
			ResultSet resultSet = state.getGeneratedKeys();
			
			if (resultSet.next() && rowsChanged == 1) {
				account.setId(resultSet.getInt("acc_id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public A getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<A> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object A) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object A) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public A create(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

}
