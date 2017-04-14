import java.util.* ;

public class mergeSort{


	public static int[] arr;
	public static int[] temp;

	public static void mergeSort(int low,int high){


			if (low<high){
				int middle = low+(high-low)/2 ;
				mergeSort(low,middle) ;
				mergeSort(middle+1,high) ;
				merge(low,middle,high) ;
			}

	}

	public static void merge(int low,int middle,int high){

		for (int i = low; i <= high; i++) {
            temp[i] = arr[i];
        }
        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
        
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            arr[k] = temp[i];
            k++;
            i++;
        }

	}

	public static void main(String[] args){


		Scanner s = new Scanner(System.in) ;
		int n = s.nextInt() ;
		arr=new int[n];
		for(int i=0 ; i<n ; i++){
	    arr[i]=s.nextInt() ;
	  }
	  temp=new int[n];
	  System.arraycopy( arr, 0, temp, 0, arr.length );
	  long startTime = System.nanoTime() ;
	   mergeSort(0,arr.length-1);
		long endTime = System.nanoTime() ;
		System.out.println("Time : "+(endTime-startTime)) ;

	}

}
