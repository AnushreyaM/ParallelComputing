#include<stdio.h>
#include<math.h>
#include<stdlib.h>
#include<string.h>

#define max 9000 //number of cells per array

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

void main()
{
	int *a,*b,*c ;
	int i,j,k,num,carry=0 ;
	
	a= calloc(max , sizeof(int)) ;
	b= calloc(max , sizeof(int)) ;
	c= calloc(max , sizeof(int)) ;

	a[max-1]=b[max-1]=1 ; c[max-1]=0 ;
	
	
	printf("Enter term number of fibonacci : ") ;
	scanf("%d" , &num) ;
	printf("\n") ;
	if(num==1)
		printf("1") ;
		
	else if(num==2)
		printf("1") ;

	else
	{
		
		
		
		for(i=3 ; i<=num ; i++) // run num-2 additions
		{
		
	
			for(j=max-1 ; j>=0 ; j--) //perform c=a+b 
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
	
		for(i=0 ; i<=max-1 ; i++) //print value
		{
			if (num_of_dig(c[i])==3)
				printf("0") ;
			printf("%d" , c[i]) ;
		}
	}
	

}


