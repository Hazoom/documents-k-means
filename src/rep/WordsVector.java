package rep;

import java.util.Vector;

/**
 * Class represents a words vector
 * @author hazoom
 *
 */
public class WordsVector {

	private Vector<Double> data;
	
	/**
	 * Constructor which gets initial capacity
	 * @param capacity
	 */
	public WordsVector(int capacity) {
		this.data = new Vector<Double>(capacity);
		for (int i = 0; i < capacity; i++) {
			this.data.addElement(0.0);
		}
	}
	
	/**
	 * Method returns the value in the given index
	 * @param index
	 * @return
	 */
	public Double get(int index) {
		if (index < 0 || index > this.data.capacity() - 1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		return this.data.get(index);
	}
	
	/**
	 * Method increment a member in the given index
	 * @param index
	 */
	public void increment(int index) {
		if (index < 0 || index > this.data.capacity() - 1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		this.data.set(index, this.data.elementAt(index) + 1);
	}
	
	/**
	 * Method calculates the Euclidean norm of the words vector
	 * @return
	 */
	public double norm() {
		double norm = 0;
		for (Double element : this.data) {
			norm += Math.pow(element, 2);
		}
		
		return Math.sqrt(norm);
	}
	
	public void multiply(double n) {
		for (int i = 0; i < this.data.capacity(); i++) {
			if (this.data.get(i) > 0) {
				this.data.setElementAt(this.data.get(i) * n, i);
			}
		}
	}
	
	public void multiply(WordsVector wv) {
		if (wv.getCapacity() == this.getCapacity()) {
		
			for (int i = 0; i < this.data.capacity(); i++) {
				this.data.setElementAt(this.data.get(i) * wv.get(i), i);
			}
		}
	}
	
	public void add(WordsVector wv) {
		if (wv.getCapacity() == this.getCapacity()) {
		
			for (int i = 0; i < this.data.capacity(); i++) {
				this.data.setElementAt(this.data.get(i) + wv.get(i), i);
			}
		}
	}
	
	public void divide(double n) {
		for (int i = 0; i < this.data.capacity(); i++) {
			if (this.data.get(i) > 0) {
				this.data.setElementAt(this.data.get(i) / n, i);
			}
		}
	}
	
	public void log() {
		for (int i = 0; i < this.data.capacity(); i++) {
			if (this.data.get(i) > 0) {
				this.data.setElementAt(Math.log(this.data.get(i)), i);
			}
		}
	}
	
	public int getCapacity() {
		return this.data.capacity();
	}
	
	public void set(int index, Double element) {
		if (index < 0 || index > this.data.capacity() - 1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		this.data.set(index, element);
	}
	
	public Double max() {
		double max = Double.MIN_VALUE;
		for (int i = 0; i < this.data.capacity(); i++) {
			if (this.data.get(i) > max) {
				max = this.data.get(i);
			}
		}
		
		return max;
	}
	
	public WordsVector clone() {
		WordsVector wv = new WordsVector(this.getCapacity());
		
		// Clone the histogram vector
		for (int i = 0; i < this.getCapacity(); i++) {
			wv.set(i, this.get(i));
		}
		
		return wv;
	}
	
	public double dotProduct(WordsVector vector) {
		
		double product = 0.0;
		if (vector.getCapacity() == this.getCapacity()) {
			for (int i = 0; i < this.data.capacity(); i++) {
				product += this.data.get(i) * vector.get(i);
			}
		}
		
		return product;
	}
}
