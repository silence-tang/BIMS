package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class temp {
	static void isAddressAvailable(String ip){ 
		try{ 
		InetAddress address = InetAddress.getByName(ip);//ping this IP 

		if(address instanceof java.net.Inet4Address){ 
		System.out.println(ip + " is ipv4 address"); 
		}else 
		if(address instanceof java.net.Inet6Address){ 
		System.out.println(ip + " is ipv6 address"); 
		}else{ 
		System.out.println(ip + " is unrecongized"); 
		} 

		if(address.isReachable(5000)){ 
		System.out.println("SUCCESS - ping " + ip + " with no interface specified"); 
		}else{ 
		System.out.println("FAILURE - ping " + ip + " with no interface specified"); 
		} 

		System.out.println("\n-------Trying different interfaces--------\n"); 

		Enumeration<NetworkInterface> netInterfaces = 
		NetworkInterface.getNetworkInterfaces(); 
		while(netInterfaces.hasMoreElements()) { 
		NetworkInterface ni = netInterfaces.nextElement(); 
		System.out.println( 
		"Checking interface, DisplayName:" + ni.getDisplayName() + ", Name:" + ni.getName()); 
		if(address.isReachable(ni, 64, 5000)){ 
		System.out.println("SUCCESS - ping " + ip); 
		}else{ 
		System.out.println("FAILURE - ping " + ip); 
		} 

		Enumeration<InetAddress> ips = ni.getInetAddresses(); 
		while(ips.hasMoreElements()) { 
		System.out.println("IP: " + ips.nextElement().getHostAddress()); 
		} 
		System.out.println("-------------------------------------------"); 
		} 
		}catch(Exception e){ 
		System.out.println("error occurs."); 
		e.printStackTrace(); 
		} 
		} 
	static boolean can(String ip){
		Runtime runtime = Runtime.getRuntime(); // ��ȡ��ǰ��������н�����
		  Process process = null; // �������������
		  String line = null; // ��������Ϣ
		  InputStream is = null; // ������
		  InputStreamReader isr = null; // �ֽ���
		  BufferedReader br = null;
		  boolean res = false;// ���
		  try {
			  
		   process = runtime.exec("ping " + ip); // PING
		   is = process.getInputStream(); // ʵ����������
		   isr = new InputStreamReader(is);// ��������ת�����ֽ���
		   br = new BufferedReader(isr);// ���ֽ��ж�ȡ�ı�
		   while ((line = br.readLine()) != null) {
		    if (line.contains("TTL")) {
		     res = true;
		     break;
		    }
		   }
		   is.close();
		   isr.close();
		   br.close();
		   return res;
		  } catch (IOException e) {
		   System.out.println(e);
		   runtime.exit(1);
		   return false;
		  }
	}


	public static void main(String[] args) {
			isAddressAvailable("127.0.0.1");
		System.out.println(can("127.0.0.1"));
	}

}
