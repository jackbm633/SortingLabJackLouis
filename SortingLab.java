/*
 File: SortingLab.java
 Authors: Jack Beckitt-Marshall and Louis Mendez
 Date: February 28, 2018
 
 */

import java.util.Random;
import java.util.Arrays;

class SortingLab {
    
    // Quicksort (not in place because Java doesn't do pass by reference)
    // Assume array populated by data prior to this call.
    // Use the Lomuto partition method (pick last element)
    static void quickSort(int[] arr, int p, int r){
        
        if (p < r){
            int q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        }
        
    }
    
    static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p - 1;
        for (int j = p; j < r + 1; j++){
            if (arr[j] < pivot) {
                i += 1;
                // Swaps elements in j and i
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
            
        }
        // Swaps elements in indexes r and i+1
        int temp = arr[r];
        arr[r] = arr[i + 1];
        arr[i + 1] = temp;
        
        return i + 1;
    }
    
    public static int[] CountSort(int[] a, int k){
        int[] count = new int[k];
        int[] arrayb = new int[a.length];
        
        //for (int i = 0; i < count.length; i++){
            //set each of the aray values to 0
            //count[i] = 0;
        //}

        
        for (int i = 0; i < a.length; i++){
            //for every number we find, add 1 to the value at index
            count[a[i]]++;
        }

        count[0]--;
        for (int i = 1; i < k; i++){
            //calculates starting index for each key
            count[i] = count[i] + count[i-1];
        }

        
        for (int i = a.length - 1; i >= 0; i -= 1){

            //now that we have all, place them into a new array, b
            //start from last index and work to 0.
            arrayb[count[a[i]]] = a[i];
            count[a[i]]--;
        }


        
        
        return arrayb; //sorted array
        
    }

    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++){ 
            if (arr[i - 1] > arr[i]){ // Not in ascending order
                return false;
            } 
        }
        return true;
    }

    public static void main(String[] args) {

        // 1st argument is length of array
        // 2nd is max integer.

        int[] arr = new int[Integer.parseInt(args[0])];
        
        // Generate random numbers.
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++){
            arr[i] = rand.nextInt(Integer.parseInt(args[1]));
        }

        // Call counting sort to sort a, timing it using milliseconds.
        int k = Integer.parseInt(args[1]);
        double start_time = System.nanoTime();
        int[] new_arr = CountSort(arr, k);
        double end_time = System.nanoTime();
        
        //System.out.println(Arrays.toString(new_arr));

        double countingSortTime = (end_time - start_time)/1000;

        if (isSorted(new_arr)) {
            System.out.println("Counting sort works, completed in "+ 
            Double.toString(countingSortTime) + " ms");
        } else {
            System.out.println("Counting sort does not work, completed in "+ 
            Double.toString(countingSortTime) + " ms");
        }

        // Call quicksort to sort a, timing it using milliseconds.
        start_time = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        end_time = System.nanoTime();

        //System.out.println(Arrays.toString(arr));

        double quickSortTime = (end_time - start_time)/1000;

        if (isSorted(arr)) {
            System.out.println("quicksort works, completed in "+ 
            Double.toString(quickSortTime) + " ms");
        } else {
            System.out.println("quicksort does not work, completed in "+ 
            Double.toString(quickSortTime) + " ms");
        }
    }
    

}
