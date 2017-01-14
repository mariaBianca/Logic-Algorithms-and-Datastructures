/*@author Group8;
 */

package heap_part;

public class Heap<E extends Comparable<E>>{
    
    private E[] arr;
    
    private int size = 0;
    
    public Heap(){
        arr = (E[]) new Comparable[8];
    }
    
    
    public E findMin(){
        return (size == 0) ? null : arr[1];
    }
    
    public E removeMin(){
        E min = findMin();

        // Trivial case
        if (size == 0)
            return null;

        // Trivial case
        if (size == 1){
            size = 0;
            return min;
        }

        // swap 1 element with last and decrease size
        arr[1] = arr[size];
        size--;

        // shift 1 element down
        int current = 1;  // current element we consider
        int smallest; // index of smallest element among current and its children (if it has them)
        while(current <= size){
            smallest = current;
            // if left child is less than smallest
            if (2*current <= size && arr[2*current].compareTo(arr[smallest]) < 0)
                smallest = 2*current;

            // if right child is less than smallest
            if (2*current+1 <= size && arr[2*current+1].compareTo(arr[smallest]) < 0)
                smallest = 2*current+1;

            // If smallest element is among children, we need to swap them
            if (smallest != current) {
                E temp = arr[current];
                arr[current] = arr[smallest];
                arr[smallest] = temp;
                current = smallest;
            }else{ // if smallest is current, then we've done
                break;
            }
        }
        
        return min;   
        
    }
    
    
    
    public void add(E elem){
        // CHeck if have enough space to hold element, double array size if necessary
        if (size+1 >= arr.length){
            E[] newArr = (E[]) new Comparable[size*2];
            for (int i = 1; i < arr.length; i++) newArr[i] = arr[i];
            arr = newArr;
        }

        // Add element to the end of array
        arr[++size] = elem;

        // Now if it's less then its parent, swap them
        int current = size;
        int parent = size/2;
        while(current > 1 && arr[parent].compareTo(arr[current]) > 0 ){
            E temp = arr[parent];
            arr[parent] = arr[current];
            arr[current] = temp;
            current = parent;
            parent = current/2;
        }
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return size <= 0;
    }
    
}