import java.util.* ;
import java.util.concurrent.* ;

public class MergeSort1 extends RecursiveAction{


  public static int[] arr ;
  public static int[] temp ;
  public int low;
  public  int high;

  MergeSort1(int[]arr,int low,int high,int[]temp) {
    this.arr=arr;
    this.temp=temp ;
    this.low=low;
    this.high=high;
  }

  protected void compute(){
    if (high-low<8192){
   Arrays.sort(arr,low,high+1) ;
    }

      else if (low<high){
        int middle=low+(high-low)/2;
        MergeSort1 left = new MergeSort1(arr,low,middle,temp);
		left.fork() ;
  		MergeSort1 right = new MergeSort1(arr,middle+1,high,temp);
  		right.compute() ;
  		left.join() ;
        merge(arr,low,middle,high,temp) ;
      }


  }
  private void merge(int[] arr,int low,int middle,int high,int[] temp){

    for (int i=low;i<=high;i++){
      temp[i]=arr[i];
    }
    int i=low,j=middle+1;
    for(int k=low;k<=high;k++){
      if (i>middle){
        arr[k]=temp[j++];
      }else if (j>high){
        arr[k]=temp[i++];
      }else if(temp[i]<temp[j]){
        arr[k]=temp[i++];
      }else{
        arr[k]=temp[j++];
      }
    }

  }


}
