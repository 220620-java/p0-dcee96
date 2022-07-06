package dev.cooley.consolebanking.ds;
@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T>{

	private int size = 0;
	private int pos = 0;
	private T[] arr;
	
	public ArrayList (int size) {
		if (size > 0) {
			this.size = size;
			arr = (T[]) new Object[size];
		}
	}

	private T[] expandArray() {
		// Double size of the current array.
		int newSize = this.size * 2;
		T[] newArr = (T[]) new Object[newSize];
		
		for (int i = 0; i < size; i++) {
			newArr[i] = (T) this.arr[i];
		}
		this.size = newSize;
		this.arr = newArr;
		
		return (T[]) this.arr;
	}
	
	@Override
	public void append(T t) {
		
		if (this.pos < this.size) {
			this.arr[pos] = t;
			this.pos++;
		} else {
			expandArray();
			this.arr[pos] = t;
		}
	}

	@Override
	public void insert(T t, int index) {
		int currentPos = 0;
		
		if (index < this.size) {
		
		T[] newArr = (T[]) new Object[++this.size];
		this.size = newArr.length;
		for (int i = 0; i < this.size-1; i++) {
			
			if (currentPos < index) {
				newArr[i] = this.arr[i];
			}
			
			else if (currentPos == index) {
				newArr[i] = t;
				newArr[++currentPos] = this.arr[i];
				
			}
			
			else if (currentPos < this.size) {
				newArr[i+1] = this.arr[i];
			}
			
			currentPos++;
		}
		this.size = newArr.length;
		this.arr = newArr;
		}
	}

	public void appendList(ArrayList<Integer> t) {
		for (int i=0; i<t.size; i++) { 
			this.append((T) t.get(i));
		}
	}

	@Override
	public T get(int index) {
		
		if (index < this.size) {
			return (T) this.arr[index];
		} else {
			return null;
		}
	}

	@Override
	public int findIndex(T t) {
		
		for (int i=0; i<this.size; i++) {
			if (t.equals(this.arr[i])) { return i;}
		}
		return 0;
	}

	@Override
	public boolean contains(T t) {
		for (int i=0; i < this.size; i++) {
			if (this.arr[i].equals(t)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public T removeItem(T t) {
		for (int i=0; i < this.size; i++) {
			if (this.arr[i].equals(t)) {
				T deletedItem = this.arr[i];
				this.arr[i] = null;
				return deletedItem;
			}
		}
		return null;
	}

	@Override
	public T removeIndex(int index) {
		
		if (index < size) {
			Object deletedItem = this.arr[index];
			this.arr[index] = null;
			for (int i = index+1; i < this.size; i++) {
				this.arr[i--] = this.arr[i];
			}
			
			return (T) deletedItem;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void appendList(T... t) {
		// TODO Auto-generated method stub
		
	}

	public void append(Integer t) {
		// TODO Auto-generated method stub
		if (this.pos < this.size) {
			this.arr[pos] = (T) t;
			this.pos++;
		} else {
			expandArray();
			this.arr[pos] = (T) t;
		}
	}

}
