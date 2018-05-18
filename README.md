A20396099
NAME : Pooja Hemantkumar Patel 
Programming Assignment 2:

Following shows how to run the linux sort and mySort : 

Steps : 

	a. Login to Neutron
	b. Navigate to cs553-pa2a folder
	c. Type following command:
	
		i. make linSort

		( Note : This will run both linux sort for 2GB and 20GB and also it will give the output for valsort for both. 
		This command will submit 2 jobs which will be executed one after the other. One job is submitted for linsort2GB 
		and other for linsort20GB. Also it will generate two log files names "linsort2GB.log" for linux sort 2 GB and
		"linsort20GB.log" for linux sort 20 GB )
	
		ii. make mySort 
		
		( Note : This will run both mySort for 2GB and 20GB and also it will give the output for valsort for both. 
		This command will also submit 2 jobs which will be executed one after the other. One job is submitted for mysort2GB 
		and other for mysort20GB. Also it will generate two log files names "mysort2GB.log" for mysort 2 GB and
		"mysort20GB.log" for mysort 20 GB )