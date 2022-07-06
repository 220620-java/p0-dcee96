package dev.cooley.consolebanking.data;

public interface DataAccessObject<T> {
	
	/**
	 * 
	 * @param t
	 */
	public void delete(T t);

}
