from multiprocessing import Process
import time


start_time = time.time()

def fun(n) :
	while n>0 :
		n-=1
	
if __name__ == '__main__':
    p = Process(target=fun, args=(1000000000,))
    q = Process(target=fun, args=(1000000000,))
    p.start()
    q.start()
    p.join()
    q.join()
    
    
print("--- %s seconds ---" % (time.time() - start_time)) 

'''
4.78644895554 seconds

'''
