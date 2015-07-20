
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
    public  Vertex target;
    public  double weight;
    public Edge(Vertex Target, double Weight)
    { target = Target; weight = Weight; }
}


class Vertex implements Comparable<Vertex>
{
    public  String name;
    //public Edge[] adjacencies;
    ArrayList <Edge> adjacencies = new ArrayList<Edge>();
    public double minDist = Double.POSITIVE_INFINITY;
    public Vertex prev;
    public String prev1;
    public Vertex(String Name) { name = Name; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDist, other.minDist);
    }
    
  
    
    
}



public class Dijkstra
{
    
	public void  buildGraph(){
		
		
		
		
	}
	
	
	
	
	public  void runDijkstra(Vertex source)
    {
        source.minDist = 0.;
        PriorityQueue<Vertex> VQueue = new PriorityQueue<Vertex>();
        VQueue.add(source);

	while (!VQueue.isEmpty()) {
	    Vertex u = VQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceViaU = u.minDist + weight;
		if (distanceViaU < v.minDist) {
			VQueue.remove(v);
		    v.minDist = distanceViaU ;
		    v.prev = u;
		    
		    v.prev1= u.name;
		    VQueue.add(v);
		}
            }
        }
    }

    public  List<Vertex> getPath(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        
      
        for (Vertex vertex = target; vertex != null; vertex = vertex.prev){
            path.add(vertex);
            
            
        }
        
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) throws IOException
    {
    	Dijkstra d1 = new Dijkstra();
    	Dijkstra d2 = new Dijkstra();
    	Scanner in = new Scanner(System.in);
    	System.out.println("Enter the comand as link_state filename node1 node2 ");
    	String cmd = in.nextLine();
    	
    	String[] cmdinfo = cmd.split(" ");
    	String filename= cmdinfo[1];
    	int sindex=Integer.parseInt(cmdinfo[2]); 
    	int dindex=Integer.parseInt(cmdinfo[3]);  
    	
    	
    	
    	
    	FileInputStream fstream = new FileInputStream(filename);
    	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    	double Cost=0.0;
    	int NumVerti;
    	NumVerti = Integer.parseInt(br.readLine());
    	System.out.println("number of vertices is   "+ NumVerti);
    	
    	Vertex[] vertices = new Vertex[NumVerti+1];
    	
        // creating vertex objects
    	for(int i=1;i<=NumVerti;i++){
    		String Vname ="v"+i;
    		//System.out.println(Vname);
    		
    		vertices[i] = new Vertex(Vname);
    		
    	}
    	
    	
 
	
		
		
	// Building graph by reading edges from file
	
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
	     
		
	}

	//Close the input stream
	br.close();
	
		
		
		
	
	
	/*Scanner in = new Scanner(System.in);
	System.out.println("Enter the comand as link_state filename node1 node2 ");
	 
	int sindex,dindex;
	sindex= in.nextInt();
	dindex = in.nextInt();*/
	System.out.println("@@@@@    " + sindex);
	System.out.println("#####    " + dindex);
	
	Vertex snode = vertices[sindex];
	Vertex dnode = vertices[dindex];
	long startTime = System.currentTimeMillis();
	d1.runDijkstra(snode);
	
        
	System.out.println("printing source routing table");
        for (Vertex v : vertices)
	{
        	 // System.out.println("inside for");
        	// System.out.println(v.name + "    "+ snode.name);
        	 //System.out.println(snode.name);
        	 if(v !=null){
        	if(!((v.name).equals(snode.name))){
	    System.out.println("Distance to " + v + ": " + v.minDist);
	    
	    
	    if((v.name).equals(dnode.name)){
	    	
	    	Cost = v.minDist;
	    	
	    }
	    
	    List<Vertex> path = d1.getPath(v);
	    //System.out.println("Path: " + path);
	    System.out.println(path.get(0)+","+ path.get(1));}
	    
        	 }
	    
	}
        for (Vertex v2 : vertices)
    	{
            	
            	 if(v2 !=null){
            		 v2.prev=null;
            	if(!((v2.name).equals(dnode.name))){
            		
            		v2.minDist=Double.POSITIVE_INFINITY;
            		
            		
            		
            	}
            	}
            	 
    	}
        
        
        
       d2.runDijkstra(dnode);
        
        // printing destination node routing table
       
       System.out.println("printing 2nd routing table");
        for (Vertex v2 : vertices)
    	{
            	
            	 if(v2 !=null){
            		 //System.out.println(v2.name+" prev is  "+ v2.prev);
            	if(!((v2.name).equals(dnode.name))){
    	    System.out.println("Distance to " + v2 + ": " + v2.minDist);
    	    
    	    
    	    
    	    
    	    List<Vertex> path1 = d2.getPath(v2);
    	    //System.out.println("Path: " + path);
    	   System.out.println(path1.get(0)+","+ path1.get(1));
    	    
            	 }//if
    	    
    	}//if 
        
        
        
    	}// for
        
        
        
        
        
        
        System.out.println("Cost between v" + sindex + "  v"+ dindex+ "is " + Cost);
        
        long elapsedTime = System.currentTimeMillis()-startTime;
       // System.out.println(" time taken is " +  elapsedTime); 
        
        
        
        
    }
}