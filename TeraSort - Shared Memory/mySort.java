import java.io.*;
import java.util.*;


public class mySort {
	
	
	
	public class fileReading{
		public void fileOpening(File fp) {
		//	File fp = new File("/input/data-20GB.in");
			//File fp = new File("C:\\Users\\pooja\\Documents\\Assignment2\\data-2GB1.in");
			 long khaliJagah = Runtime.getRuntime().freeMemory();
			 long size_of_chunk = khaliJagah / 100;
			 long size_of_file = fp.length();
			try {			
				FileReader freader = new FileReader(fp);
				String l;
				BufferedReader bReader = new BufferedReader(freader);
				StringBuffer sBuffer = new StringBuffer();
				long files_no = (size_of_file/100)/size_of_chunk;
				//System.out.println("Number of files to be created to sort = "+files_no);
				freader.close();				

			} catch (IOException e) {
				System.err.println("File Not Found");
			}					
	}
		FileWriter fw;
		ArrayList<String> list_of_data = new ArrayList<String>();
		
		String splitted_name_of_file = "Division";
		public  int split_and_conquer(File file,long chunk_FileSize,long fileSize,int numOfThreads) throws Exception
		{	long LN = 0,count = 0,LN1=0;
		FileReader fr;
		BufferedReader br;
		fr	 = new FileReader(file);
			 br = new BufferedReader(fr);		
			boolean t = true;
			//to track the number of lines accessed in previous iteration to write one file
			while(t)
			{
				count = LN;
				String line ="";
				LN = count;
				LN1=LN;
				//write to file block by block
				long totalLines = count + chunk_FileSize;
				while(LN<totalLines)
				{
					line = br.readLine();
					if(line != null)
					{
						list_of_data.add(line);//
						LN++; // 
					}
					else
						break;
					
				}
				if(line!=null) {}
				else if(line ==null)
				{
					if(!list_of_data.isEmpty())
						LN = LN+chunk_FileSize;
					else if(list_of_data.isEmpty())
						break;
						
				}
				 String filename = "/tmp/" + splitted_name_of_file + LN/chunk_FileSize + ".txt";
				//String filename = splitted_name_of_file + LN/chunk_FileSize + ".txt";
				File b = new File(filename);
				fw = new FileWriter(b);
				//System.out.println("number of blocks =" + ((fileSize/100)/chunk_FileSize));
				int l = list_of_data.size()-1;
				ArrayList<String> sorted_data_List = sorting_by_mergeSort(list_of_data,0,l,numOfThreads);
				//System.out.println("Creating sorted chunk file" + (LN/chunk_FileSize));
				for(String line_ele: sorted_data_List)
						fw.write(line_ele+" \n");
				list_of_data.clear();
				fw.close();
			}
			br.close();
			double z1 = LN/chunk_FileSize;
			int sortedFiles = (int)z1;
			//System.exit(1);		
			return sortedFiles;
		}
		public void mergeSortedFiles(int nf, long fs) throws Exception
		{int fn ;
		fn= 0 ;
		String mergeArray[] = new String[nf];
			BufferedReader br[] = new BufferedReader[nf];
			long numFileLine[] =  new long[nf];
			long mer_len;
			mer_len = 0L;
				
			
			while(fn < nf)
			{String name_of_file = "/tmp/" + splitted_name_of_file + (fn+1)+".txt";
				//String name_of_file = splitted_name_of_file + (fn+1)+".txt";
				FileReader frr1= new FileReader(new File(name_of_file));
				br[fn] =  new BufferedReader(frr1);
				long s1=fs/100;
				numFileLine[fn] =s1;
				if(numFileLine[fn]<=mer_len)
				{
					
				}
				else {	
					mer_len = numFileLine[fn];
				}
				
				fn=fn+1;
				
			
				
				
			}
			

			String rd_vl = "", small_val = "";
			int pos = -1;

			fw = new FileWriter(new File("/tmp/sortedfile"+".txt"));
			//fw = new FileWriter(new File("SortedFile"+".txt"));
			int l = 0;
			long s2=fs/100;
			while(l<s2)	
			{
				for(int ele=0;ele<nf;ele++)
				{
					if(mergeArray[ele] == null)	
					{
						rd_vl = br[ele].readLine();
						if(rd_vl!=null)
						{
							
							
							if(!small_val.equals(""))
							{
							 boolean entered1= true;
							// System.out.println(entered1);
							 entered1=false;
							
							}
							else
							{
								boolean entered2= true;
								// System.out.println(entered2); 
								entered2=false;
							
								small_val = rd_vl;
							}
							if(compareLine(rd_vl,small_val))
							{
								boolean entered3= true;
								// System.out.println(entered3);
								 entered3=false;
								
								small_val = rd_vl;
								pos = ele;
							}
							mergeArray[ele]= rd_vl;
							
						}
						else
						{
							mergeArray[ele] = "";
						}	

					}
					else
					{
						

						
						
						rd_vl = mergeArray[ele];
						
						if((rd_vl.compareTo("")!=0) && (small_val.compareTo("")==0))
						{
							small_val =rd_vl;
						}
						if((rd_vl.compareTo("")!=0) && (compareLine(rd_vl,small_val)))
						{
							small_val = rd_vl;
							pos = ele;
						}
					}
				}	
				
				mergeArray[pos]=null;			
				fw.write(small_val +"\n");
				small_val = "";
					
			
			l++;		
			}
	
			int v1=1;
			while(v1<=nf)
			{
				br[v1-1].close();
				
				File f11= new File("/mount1/"+splitted_name_of_file + v1+".txt");
				f11.delete();
				v1=v1+1;
			}
			fw.close();


		}



