package dev.cooley.consolebanking.data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dev.cooley.consolebanking.ds.ArrayList;
import dev.cooley.consolebanking.ds.List;
import dev.cooley.consolebanking.models.Accounts;
import dev.cooley.consolebanking.models.Users;
import dev.cooley.consolebanking.utils.ConnectionUtil;

public class AccountDAO<A> implements AccInterface<A> {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	public Accounts create(Accounts account) {
		try (Connection conn = connUtil.openConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "insert into bank_data.accounts "
					+ "(acc_id, balance, acc_name, owner_id) "
					+ "values (default, ?, ?, ?)";
			
			String[] keys = {"acc_id"};
			
			PreparedStatement state = conn.prepareStatement(sql, keys);
			state.setDouble(1, account.getBalance());
			state.setString(2, account.getName());
			state.setInt(3, account.getUser_id());
			
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
		return  account;
	}

	@Override
	public Accounts getByID(Users user) {
		Accounts account = null;
		
		int id = user.getId();
		
		try (Connection conn = connUtil.openConnection()) {
			
			String sql = "select accounts.acc_id, "
							+ "accounts.balance, "
							+ "accounts.acc_name "
							+ "from bank_data.accounts "
							+ "where accounts.owner_id = ?";
			
			PreparedStatement state = conn.prepareCall(sql);
			state.setInt(1, id);
			
			ResultSet resultSet = state.executeQuery();
			
			if (resultSet.next()) {
				String name = resultSet.getString("acc_name");
				double balance = resultSet.getDouble("balance");
				int acc_id = resultSet.getInt("acc_id");
				
				Accounts newAccount = new Accounts(name, balance, id);
				newAccount.setId(acc_id);
				return newAccount;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public void update(Accounts account) {
		
		try (Connection conn = connUtil.openConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "update bank_data.accounts "
							+ "set balance = ? "
							+ "where bank_data.accounts.acc_id = ?;";
			
			PreparedStatement state = conn.prepareCall(sql);
			state.setDouble(1, account.getBalance());
			state.setInt(2, account.getId());
			
			int changedRows = state.executeUpdate();
			
			if (changedRows <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Object A) {
		// TODO Auto-generated method stub
		
	}

	public List<Accounts> allAccounts(Users user) {
		List<Accounts> userAccounts = new ArrayList<>(5);
		
		try {
			
			Connection conn = connUtil.openConnection();
			
			String sql = "select * from bank_data.accounts "
							+ "where accounts.owner_id = ?;";
			
			PreparedStatement state = conn.prepareStatement(sql);
			state.setInt(1, user.getId());
			
			ResultSet resultSet = state.executeQuery(sql);
			
			while (resultSet.next()) {
				int acc_id = resultSet.getInt("acc_id");
				String name = resultSet.getString("acc_name");
				double balance = resultSet.getDouble("balance");
				
				Accounts newAccount = new Accounts(name, balance, user.getId());
				newAccount.setId(acc_id);
				
				userAccounts.append(newAccount);
			}
			
		} catch (Exception e) {
			
		}
		
		
		return userAccounts;
		
	}
}
