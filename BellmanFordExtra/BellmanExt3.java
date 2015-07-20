



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;



public class BellmanExt3
{
	double Ctable []={0,3,1,0,1};;
	public String []Identity= {"localhost$7735","localhost$7736","localhost$7737","localhost$7738"};
	//Vertex[] vertices=new vertices ();
	int NumVerti,N;
	String IPaddress[] =new String[5];
	int PortNo[] =new int[5];
	DatagramSocket clientSocket;
	DatagramSocket serverSocket;
	ByteArrayInputStream bais;
    DataInputStream dais;
    byte[] recData1 = new byte[36];
   byte[] recData = new byte[36];
    
   static boolean  Run=true;
	
	BellmanExt3(){
		
	
		IPaddress[1]="localhost";
		IPaddress[2]="localhost";
		IPaddress[3]="localhost";
		IPaddress[4]="localhost";
		
			PortNo[1]=7735;
			PortNo[2]=7736;
			PortNo[3]=7737;
			PortNo[4]=7738;
			
			
		
		
		
	}
	
	
	
		
	
	
	void BF(Vertex v) throws Exception{
		clientSocket= new DatagramSocket();
		
		boolean Tflag=false;
		 
		 //To compute DV of my own
		v.flag=false;
			 
		String[] result2 = v.name.split("");
		
			int i=Integer.parseInt(result2[2]);
		 for (int j=1;j<5;j++)
			{
			 
			 
			 //v.Dtable[i][j]= 
			 if(i==j) { v.Dtable[i][j]=0;}
			 
			 else{
				 for (Edge e : v.adjacencies)
					{
					 
					 String  neigh1 = e.TargetName;
					 String[] result6 = neigh1.split("");
					 
					 int z = Integer.parseInt(result6[2]);
					 
					 
					 
					 if(i!=z){
						 
						 if((z!=j)){
						 
						 double newValue=Ctable[z] + v.Dtable[z][j];
						System.out.println(i+"  " + z+"  "+ j);
						System.out.println("new value =" + newValue);
						System.out.println("Dtable"+v.Dtable[i][j]);
						 if(newValue<v.Dtable[i][j]){
							 System.out.println("**");
						 v.Dtable[i][j]=newValue; Tflag= true;
						 System.out.println("I got updated !!");
						 
						 }
					  
						 }
						 
					 }
					 }
					 
				
				 
				 
			 }
			 }
			 
			
		 
		 
	
		 // copied her D vector into neighbours  table
		 
		 if(Tflag){
		 
			 //update(v);
	
		 }// if
		 
		 
		 update(v);
		 clientSocket.close();
		 
		
	}
	
	void update(Vertex v) throws Exception{
		
		
		for (Edge e : v.adjacencies)
        {
			 String neigh = e.TargetName;
			 
			 String[] result6 = neigh.split("");
			 String[] result5 = v.name.split("");
			 
			 
			 
			 int R = Integer.parseInt(result5[2]);// row to be sent
			 int S = Integer.parseInt(result6[2]);// neighbour to whom its to be sent
			String Data1="v3";
			 for(int z=1;z<5;z++){
				 Data1= Data1+ "#"+(v.Dtable[R][z]);
				 
			 }
			 
			 DVclient(Data1,S);
			 // neigh.Dtable[R] = v.Dtable[R];
			// neigh.flag= true;
			 System.out.println("They  got updated !!");
			 
        }
		
		
	}
	
	
	
	void DVclient(String data,int T) throws Exception{
		
		System.out.println("sending data " + data);
		
		//double j=0.0;
		DatagramSocket clientSocket = new DatagramSocket();
		ByteArrayOutputStream baos;
    	DataOutputStream daos;
    	baos = new ByteArrayOutputStream();
        daos = new DataOutputStream(baos);
     //   daos.writeBytes("v3");
        daos.writeBytes(data) ;  
        byte[] sendData1= baos.toByteArray();
        InetAddress IpAddress = InetAddress.getByName(IPaddress[T]);
        DatagramPacket sendPacket = new DatagramPacket(sendData1, sendData1.length,IpAddress,PortNo[T]);
        clientSocket.send(sendPacket);
        
        
        
		
		
	}
	
	void DvServer() throws Exception{
		
		 serverSocket = new DatagramSocket(7735);
		 DatagramPacket  recPacket = new DatagramPacket(recData, recData.length);
        serverSocket.receive(recPacket);
        System.out.println("\n Packet length: " + recPacket.getLength());
        recData1= recPacket.getData();
           
        bais = new ByteArrayInputStream(recData1);
	     dais = new DataInputStream(bais);
	     
	     String s1 = dais.readUTF();
	     
	     String[] result = s1.split("$");
	
	     String Nname =result[0];
	     
	     
	     
	    	 
	     
		
		
		
	}
	
	
	
	
	
	
	
	
public static void main(String[] args) throws Exception
{
	
	
	BellmanExt3 b = new BellmanExt3();
	

	
	Vertex s3 = new Vertex("v3",5);
	
	s3.adjacencies=new Edge[]{ 
            new Edge("v1", 3),
            new Edge("v2", 1),new Edge("v4", 1) };
	
	s3.Dtable[3][1]=3.0;
	s3.Dtable[3][2]=1.0;
	s3.Dtable[3][3]=0.0;
	s3.Dtable[3][4]=1.0;
	
	
	//b.BF(s);
	
	
	//b.DVclient();
	
	new BellRecv(7737,s3);
	
	//Boolean Run=true;
	
	MyTimerTask Task;
	Timer myTimer = new Timer(); 
	
	Task = new MyTimerTask();
	myTimer.schedule(Task,72000);
	
	System.out.println("running client 3");
	int count = 0;
	while(b.Run){
		
		//b.Run= false;
		
		if(s3.flag){
			
			b.BF(s3);
			System.out.println("Inside while");

			//break;
			count++;
			
		}
		
		b.Run=true;
		
		if(count == 100) b.Run = false;
	}
	
	
		
	
	
	
	// printing routing table
	
	
	for (int i=1;i<5;i++){
		 
		 for(int j=1;j<5;j++){
			 
			 System.out.print(s3.Dtable[i][j]);
			 
			 }
		 System.out.println("");
	 }
	
	for(int j=1;j<5;j++){
		
		System.out.println("\n ");
		
		 
		 System.out.println("Cost from node 3 to node "+j+"  is   "+ +s3.Dtable[3][j]);
		 
		 }
	
	
	
	
	  
	     

} //main

}// BellmanFord class





	




