/*
*@author Group8
*/
package heap_part;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Heap<Integer> h = new Heap<>();

        System.out.println("Random numbers inserted in heap:");

        Random rn = new Random();
        for(int i = 0; i < 15; i++){
            int number = rn.nextInt(10);
            System.out.println(number);

            h.add(number);
        }

        System.out.println("Extracted numbers from heap:");

        for(int i = 0; i < 15; i++){
            System.out.println(h.removeMin());
        }

    }
}