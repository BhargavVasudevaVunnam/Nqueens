import java.util.*;
import java.lang.Math;
import java.io.*;
public class Nqueens {
	//for getting input from infile.txt it needs to be in classpath or can give the absolute path of your file
	public static ArrayList<Integer> getinput(){
		ArrayList<Integer> values=new ArrayList<>();
		int a[]=new int[15];
		try {
		Scanner in= new Scanner(System.in);
		File file = new File("infile.txt"); 
			    Scanner sc = new Scanner(file); 
			    int firstline= Integer.parseInt(sc.nextLine());
			    while (sc.hasNextLine()) { 
			    	if(firstline<1) {
			    		break;
			    	}
			    	values.add(Integer.parseInt(sc.nextLine().replaceAll("\\s+", ""))); 
			    	firstline--;
			    }
	}
		catch(Exception e) {
		System.out.println(e);
		}
		return values;
	}
	//for storing the input values after conversion into one dimensional array
	 public static int[] processinginput(int n) {
	    	int a[]=new int[8];
	    	int i=7;
	    	while(n>0){
	    	   a[i]=n%10;
	    	   n=n/10;
	    	   i--;
	    	}
	     	return a;
	    }
	 // for calculating the no of queens attacking each other
  public static int[] initialattackscalculation(int[] a) {
	  int spare[]=new int[8];
	  int count=0;
	  for(int i=0;i<a.length;i++) {
		  for(int j=0;j<a.length;j++) {
			  if(i!=j) {
				  if(a[i]==a[j]||Math.abs(a[i]-a[j])==Math.abs(i-j)) {
					  count++;
				  }
			  }
		  }
		  spare[i]=count;
		  count=0;
	  }
	  return spare;
  }
  //for getting the max value
  public static int getinitialpositon( int[] arr)
  {
      int result=0;
      int max = arr[0];  
      for (int i = 1; i < arr.length; i++)
          if (arr[i] > max)
              max = arr[i];    
      for(int j=0;j<arr.length;j++) {
    	  if(arr[j]==max) {
    		  result=j+1;
    		  break;
    	  }
      }
      if(arr[result-1]==0) {
    	  System.out.println("success");
    	  return 9999;
      }
      else
      return result;
  }
  // used for printing the array
  public static void printarray(int spax[]) {
	  for(int i=0;i<spax.length;i++) {
		  System.out.print(spax[i]+" ");
	  }
  }
  public static int[] anothercomputationagain(int[] currentstate,int position) {
	 int[] valuesarray=new int[8];
	 int[] outputstate=new int[8];
	 outputstate=currentstate.clone();
	 for(int i=0;i<=7;i++) {
		 outputstate[position-1]=i+1;
		 valuesarray[i]=initialattackscalculation(outputstate)[position-1];
	 }
	  return valuesarray;
  }
  //for least valued row calculation
  public static int leastrowcalculationn2(int[] arr,int[] v) {
	  int result=0;
	  int spare[]=new int[8];
	  spare=v.clone();
      int least = spare[0];  
      for (int i = 1; i < arr.length; i++)
          if (spare[i] < least)
              least = spare[i];    
      for (int j=0;j<=arr.length;j++) {
    	  if(spare[j]==least) {
    		  result=j+1;
    		  break;
    	  }
      }
      return result;
  }
  public static void maininput(int[] gettinginput) {
	  // loop variables usage starts
	  int queensattackingeachothervalue[]=new int[8];
	  int positionofthefirstmaxvalueattacking;
	  int valuesatthatspecificposition[]=new int[8];
	  //loop variables usage ends
	  int input[]=new int[8];
	  int values[]=new int[8];
	  int nextstate[]=new int[8];
	  int n2=0,n1=0;
      input=gettinginput.clone();
	  int output[]=new int[8];	  
	  while(true){
		  printarray(input);
		  System.out.println("");
		  queensattackingeachothervalue=initialattackscalculation(input);
		  positionofthefirstmaxvalueattacking=getinitialpositon(queensattackingeachothervalue);
		  if((positionofthefirstmaxvalueattacking)==9999) {
			  break;
		  }
		  n1=queensattackingeachothervalue[positionofthefirstmaxvalueattacking-1];
		  valuesatthatspecificposition=anothercomputationagain(input,positionofthefirstmaxvalueattacking);
		  n2=valuesatthatspecificposition[leastrowcalculationn2(input,valuesatthatspecificposition)-1];
		  if(n2>=n1) {
			  System.out.println("Failure");
			  break;
		  }
		  else {
			  input[positionofthefirstmaxvalueattacking-1]=leastrowcalculationn2(input,valuesatthatspecificposition);
		  }
	  }
  }
  public static void main(String[] args) {
	  ArrayList<Integer> input=getinput(); // for getting input 
	  try {
	  PrintStream fileOut = new PrintStream("outfile.txt"); // setting the output file
	  System.setOut(fileOut); // setting the system out to file out
	  }
	  catch(Exception e ) {
		  System.out.println(e);
	  }
      for(int i=0;i<input.size();i++) {
    	  maininput(processinginput(input.get(i)));//for converting and passing the input
    	  System.out.println();
      }
      
}
  
}
