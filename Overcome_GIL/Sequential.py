from multiprocessing import Process
import time


start_time = time.time()

def f(n) :
	while n>0 :
		n-=1
	


if __name__ == '__main__':
    f(100000000)
    f(100000000)
    
print("--- %s seconds ---" % (time.time() - start_time)) 

'''
 8.09192991257 seconds
 
'''
