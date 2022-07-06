package dev.cooley.consolebanking.data;

import dev.cooley.consolebanking.models.Users;

public interface UserInterface<U> extends DataAccessObject<U> {
	
	public Users create(Users user);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Users getByID(int id);
	
	public Users getUsername(String username);
}
