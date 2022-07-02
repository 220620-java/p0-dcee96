package dev.cooley.consolebanking.ds;

@SuppressWarnings("unchecked")
public interface List<T> {
	/** 
	 * Appends an item to the end of the list.
	 * 
	 * @param t the item to be appended to the list.
	 */
	public void append(T t);
	
	/**
	 * Inserts an item into the specified index.
	 * 
	 * @param t is the item being inserted.
	 * @param index is the index of the list where the item will be inserted.
	 */
	public void insert(T t, int index);
	
	/**
	 * This method will append an entire array, list,
	 *  or comma separated values to the list.
	 * 
	 * @param t represents the values that are going to be appended.
	 */
	public void appendList(T...t);
	
	/**
	 * This method will return the contents found at the specified index.
	 * 
	 * @param index of the list you wish to get.
	 * @return contents found at the index specified.
	 */
	public T get(int index);
	
	/**
	 * This will look through a list and return the first occurrence
	 * of the item being searched for.
	 * 
	 * @param t the item being searched for
	 * @return the index of the item.
	 */
	public int findIndex(T t);
	
	/**
	 * Searches through a list and returns the true or false 
	 * depending on if the item was found or not.
	 * 
	 * @param t the item being searched for.
	 * @return true or false
	 */
	public boolean contains(T t);
	
	/**
	 * Removes the first occurrence item that is 
	 * specified from the list and returns it.
	 * 
	 * @param t The item being removed from the list.
	 * @return the item that was removed.
	 */
	public T removeItem(T t);
	
	/**
	 * Removes the contents of the index specified.
	 * 
	 * @param index the index that you wish to remove.
	 * @return The item that was removed.
	 */
	public T removeIndex(int index);
	
	/**
	 * Finds and returns the size of the list.
	 * 
	 * @return The size of the array as an integer.
	 */
	public int size();
	
}
