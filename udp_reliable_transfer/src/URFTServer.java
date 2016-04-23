import java.net.*;
import java.util.*;
import java.io.*;

public class URFTServer {
	
	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
 
public static void main(String[] args) throws Exception {
 
	int receivedSEQ = 0;
	int ACK = 0;
	String dir = args[3];
	File file = null;
	int fileSize = 0;
	 
	final int SIZE = 512;
	byte[] buf = new byte[SIZE];
	 
	ArrayList<DatagramPacket> packets = new ArrayList<DatagramPacket>();
	TreeMap<Integer, byte[]> packetMap = new TreeMap<Integer, byte[]>();
	 
	DatagramSocket socket = null;
	 
	try {
		socket = new DatagramSocket(Integer.parseInt(args[1]));
	} catch (NumberFormatException e1) {
		e1.printStackTrace();
	} catch (SocketException e1) {
		e1.printStackTrace();
	}
	 
	DatagramPacket packet = new DatagramPacket(buf, buf.length);
	System.out.println("Waiting for client...");
	
	try {
		socket.receive(packet);
		System.out.println("Received handshake packet!");
	} catch (IOException e2) {
		e2.printStackTrace();
	}
	
	String parsedStr = "";  //me
	String response = new String(packet.getData());
	 
	response = response.substring(response.indexOf("\r\n") + 2); //parse file name
	String fName = response.substring(0, response.indexOf("\r\n") + 2);
	fName = dir + "/" + fName;
	file = new File(fName);
	response = response.substring(response.indexOf("\r\n") + 2);
	parsedStr = response.substring(0, response.indexOf("\r\n")); //parse file size
	fileSize = Integer.parseInt(parsedStr);
	 
	String hdr = ACK + "\r\n";
	 
	InetAddress addr = packet.getAddress();
	int port = packet.getPort();
	byte[] header = hdr.getBytes();
	packet = new DatagramPacket(header, header.length, addr, port);
	
	socket.setSoTimeout(1000);
	boolean timeOut = true;
	
	while(timeOut) {
		try {
			socket.send(packet);
			System.out.println("sending first response");  //ACK receipt of file name and size
			timeOut = false;
		} catch (IOException e2) {
			timeOut = true;
			e2.printStackTrace();
		}
	}
	 
	int packetCount = 1;
	 
	boolean receiving = true;
	int segNum = 0;
	 
	int totalPkts = 0;
	 
	if(fileSize % 512 == 0)
		totalPkts = (int) fileSize/512;
	else
		totalPkts = (int)Math.ceil(fileSize/512.0);
	//System.out.println("Total packets: " + totalPkts);
	 
	int numPkts = 0;
	 	 
	while(receiving) {
	 
		while(numPkts < totalPkts){
			buf = new byte[512];
			packet = new DatagramPacket(buf, buf.length);
			 
			try {
				socket.receive(packet);
				packets.add(packet);
				System.out.println("Packet " + numPkts + " received");
				numPkts++;
			} 
			catch(Exception e){
				System.out.println("Timed out receiving packet " + numPkts);
				
			}
		}
		 
		for(DatagramPacket dp : packets){
			response = new String(dp.getData());
			String seqNum = response.substring(0, response.indexOf("\r\n"));
			receivedSEQ = Integer.parseInt(seqNum);
		 
			if(receivedSEQ == -1) { //break out of while and write file
				System.out.println("Got sequence number -1");
				receiving = false;
			}
			else{
				response = response.substring(response.indexOf("\r\n") + 2);
				//byte[] data = response.getBytes();
				packetMap.put(receivedSEQ, response.getBytes());
			}
		}
		 
		packets.clear();
		 
		if(!receiving)
			break;
		
		boolean gap = false;
		 
		//System.out.println("Size of packetMap: " + packetMap.size());
		for(; segNum < packetMap.size(); segNum++) {
		 
			if(!packetMap.containsKey(segNum)){
				ACK = segNum;
				gap = true;
				System.out.println("Gap detected at segment " + segNum);
			}
			else if(gap) 
				packetMap.remove(segNum);
			else
				ACK = segNum + 1;
		}
		 
		hdr = ACK + "\r\n";
		 
		addr = packet.getAddress();
		port = packet.getPort();
		header = hdr.getBytes();
		packet = new DatagramPacket(header, header.length, addr, port);
		 
		try {
			socket.send(packet);
			System.out.println("Sending cumulative ACK: " + ACK);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		 
		packetCount++;
		System.out.println("sent packet #" + packetCount);
		 
		if(ACK == numPkts)
			numPkts = ACK - 1;
		else
			numPkts = ACK;
	}
	 
	File file2 = new File("server_dir/test2.txt");
	
	FileOutputStream fos = null;
	FileOutputStream fo = new FileOutputStream(file2, true);
	Collection<byte[]> d = packetMap.values();
	Object[] data = d.toArray();
	byte[] values = serialize(data);
 
	try {
		fos = new FileOutputStream(file, true);
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	 
	System.out.println(packetMap);
	 
	for(int i = 0; i < totalPkts; i++) {
		try {
			fos.write(packetMap.get(i));
			fo.write(values);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	fos.close();
	fo.close();
	socket.close();
	System.out.println("Server closing connection.");
	}
 

}