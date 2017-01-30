#include<stdio.h>
#include<math.h>
#include<stdlib.h>
#include<string.h>
#include<pthread.h>


#define max 9000 //number of cells per array

int *a,*b,*c ;

pthread_t thread_id[2];

int num_of_dig(int n)
{
	int dig=0 ;
	while(n)
	{
		n/=10 ;
		dig++ ; 
	}
	return dig ;
}

int makefour(int num) //make sure every cell has four digits
{
	int new=0 ;
	int i= 0 ; 
	while(i<=3)
	{
		new+=num%10*pow(10,i) ;
		num/=10 ;
		i++ ;
	}
	return new ;
}

void* calc_fib(void* n) //function every thread runs
{
	int *n1 = n ;
	int num = *n1 ;
	int *a,*b,*c ;
	a= calloc(max , sizeof(int)) ;
	b= calloc(max , sizeof(int)) ;
	c= calloc(max , sizeof(int)) ;
	
	if(num==2 || num==1)
	{
		c[max-1]=1 ;
	}
	
	else
	{
	int i,carry=0 ,j,k;
	a[max-1]=b[max-1]=1 ;
	
	for(i=3 ; i<=num ; i++)
		{
			for(j=max-1 ; j>=0 ; j--) //c=a+b
			{
				c[j]=a[j]+b[j]+carry ;
				carry= 0;
				if(c[j]%10000!=0)
				{
					carry=c[j]/10000 ;
					c[j]=makefour(c[j]) ;
				}
			}
			
			
			for(k=0 ; k<=max-1 ; k++) //equivalent to a=b b=c
			{
				a[k]=b[k] ;
			
				b[k]=c[k] ;
			}
		}
		
		
	}
	
	
	return c ;
	
		
}


void main()
{
	
	int num,carry= 0,j ;
	pthread_t thread_id[4];
	void *res_a,*res_b,*res_c;
	
	int *res1_a , *res1_b ,*res1_c ;
	int *result = calloc(max,sizeof(int)) ;
	
	
	
	
	printf("Enter term number of fibonacci : ") ;
	scanf("%d" , &num) ;
	int num1 = num-2 ;
	int num2 = num-3 ;
	int num3 = num-4 ;
	int i;
	printf("\n") ;
	if(num==1)
		printf("1") ;
	else if(num==2)
		printf("1") ;

	else
	{
		
		
		
			
			pthread_create(&thread_id[0],NULL,calc_fib,&num1) ;
			pthread_create(&thread_id[1],NULL,calc_fib,&num2) ;
			pthread_create(&thread_id[2],NULL,calc_fib,&num3) ;
			
		    pthread_join(thread_id[0],&res_a);
		    pthread_join(thread_id[1],&res_c);
		    pthread_join(thread_id[2],&res_b);
		    
		    res1_a=res_a;
		    res1_b=res_b ;
		    res1_c=res_c ;
		   
			//perform additions on results obtained from threads
			
			for(j=max-1 ; j>=0 ; j--)
			{
				result[j]=res1_a[j]+res1_b[j]+carry ;
				carry= 0;
				if(result[j]%10000!=0)
				{
					carry=result[j]/10000 ;
					result[j]=makefour(result[j]) ;
					
				}
			}
			
			for(j=max-1 ; j>=0 ; j--)
			{
				result[j]=result[j]+res1_c[j]+carry ;
				carry= 0;
				if(result[j]%10000!=0)
				{
					carry=result[j]/10000 ;
					result[j]=makefour(result[j]) ;
					
				}
			}
			
			for(j=max-1 ; j>=0 ; j--)
			{
				result[j]=result[j]+res1_c[j]+carry ;
				carry= 0;
				if(result[j]%10000!=0)
				{
					carry=result[j]/10000 ;
					result[j]=makefour(result[j]) ;
					
				}
			}
			
			
			for(int j=0 ; j<=max-1 ; j++)
			{
				if(num_of_dig(result[j])==3)
					printf("0") ;
				printf("%d",result[j]) ;
			} 
	
	
	}

}


