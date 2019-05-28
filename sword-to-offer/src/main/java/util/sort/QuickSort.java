package util.sort;

/**
 * @program: util.sort
 * @author: mashifei
 * @create: 2019-05-26-22
 * 快排工具
 */
public class QuickSort {
    public static void sort(int[] array){
        sort(array,0,array.length-1);
    }


    private static void sort(int[] array,int left,int right){
        int pivot;
        if(left<right){
            pivot = partition(array,left,right);
            sort(array,left,pivot-1);
            sort(array,pivot+1,right);
        }
    }

    private static int partition(int[] array,int left,int right){
        int pivotKey;
        pivotKey = array[left];

        while(left<right){
            while(left<right && pivotKey<=array[right]){
                right--;
            }
            swap(array,left,right);
            while(left<right && pivotKey>=array[left]){
                left++;
            }
            swap(array,left,right);
        }
        return left;
    }

    private static void swap(int[] arr,int i,int j){
        int temp =arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
