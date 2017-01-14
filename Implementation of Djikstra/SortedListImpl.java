/**
 * @author Group8;
 * */

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class SortedListImpl <E extends Comparable<E>> implements SortedList<E> {

	private E[] arr = (E[]) new Comparable[5];
	private int size=0;

	@Override
	// O(size) worst case scenario

	public void add(E elem) {
		if(size>=arr.length){
			E[] newArr = (E[]) new Comparable[size*2];
			for(int i=0; i<arr.length; i++)
				newArr[i]=arr[i];
			arr=newArr;

		}
		arr[size] = (E) elem;
		if(size!=0)
			for(int i=size; i>0; i--)
				if(arr[i].compareTo(arr[i-1])<0)
					Swap(i,i-1);
		size++; 	

	}

	@Override
	// O(size + length of array) -worse case scenario

	public void addSortedArray(E[] arr) {
		E[] newArr = (E[]) new Comparable[size+arr.length];

		int i=0;//index pointer for this.array
		int k=0;//index pointer for array

		while(i<size && k<arr.length){
			E ie = (E) this.arr[i];
			E ik = (E) arr[k];

			if(ie.compareTo(ik)<=0){
				newArr[i+k] = ie;
				i++;
			}else{
				newArr[i+k] = ik;
				k++;
			}

		}
		for(;i <size; i++)//add reminder
			newArr[i+k] = this.arr[i];

		for(;k <arr.length; k++)//add reminder
			newArr[i+k] = arr[k];
		this.arr = newArr;
		this.size = newArr.length;

	}
	//swap method
	public void Swap(int i, int i2){
		Comparable<E> e = arr[i];
		arr[i] = arr[i2];
		arr[i2] = (E) e;
	}

	@Override
	public E get(int ix) {//O(1)
		if(ix>=size || ix<0)
			return null;

		return arr[ix];
	}

	@Override
	// O(logH)-worst case scenario
	public int firstIndex(E elem) {
		int hi,lo,equal;
		hi=size-1;
		lo=0;
		equal=size;
		int larger = size;
		while(lo<=hi){
			int mid = (lo+hi)/2;
			E e = arr[mid];
			if(e.compareTo(elem)==0){
				hi=mid-1;
				equal=mid;
			}
			if(e.compareTo(elem)<0)
				lo=mid+1;
			if(e.compareTo(elem)>0){
				hi=mid-1;
				larger=mid;
			}
		}
		if(equal!=size)
			return equal;

		return larger;

	}

	@Override
	public int lastIndex(E elem) {
		int equal = -1;
		int smaller = -1;
		int hi=size-1;
		int lo=0;

		while(lo<=hi){
			int mid = (hi+lo)/2;

			E e = arr[mid];

			if(e.compareTo(elem)==0){
				lo=mid+1;
				equal=mid;
			}
			if(e.compareTo(elem)<0){
				lo=mid+1;
				smaller=mid;
			}
			if(e.compareTo(elem)>0)
				hi=mid-1;
		}
		if(equal!=-1)
			return equal;

		return smaller;

	}

	@Override
	//O(logN)
	public boolean contains(E elem) {
		int hi=size-1;
		int lo=0;
		while(lo<=hi){
			int mid = (lo+hi)/2;
			E e = arr[mid];
			if(e.compareTo(elem)<0){
				lo = mid+1;
			}else if(e.compareTo(elem)>0){
				hi=mid-1;
			}else{
				return true;
			}
		}
		return false;
	}

	@Override
	public int countBetween(E lo, E hi) {
		return lastIndex(hi) - firstIndex(lo) + 1;
	}

	@Override
	public int size() {
		if(size==0)
			return -1;
		return size;
	}
	public int test(int i1, int i2){
		return arr[i1].compareTo(arr[i2]);
	}


}