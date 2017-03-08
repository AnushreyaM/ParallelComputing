from threading import Thread
import time


start_time = time.time()

def string_search(n) :
	while n>0 :
		n-=1


t1 = Thread(target=string_search , args=(1000000,))
t1.start()
t2 = Thread(target=string_search , args=(1000000,))
t2.start()
t1.join()
t2.join() 


print("--- %s seconds ---" % (time.time() - start_time))

'''
12.9598841667 seconds

'''
