package dev.cooley.consolebanking.data;

import dev.cooley.consolebanking.models.Accounts;
import dev.cooley.consolebanking.models.Users;

public interface AccInterface<A> extends DataAccessObject<A> {
	/**
	 * 
	 * @param account
	 * @return
	 */
	public Accounts create(Accounts account);
	
	/**
	 * 
	 * @param account
	 */
	public void update(Accounts account);
	
	/**
	 * 
	 */
	public Accounts getByID(Users user);
	
}