		public  ArrayList<String> sorting_by_mergeSort(ArrayList<String> list_of_data, long start, long end, int nt_no_thrds) throws Exception
		{	
			boolean entered3=true;
			//System.out.println(entered3);
			ArrayList<String> right =null;
			entered3=false;
			
			Thread thr=null;
			boolean entered4=true;
			//System.out.println(entered4);
			ArrayList<String> left = null;
			entered4=false;
			
			
			sorting_mergesort_thrd sortleft=null;
			
			long totallen = start+end;
			long mid = totallen/2;
			
			//create and return empty arraylist if start is greater than end
			if(start==end)
			{	
				ArrayList<String> rec_1;
				String rec = list_of_data.get((int)start);
				rec_1 = new ArrayList<String>();
				
				rec_1.add(rec);
				return rec_1;
			}
			if(end>start)
			{
				//
			}
			else if(end<start)
			{
				ArrayList<String> newList = new ArrayList<String>();
				return newList;
			}
			
			
		
			
			if(nt_no_thrds>1)
				
			{
				sortleft = new sorting_mergesort_thrd(list_of_data,start,mid,nt_no_thrds/2);
				thr = new Thread(sortleft);
				thr.start();
				
						}
			else
			{
				
				left = sorting_by_mergeSort(list_of_data,start,mid,nt_no_thrds/2);
			}
	int thrd_div=nt_no_thrds/2;
			int nthreads = nt_no_thrds - thrd_div;
			int add = 1;
			right = sorting_by_mergeSort(list_of_data,mid+ add,end,nthreads);

			if(nt_no_thrds>1)
			{
			//	try 
			//{
					// join the threads
					thr.join();
				//} 
				/*catch (InterruptedException e)
				{
					System.out.println("Threads could not join");
				}*/
			
				left = sortleft.getSortedData();
				int flag = 0;
			}
			return mergeList(left,right);
		}

		
		public boolean compareLine(String s1,String s2)
		{
			
			if(s1.compareTo(s2)>0) {
				return false;
			}
			else
			{
				return true;	
			}
			/*if(s1.compareTo(s2)<=0)
				return true;
			else
				return false;
					*/
		}
		
