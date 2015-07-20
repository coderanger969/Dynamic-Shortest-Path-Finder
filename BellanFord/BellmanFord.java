
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Edge
{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}


class Vertex implements Comparable<Vertex>
{
	public boolean flag=false;
    public final String name;
    //public Edge[] adjacencies;
    public double [][] Dtable;
    ArrayList <Edge> adjacencies = new ArrayList<Edge>();
    public double minDistance = Double.POSITIVE_INFINITY;
   // public Vertex previous;
   // public int [] NextHop = new int [5];
    public int [] NextHop;
    
    public Vertex(String argName,int N) {
    	Dtable= new double[N][N];
    	NextHop = new int[N];
    	name = argName;
    	for(int i=0;i<N;i++){
    		for(int j=0;j<N;j++){
        		
    			Dtable[i][j] = Double.POSITIVE_INFINITY;
        		
        		
        	}
    		
    		
    	}
    	
    }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    } 
     
    
}

public class BellmanFord
{
	double Ctable [][];
	Vertex[] vertices;
	int NumVerti,N;

	
	BellmanFord(){
		
		
	}
	
	
	
	void BuildGraph(String Fname) throws Exception{
		
		FileInputStream fstream = new FileInputStream(Fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		
		NumVerti = Integer.parseInt(br.readLine());
		N=NumVerti+1;
		System.out.println("number of vertices is   "+ NumVerti);
		
		vertices = new Vertex[NumVerti+1];
		
		Ctable= new double[N][N];
		for (int i=0;i<N;i++){
			 
			 for(int j=0;j<N;j++){
				 
				 Ctable[i][j]=Double.POSITIVE_INFINITY;
				 if(i==j){ 
					 Ctable[i][j]=0;
				 }
				 
				 
			 }
	   }
		
		
	    
		for(int i=1;i<=NumVerti;i++){
			String Vname ="v#"+i;
			//System.out.println(Vname);
			
			vertices[i] = new Vertex(Vname,NumVerti+1);
			
		}
		
		int Svertex,Dvertex;double w;
		String strLine; 
		while ((strLine = br.readLine()) != null)   {
			String[] result1 = strLine.split(" ");
			
		     Svertex = Integer.parseInt(result1[0]);	
		     
		     
		     Dvertex = Integer.parseInt(result1[1]);
		    // System.out.println( result1[0]+ result1[1]);
		     w = Double.parseDouble((result1[2]));
		     Vertex S,D;
		     S= vertices[Svertex];
		     D= vertices[Dvertex];
		     
		     S.adjacencies.add(new Edge(D,w));
		     
		    D.adjacencies.add(new Edge(S,w));
		     
		Ctable[Svertex][Dvertex]= w;
		Ctable[Dvertex][Svertex]= w;
		    
			
		}

		//Close the input stream
		br.close();
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	void BF(Vertex v){
		
		
		boolean Tflag=false;
		 
		 //To compute DV of my own
		v.flag=false;
			 
		String[] result2 = v.name.split("#");
		
			int i=Integer.parseInt(result2[1]);
		 for (Vertex u : vertices)
			{
			 if(u!=null){
				 String[] result3 = u.name.split("#");
			 int j = Integer.parseInt(result3[1]);
			 
			 //v.Dtable[i][j]= 
			 if(i==j) { v.Dtable[i][j]=0;}
			 else{
				 for (Edge e : v.adjacencies)
					{
					 
					 Vertex neigh1 = e.target;
					 String[] result6 = neigh1.name.split("#");
					 
					 int z = Integer.parseInt(result6[1]);
					 
									 
									 				 
					 
					 if(i!=z){
						 
						 double newValue= Ctable[i][z] + v.Dtable[z][j];
						// System.out.println(i+"  " + z+"  "+ j);
						//System.out.println("new value =" + newValue);
						//System.out.println("Dtable"+v.Dtable[i][j]);
						 
						 
						 
						 if(newValue <= v.Dtable[i][j]){ 
						 v.NextHop[j] = z;
						 }
						 
						 if(newValue < v.Dtable[i][j]){
							 
				
							 
							 System.out.println("**");
							 System.out.println("updated value  " + newValue);
						 v.Dtable[i][j]=newValue; 
						 v.Dtable[j][i]=newValue; 
						 
						 Tflag= true; 
						 
						 
						// v.previous = neigh1;
						 
						 System.out.println("I got updated !!");
						 
						 
						 
						 }
					  
						 
					 }
					 
					 
					} //inner for
				 
				 
			 }
			 }
			 
			}
		 
		 
	
		 // copied her D vector into neighbours  table
		 
		 if(Tflag){
		 for (Edge e : v.adjacencies)
         {
			 Vertex neigh = e.target;
			 
			 String[] result5 = v.name.split("#");
			 
			 
			 
			 int R = Integer.parseInt(result5[1]);
			 
			// neigh.Dtable[R] = v.Dtable[R];
			 
			 for(int yu=1;yu<N;yu++){
				 
				 neigh.Dtable[R][yu] = v.Dtable[R][yu];
				 
				 
				 
			 }
			 
			 
			 
			 System.out.println(v.Dtable[R]);
			 neigh.flag= true;
			 System.out.println("They  got updated !!");
			 
         }
	
		 }// if
		 
		 
		 
		
	}
	
	void initialise(){
		
		for (Vertex u : vertices)
		{
			if(u!=null){
			System.out.println(u.name);
			
			int length = Ctable.length;
			
			System.out.println(length);
			String[] result77 = u.name.split("#");
			 
			 
			 
			// int i = Integer.parseInt(result77[1]);
			
			for (int i= 0; i < length; i++) {
		    	 for (int j = 0; j < length; j++) {
		    	
		    	//System.arraycopy( Ctable[i],0,u.Dtable[i],0,length);
		    		 
		    		// System.out.println(Ctable[i][j]);		 
		    	u.Dtable[i][j]=Ctable[i][j];
		    	
		    	 
		    }
			}
			
			u.flag=true;
			
			
		}
		
		}
		
		
		
		
	/*	
		for (Vertex u : vertices)
		{
		 for (Edge e : u.adjacencies)
	    {
		
			int i = Integer.parseInt(u.name); 
			int j= Integer.parseInt(e.target.name);
			 v.Dtable[i][j]=e.weight;
			 
			 
	    }
		}*/
		
	}
	
	
	
	
	
	
	
public static void main(String[] args) throws Exception
{
	Scanner in = new Scanner(System.in);
	System.out.println("Enter command as : distance_vector <initial-node> file-name node1 node2");
	String cmd = in.nextLine();
	
	String[] cmdinfo = cmd.split(" ");
	
	int initial_node=Integer.parseInt(cmdinfo[1]); 
	
	int sindex,dindex;
	String fileName = cmdinfo[2];
	sindex= Integer.parseInt(cmdinfo[3]); 
	dindex = Integer.parseInt(cmdinfo[4]); 
	System.out.println("@@@@@    " + sindex);
	System.out.println("#####    " + dindex);
	
	
	
	BellmanFord b = new BellmanFord();
	b.BuildGraph(fileName);
	b.initialise();
	Vertex initn = b.vertices[initial_node];
	Vertex snode = b.vertices[sindex];
	Vertex dnode = b.vertices[dindex];
	
	//int sindex=2;
	//Vertex s = b.vertices[sn];
	long startTime = System.currentTimeMillis();
	b.BF(initn);
	int count =0;
	Boolean Run=true;
	while(Run){
		
		count++;
		Run= false;
	for (Vertex u : b.vertices)
	{
		if(u!=null){
		if(u.flag){
			
			b.BF(u);
			System.out.println("Inside while");
			Run=true;
			
			
		}
		
		}
		
	}
	
	}
		
	long elapsedTime = System.currentTimeMillis()-startTime;
   // System.out.println(" time taken is " +  elapsedTime); 
    //System.out.println(" number of iterations =  " + count);
	
	// Printing Dtable of source node.
    
    System.out.println("printing routing table of node 1");
    Double Cost=0.0; int hop=0;
 Vertex vf = b.vertices[sindex];
	
	for(int j=1;j<b.N;j++){
		 
		if(j==dindex){
			Cost = vf.Dtable[sindex][j];
			hop = vf.NextHop[j];
			
		}
		
		
		 System.out.println("Cost of Source  "+sindex+ "to "+ j+ "is   "      +vf.Dtable[sindex][j]+ " with next hop   " + vf.NextHop[j]);
		 
		 }
	
	
	System.out.println(" ********    Cost of Source  "+sindex+ "to "+ dindex + "is   "      +Cost+ " with next hop   " + hop);
	
	
	
	
	
	
	
	
	// printing Destination port Routing table
	System.out.println("printing routing table of node 2");
	
	
Vertex vf1 = b.vertices[dindex];
	
	for(int j=1;j<b.N;j++){
		 
	
		 System.out.println("Cost of Source  "+dindex+ "to "+ j+ "is   "      +vf1.Dtable[dindex][j]+ " with next hop   " + vf1.NextHop[j]);
		 
		 }
	
	
	
	
	
	
	
	
	
/*	
	
  //Vertex vf = b.vertices[1];
	
for (int i=1;i<b.N;i++){
		 
		 for(int j=1;j<b.N;j++){
			 
			 System.out.print(vf.Dtable[i][j]+ "    ");
			 
			 }
		 System.out.println("");
		 
	 }
	
	*/
	
	

} //main

}// BellmanFord class







