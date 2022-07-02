package dev.cooley.consolebanking.data;

import dev.cooley.consolebanking.ds.ArrayList;

public interface DataAccessObject<T> {
	
	/**
	 * 
	 * @param t
	 * @return
	 */
	public T create(T t);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T getByID(int id);
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<T> getAll();
	
	/**
	 * 
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 
	 * @param t
	 */
	public void delete(T t);
}
