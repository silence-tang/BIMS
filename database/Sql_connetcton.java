package database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//import org.omg.CORBA.PRIVATE_MEMBER;

//import com.sun.org.apache.bcel.internal.generic.Select;

//import jdk.internal.dynalink.beans.StaticClass;

//import javax.swing.text.html.AccessibleHTML.TableElementInfo.TableAccessibleContext;



public class Sql_connetcton {
	public static String url,ip;
	private static Connection con=null;
	private static String sql,sql1,sql2,sql3;
	private static Statement stmt;
	static boolean can(String ip){ //�жϵ�ַ�ܷ�pingͨ
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
	
	// ��ʼ��Java��MySQL������
	public static void init(){
		try {
			if(can("127.0.0.1")){
				ip="127.0.0.1";
			}
			else{
				ip="localhost";
			}
			url="jdbc:mysql://"+ip+":3306"+"/������ȫ����ϵͳ?useSSL=false";
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("δ�ҵ�����");
		} 
	}
	
	// ����Java��MySQL������
	public static int login_s(String user,String password){
		try {
			init();
			con=DriverManager.getConnection(url, user, password);
			stmt=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��½ʧ��");
			return 1;
		}
		return 0;
	}
	
	// �жϵ�¼��Ϣ�Ƿ���ȷ
	public static Vector<String> getLogin(String key1, String key2, String key3){
		if(key1==null||key1.equals(""))
			return null;
		sql="select *"
				+ " from login"
				+ " where login.gonghao = \""+key1+"\""
				+ " and login.passwd = \""+key2+"\""
				+ " and login.zhiwei =\""+key3+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			int clen=result.getMetaData().getColumnCount();
			Vector<String> ans=new Vector<String>();
			while(result.next()){
				for(int i=1;i<=clen;i++)
					ans.add(result.getString(i));
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// �ڲ���ʱ�ж��Ƿ���ڹ�����ͬ��˾���������ڣ���ѡ������޸�ģʽ
	// �����޸�ģʽ������δʵ��
	public static String[][] find_sj(int index,String s){
		String nature="";
		switch (index) {
		case 1:
			nature="sjnum";
			sql="select * from sj where "+nature+"="+"\""+s+"\""+";";
			break;
		case 2:
			nature="sjname";
			sql="select * from sj where "+nature+"="+"\""+s+"\""+";";
			break;
		case 3:
			nature="sjsex";
			sql="select * from sj where "+nature+"="+"\""+s+"\""+";";
			break;
		case 4:
			nature="xlname";
			sql="select * from sj where "+nature+"="+"\""+s+"\""+";";
			break;
		default:
			break;
		}
		
		ResultSet result;
		String []now;
		ArrayList<String[]> a=new ArrayList<>();
		try{
			result=stmt.executeQuery(sql);
			int ind=0,i;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				now=new String[5];
				for(i=0;i<clen;i++){
					now[i]=result.getString(i+1);
					if(now[i]==null)now[i]="NULL";
				}
				now[4]=now[4].split(" ")[0];//ȥ�����ڵ�ʱ��
				a.add(now);
			}
			String [][]ans=new String[a.size()][5];
			for(ind=0;ind<a.size();ind++)
				ans[ind]=a.get(ind);
			return ans;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	// �ڲ���ʱ�ж��Ƿ���ڳ��ƺ���ͬ�ĳ����������ڣ���ѡ������޸�ģʽ
	// �����޸�ģʽ������δʵ��
	public static String[][] find_cl(int index,String s){
		String nature="";
		switch (index) {
		case 1:
			nature="cpnum";
			sql="select * from cl where "+nature+"="+"\""+s+"\""+";";
			break;
		case 2:
			nature="zwnum";
			sql="select * from cl where "+nature+"="+"\""+s+"\""+";";
			break;
		case 3:
			nature="xlname";
			sql="select * from cl where "+nature+"="+"\""+s+"\""+";";
			break;
		default:
			break;
		}
		
		ResultSet result;
		String []now;
		ArrayList<String[]> a=new ArrayList<>();
		try{
			result=stmt.executeQuery(sql);
			int ind=0,i;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				now=new String[5];
				for(i=0;i<clen;i++){
					now[i]=result.getString(i+1);
					if(now[i]==null)now[i]="NULL";
				}
				now[4]=now[4].split(" ")[0];//ȥ�����ڵ�ʱ��
				a.add(now);
			}	
			String [][]ans=new String[a.size()][5];
			for(ind=0;ind<a.size();ind++)
				ans[ind]=a.get(ind);
			return ans;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	// ��������ĳ��ӺŲ�ѯ������Ϣ����ֹ�û����벻�淶
	public static Vector<String> getcd(String num){
		if(num==null||num.equals(""))
			return null;
		sql="select * from cd"
				+ " where cd.cdnum =\""+num+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			int clen=result.getMetaData().getColumnCount();
			Vector<String> ans=new Vector<String>();
			while(result.next()){
				for(int i=1;i<=clen;i++)
					ans.add(result.getString(i));
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ��������Ĺ��Ų�ѯ˾����Ϣ����ֹ���벻�淶
	public static Vector<String> getsj(String num){
		if(num==null||num.equals(""))
			return null;
		sql="select * from sj"
				+ " where sj.sjnum =\""+num+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			int clen=result.getMetaData().getColumnCount();
			Vector<String> ans=new Vector<String>();
			while(result.next()){
				for(int i=1;i<=clen;i++)
					ans.add(result.getString(i));
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// �����������·����ѯ��·��Ϣ����ֹ���벻�淶
	public static Vector<String> getxl(String name){
		if(name==null||name.equals(""))
			return null;
		sql="select * from xl where xl.xlname = \""+name+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			int clen=result.getMetaData().getColumnCount();
			Vector<String> ans=new Vector<String>();
			while(result.next()){
				for(int i=1;i<=clen;i++)
					ans.add(result.getString(i));
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// �ڲ���˾����Ϣʱ�ڱ������ʾ���е�˾����Ϣ
	public static Vector<Vector> getAllSj(int index, String key){
		switch (index) {
		// ����Ա
		case 1:
			sql="select sj.sjnum, sj.sjname, sj.sjsex, sj.xlname"
					+ " from sj, xl"
					+ " where sj.xlname=xl.xlname;";
			break;
		// ���ӳ�ֻ�ܲ鿴�����ӵ�����˾��
		case 2:
			sql="select sj.sjnum, sj.sjname, sj.sjsex, sj.xlname"
					+ " from sj, xl"
					+ " where sj.xlname=xl.xlname and xl.cdnum = \""+key+"\""+";";
			break;
		// ·�ӳ�ֻ�ܲ鿴����·������˾��
		case 3:
			sql="select *"
					+ " from sj where sj.xlname = \""+key+"\""+";";
			break;
		default:
			break;
		}
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(false);
				for(int i=1;i<=4;i++){
					s.add(result.getString(i));
					if(s.lastElement()==null)
						s.set(i, "NULL");
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// �ڲ��복����Ϣʱ�ڱ������ʾ���еĳ�����Ϣ
	public static Vector<Vector> getAllCl(int index, String key){
		switch (index) {
		// ����Ա
		case 1:
			sql="select * from cl order by xlname;";
			break;
		// ���ӳ�ֻ�ܲ鿴�����ӵ����г���
		case 2:
			sql="select cl.cpnum, cl.zwnum, cl.xlname"
					+ " from cl, xl"
					+ " where cl.xlname = xl.xlname and xl.cdnum = \""+key+"\""+";";
			break;
		// ·�ӳ�ֻ�ܲ鿴����·�����г���
		case 3:
			sql="select *"
					+ " from cl where cl.xlname = \""+key+"\""+";";
			break;
		default:
			break;
		}
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(false);
				for(int i=1;i<=3;i++){
					s.add(result.getString(i));
					if(s.lastElement()==null)
						s.set(i, "NULL");
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// �ڲ���Υ����Ϣʱ�ڱ������ʾ���е�Υ����Ϣ
	public static Vector<Vector> getAllWz(int index, String key){
		switch (index) {
		// ����Ա
		case 1:
			sql="select * from wz;";
			break;
		// ���ӳ�ֻ�ܲ鿴�����ӵ�Υ����Ϣ
		case 2:
			sql="select *"
					+ " from wz"
					+ " where wz.cdnum = \""+key+"\""+";";
			break;
		// ·�ӳ�ֻ�ܲ鿴����·��Υ����Ϣ
		case 3:
			sql="select *"
					+ " from wz where wz.xlname = \""+key+"\""+";";
			break;
		default:
			break;
		}
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(false);
				for(int i=1;i<=7;i++){
					s.add(result.getString(i));
					if(s.lastElement()==null)
						s.set(i, "NULL");
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ͨ�����Ų�����������ʾ��ҳ������Ϊ��ʾ��Ϣ
	public static Vector<String> getname(int position, String key){
		if(key==null||key.equals(""))
			return null;
		switch (position) {
		case 1:
			sql="select sj.sjname"
					+ " from sj"
					+ " where sj.sjnum =\""+key+"\""+";";
			break;
		case 2:
			sql="select ldz.ldzname"
					+ " from ldz"
					+ " where ldz.ldznum =\""+key+"\""+";";
			break;
		case 3:
			sql="select cdz.cdzname"
					+ " from cdz"
					+ " where cdz.cdznum =\""+key+"\""+";";
			break;
		case 4:
			sql="select ldz.xlname"
					+ " from ldz"
					+ " where ldz.ldznum =\""+key+"\""+";";
			break;
		case 5:
			sql="select cdz.cdnum"
					+ " from cdz"
					+ " where cdz.cdznum =\""+key+"\""+";";
			break;
		default:
			break;
		}
		try {
			ResultSet result=stmt.executeQuery(sql);
			int clen=result.getMetaData().getColumnCount();
			Vector<String> ans=new Vector<String>();
			while(result.next()){
				for(int i=1;i<=clen;i++)
					ans.add(result.getString(i));
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ͨ�����Ų�ѯ����
	public static Vector<Vector> getpswd(){
		sql="select * from login"
				+ " where login.gonghao =\""+Login_infos.gh+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(false);
				for(int i=1;i<=3;i++){
					s.add(result.getString(i));
					if(s.lastElement()==null)
						s.set(i, "NULL");
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ��ѯΥ������ֶε�����������ϣ���ֹ�����ʽ�쳣
	public static Vector<String> getallconds(String key1, String key2, String key3, String key4, String key5){
		if(key1==null||key1.equals(""))
			return null;
		sql="select sj.sjnum, cl.cpnum, cd.cdnum, sj.xlname, zd.zdname"
				+ " from sj,cl,cd,xl,zd"
				+ " where sj.xlname=xl.xlname and cl.xlname=xl.xlname and zd.xlname=xl.xlname and xl.cdnum=cd.cdnum"
				+ " and sj.sjnum = \"" + key1 + "\""
				+ " and cl.cpnum = \"" + key2 + "\""
				+ " and cd.cdnum = \"" + key3 + "\""
				+ " and xl.xlname = \"" + key4 + "\""
				+ " and zd.zdname = \"" + key5 + "\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			int clen=result.getMetaData().getColumnCount();
			Vector<String> ans=new Vector<String>();
			while(result.next()){
				for(int i=1;i<=clen;i++)
					ans.add(result.getString(i));
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ͨ�����Ų�ѯ˾��������Ϣ
	public static Vector<Vector> sjFindbynum(String key) {
		if(key.equals(""))
			return null;
		sql="select *"
				+ " from sj"
				+ " where sj.sjnum =\""+key+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int ind=1;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(ind++);
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ��ѯĳһ��·������˾����Ϣ
	public static Vector<Vector> sjFindbyxlname(String key) {
		if(key.equals(""))
			return null;
		sql="select *"
				+ " from sj"
				+ " where sj.xlname =\""+key+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int ind=1;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(ind++);
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ��ѯĳһ����������˾����Ϣ
	public static Vector<Vector> sjFindbycdnum(String key) {
		if(key.equals(""))
			return null;
		sql="select sj.sjnum, sj.sjname, sj.sjsex, sj.xlname, xl.cdnum"
				+ " from sj,xl where sj.xlname = xl.xlname and"
				+ " xl.cdnum =\""+key+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int ind=1;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(ind++);
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Vector<Vector> sjwzFindown(String key) {
		if(key.equals(""))
			return null;
		sql="select *"
				+ " from wz"
				+ " where wz.sjnum =\""+key+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int ind=1;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(ind++);
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ��ѯĳһ������ĳһ��·���Ƿ����
	public static Vector<Vector> xlFindbycd(String key1, String key2) {
		if(key1.equals(""))
			return null;
		sql="select *"
				+ " from xl"
				+ " where xl.cdnum = \""+key1+"\""
				+ " and xl.xlname = \""+key2+"\""+";";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int ind=1;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(ind++);
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ��ѯ����/��·/����Υ����Ϣ/����Υ��ͳ����Ϣ
	public static Vector<Vector> wzFind(int index, String key1, String key2, String key3) {
		if(key1.equals(""))
			return null;
		switch (index) {
		// case 1 ��ѯ����Υ����Ϣ
		case 1:
			sql="select sj.sjnum, sj.sjname, wz.cpnum, wz.zdname, wz.wztime, wz.wzinfo"
					+ " from wz, sj"
					+ " where wz.sjnum = sj.sjnum and wz.wztime between \""+key2+"\""
					+ " and \""+key3+"\""
					+ " and wz.sjnum =\""+key1+"\""+";";
			break;
		// case 2 ��ѯ��·Υ����Ϣ
		case 2:
			sql="select sj.sjnum, sj.sjname, wz.cpnum, wz.zdname, wz.wztime, wz.wzinfo"
					+ " from wz, sj "
					+ " where wz.sjnum = sj.sjnum and wz.wztime between \""+key2+"\""
					+ " and \""+key3+"\""
					+ " and wz.xlname = \""+key1+"\""+";";
			break;
		// case 3 ��ѯ����Υ����Ϣ
		case 3:
			sql="select sjnum, sjname, cpnum, xlname, zdname, wztime, wzinfo"
					+ " from view_cdwz"
					+ " where wztime between \""+key2+"\""
					+ " and \""+key3+"\""
					+ " and cdnum = \""+key1+"\""+";";
			break;
		// case 4 ��ѯ����Υ��ͳ����Ϣ������������MySQL�洢����
		case 4:
			sql1="set @cdh = \""+key1+"\""+";";
			sql2="set @sd = \""+key2+"\""+";";
			sql3="set @ed = \""+key3+"\""+";";
			sql="CALL find_sjwz_count(@cdh,@sd,@ed);";
			// ִ��sql����ȡ��ѯ���
			try {
				stmt.executeQuery(sql1);
				stmt.executeQuery(sql2);
				stmt.executeQuery(sql3);
				ResultSet result=stmt.executeQuery(sql);
				Vector<Vector> ans=new Vector<Vector>();
				int ind=1;
				int clen=result.getMetaData().getColumnCount();
				// ��������б����л�ȡ���ݣ���󸳸�ans
				while(result.next()){
					Vector s=new Vector<String>();
					s.add(ind++);
					for(int i=1;i<=clen;i++){
						s.add(result.getString(i));
					}
					ans.add(s);
				}
				return ans;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		default:
			break;
		}
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int ind=1;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(ind++);
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// �ϳ�sql���
	private static String add_sql(String sql,Vector<String> data){
		for(int i=0;i<data.size();i++){
			String s=data.get(i);
			if(s.equals("NULL")==false && s.equals("")==false){
				sql+="\""+data.get(i)+"\"";
			}else{
				sql+="NULL";
			}
			if(i!=data.size()-1)
				sql+=",";
		}
		sql+=");";
		return sql;
	}
	
	// ��������
	public static boolean update(Vector<String> data,String name){
		System.out.println(data.toString());
		switch (name) {
		case "sj":
			sql="update sj set sjname=\""+data.get(1)+"\""+","+"sjsex=\""+data.get(2)+"\","+"xlname=\""+data.get(3)+"\""+" where sjnum=\""+data.get(0)+"\";";
			break;
		case "cl":
			sql="update cl set zwnum=\""+data.get(1)+"\""+","+"xlname=\""+data.get(2)+"\""+" where cpnum=\""+data.get(0)+"\";";
			break;
		case "pswd":
			sql="update login set passwd=\""+data.get(0)+"\""+"where gonghao=\"" + Login_infos.gh +"\";";
			break;
		default:
			break;
		}
		
		System.out.println(sql);
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	// ��������
	public static boolean insert(Vector<String> data,String name){
		switch (name) {
		// case 1 ����˾����
		case "sj":
			sql="insert into " +name+ "(sjnum,sjname,sjsex,xlname) values(";
			break;
		// case 2 ���복����
		case "cl":
			sql="insert into " +name+ "(cpnum,zwnum,xlname) values(";
			break;
		// case 3 ����Υ�±�
		case "wz":
			sql="insert into " +name+ "(sjnum,cpnum,cdnum,xlname,zdname,wztime,wzinfo) values(";
			break;
		default:
			break;
		}
		// ����add_sql������ʵ��sql����ƴ��
		sql=add_sql(sql, data);
		System.out.println(sql);
			try {
				// ִ��sql���
				stmt.execute(sql);
				return true;
			} catch (SQLException e) {
				// ����׽�������쳣�򷵻�
				return false;
			}
	}
	
	// ɾ��˾����¼
	public static boolean delSj(String code){
		System.out.println(code);
		sql="delete from sj where sjnum=\""+code+"\""+";";
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	// ɾ��������¼
	public static boolean delCl(String code){
		System.out.println(code);
		sql="delete from cl where cpnum=\""+code+"\""+";";
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	// ɾ��Υ�¼�¼
		public static boolean delWz(String code1, String code2, String code3, String code4, String code5){
			System.out.println(code1);
			sql="delete from wz where sjnum=\""+code1+"\""
			+ " and cpnum = \"" + code2 + "\""
			+ " and zdname = \"" + code3 + "\""
			+ " and wztime = \"" + code4 + "\""
			+ " and wzinfo = \"" + code5 + "\"";
			try {
				stmt.execute(sql);
				return true;
			} catch (SQLException e) {
				return false;
			}
		}
	
	public static void close(){
		try {
			if(stmt!=null)stmt.close();
			if(con!=null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean Do(String sql1){
		try {		
			stmt.execute(sql1);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		init();
		if(login_s("root","tzchen_20010102")==0)
			System.out.print("��½�ɹ���");
		int a=0;
	}
	
}