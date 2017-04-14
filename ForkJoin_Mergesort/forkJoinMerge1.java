import java.util.* ;
import java.util.concurrent.*;

public class forkJoinMerge1 {

  public static int[] array ;
  public static int[] temp;
  private static int n;
  forkJoinMerge1(){
  Scanner s = new Scanner(System.in) ;
  n=s.nextInt() ;
  array =new int[n] ;
  for(int i=0 ; i<n ; i++){
    array[i]=s.nextInt() ;
  }
  temp=new int[n];
  System.arraycopy(array, 0, temp, 0, array.length );

  }

  public static void main(String[] args){



    forkJoinMerge1 obj = new forkJoinMerge1() ;
    ForkJoinPool p = new ForkJoinPool() ;
    long startTime = System.nanoTime() ;
    p.invoke(new MergeSort1(array,0,n-1,temp)) ;
    long endTime = System.nanoTime() ;
    System.out.println("Time : "+(endTime-startTime)) ;
  }

}