		public  ArrayList<String> mergeList(ArrayList<String> leftlist, ArrayList<String> rightlist) throws Exception
		{ int right_pos=0;
			int left_pos =0;		
			ArrayList<String> sortedList = new ArrayList<String>();		
		
			long totallen = leftlist.size()+rightlist.size();

			for(long pos=0 ;pos<totallen;pos++)
			{
				if (right_pos >= rightlist.size()) 
				{
				String ele = leftlist.get(left_pos);
	                	sortedList.add(ele);
	                	int q= left_pos+ 1; 
	                	left_pos= q ;
	                	
	            		}
	            		else if (left_pos >= leftlist.size()) 
				{
					String ele = rightlist.get(right_pos);
	                		sortedList.add(ele);
	                		int s= right_pos+ 1; 
	                		right_pos= s ;
	                
	            		}
	            		else if (compareTwoListVal(leftlist.get(left_pos),rightlist.get(right_pos)))
				{
	            			String ele = leftlist.get(left_pos);
					sortedList.add(ele);
					int q= left_pos+ 1; 
                	left_pos= q ;
	                		
	            		}
	            		else 
				{
					String ele =rightlist.get(right_pos);
	            			sortedList.add(ele);
	            			int s= right_pos+ 1; 
	                		right_pos= s ;
	                		
	            		}
			
			}
			
			return sortedList;
		}

		private	 class sorting_mergesort_thrd implements Runnable
		{
			ArrayList<String> list_unordered;
			ArrayList<String> sortedList;
			long  end,start;
			
			int nt_no_thrds;
			public ArrayList<String> getSortedData()
			{
				return sortedList;
			}
			
			public sorting_mergesort_thrd(ArrayList<String> data_unsorted, long s, long e, int nt) 
			{
				// TODO Auto-generated constructor stub
				boolean s21=true;		
				start = s;
				//System.out.println(s21);				
				end = e;
				s21=false;			
				list_unordered = data_unsorted;
				boolean s22=true;			
				int tmp_nt = nt;
				//System.out.println(s22);
				nt_no_thrds= tmp_nt;				
				s22=false;
			}
	
			@Override
			public void run()
			{ 
				try
				{
				//call mergesort from run method
				sortedList = sorting_by_mergeSort(list_unordered, start, end, nt_no_thrds);
				}
				catch(Exception e1)
				{
				e1.getMessage();			
				}		
			}		
	}
	
		
		
		
		public  boolean compareTwoListVal(String l,String r)
		{
			
			if(l.compareTo(r) > 0)
			{
			return false;
			}
		else
		{
			return true;
			
		}
		
		}
	
	}
	public static void main(String[] args) throws Exception
	{	//  File fp = new File("/input/data-2GB.in");
		File fp = null;
		if(args.length > 0)
			
		{
		
			fp = new File(args[0]);
		}
		mySort ms= new mySort();
		fileReading fr= ms.new fileReading();		
		fr.fileOpening(fp);			
		 long khaliJagah = Runtime.getRuntime().freeMemory();
		 long size_of_chunk = khaliJagah / 100;

		long start_time = System.currentTimeMillis();

		int numSortFile = fr.split_and_conquer(fp,size_of_chunk,fp.length(),4);
	
		fr.mergeSortedFiles(numSortFile,fp.length());

		long end_time = System.currentTimeMillis();

                double end_start =end_time - start_time;		
                long k= 15000;
               long p =1000000 ;
               double time = end_start/1000;
               long i = k*p;
                if (fp.length() <= i ) {           
		/*String exe_time_2gb = "Compute Time :  2 GB file  = "+time+" sec";
		File file_log = new File("my2GBtime.log");
		FileWriter fl = new FileWriter(file_log);
		fl.write(exe_time_2gb);*/
		System.out.println("Execution/Compute time for 2 GB file sort = "+time+" sec");
		//fl.close();
                }               
                else
                {
                	/*String exe_time_20gb = "Compute Time :  20 GB file   = "+time+" sec";
            		File file_log = new File("my20GBtime.log");
            		FileWriter fl = new FileWriter(file_log);
            		fl.write(exe_time_20gb);*/
            		System.out.println("Execution/Compute time for 20 GB file sort = "+time+" sec");
//            		fl.close();
                }		
	}
}



