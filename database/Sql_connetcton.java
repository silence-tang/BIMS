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
	static boolean can(String ip){ //判断地址能否ping通
		Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
		  Process process = null; // 声明处理类对象
		  String line = null; // 返回行信息
		  InputStream is = null; // 输入流
		  InputStreamReader isr = null; // 字节流
		  BufferedReader br = null;
		  boolean res = false;// 结果
		  try {
		   process = runtime.exec("ping " + ip); // PING
		   is = process.getInputStream(); // 实例化输入流
		   isr = new InputStreamReader(is);// 把输入流转换成字节流
		   br = new BufferedReader(isr);// 从字节中读取文本
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
	
	// 初始化Java和MySQL的连接
	public static void init(){
		try {
			if(can("127.0.0.1")){
				ip="127.0.0.1";
			}
			else{
				ip="localhost";
			}
			url="jdbc:mysql://"+ip+":3306"+"/公交安全管理系统?useSSL=false";
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("未找到驱动");
		} 
	}
	
	// 建立Java和MySQL的连接
	public static int login_s(String user,String password){
		try {
			init();
			con=DriverManager.getConnection(url, user, password);
			stmt=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("登陆失败");
			return 1;
		}
		return 0;
	}
	
	// 判断登录信息是否正确
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
	
	// 在插入时判断是否存在工号相同的司机，若存在，可选择进入修改模式
	// 进入修改模式功能尚未实现
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
				now[4]=now[4].split(" ")[0];//去除日期的时间
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
	
	// 在插入时判断是否存在车牌号相同的车辆，若存在，可选择进入修改模式
	// 进入修改模式功能尚未实现
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
				now[4]=now[4].split(" ")[0];//去除日期的时间
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
	
	// 根据输入的车队号查询车队信息，防止用户输入不规范
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
	
	// 根据输入的工号查询司机信息，防止输入不规范
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
	
	// 根据输入的线路名查询线路信息，防止输入不规范
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
	
	// 在插入司机信息时在表格中显示所有的司机信息
	public static Vector<Vector> getAllSj(int index, String key){
		switch (index) {
		// 管理员
		case 1:
			sql="select sj.sjnum, sj.sjname, sj.sjsex, sj.xlname"
					+ " from sj, xl"
					+ " where sj.xlname=xl.xlname;";
			break;
		// 车队长只能查看本车队的所有司机
		case 2:
			sql="select sj.sjnum, sj.sjname, sj.sjsex, sj.xlname"
					+ " from sj, xl"
					+ " where sj.xlname=xl.xlname and xl.cdnum = \""+key+"\""+";";
			break;
		// 路队长只能查看本线路的所有司机
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
	
	// 在插入车辆信息时在表格中显示所有的车辆信息
	public static Vector<Vector> getAllCl(int index, String key){
		switch (index) {
		// 管理员
		case 1:
			sql="select * from cl order by xlname;";
			break;
		// 车队长只能查看本车队的所有车辆
		case 2:
			sql="select cl.cpnum, cl.zwnum, cl.xlname"
					+ " from cl, xl"
					+ " where cl.xlname = xl.xlname and xl.cdnum = \""+key+"\""+";";
			break;
		// 路队长只能查看本线路的所有车辆
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
	
	// 在插入违章信息时在表格中显示所有的违章信息
	public static Vector<Vector> getAllWz(int index, String key){
		switch (index) {
		// 管理员
		case 1:
			sql="select * from wz;";
			break;
		// 车队长只能查看本车队的违章信息
		case 2:
			sql="select *"
					+ " from wz"
					+ " where wz.cdnum = \""+key+"\""+";";
			break;
		// 路队长只能查看本线路的违章信息
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

	// 通过工号查找姓名，显示在页面上作为提示信息
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
	
	// 通过工号查询密码
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
	
	// 查询违章五个字段的所有排列组合，防止输入格式异常
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
	
	// 通过工号查询司机个人信息
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
	
	// 查询某一线路下所有司机信息
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
	
	// 查询某一车队下所有司机信息
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
	
	// 查询某一车队下某一线路名是否存在
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
	
	// 查询个人/线路/车队违章信息/车队违章统计信息
	public static Vector<Vector> wzFind(int index, String key1, String key2, String key3) {
		if(key1.equals(""))
			return null;
		switch (index) {
		// case 1 查询个人违章信息
		case 1:
			sql="select sj.sjnum, sj.sjname, wz.cpnum, wz.zdname, wz.wztime, wz.wzinfo"
					+ " from wz, sj"
					+ " where wz.sjnum = sj.sjnum and wz.wztime between \""+key2+"\""
					+ " and \""+key3+"\""
					+ " and wz.sjnum =\""+key1+"\""+";";
			break;
		// case 2 查询线路违章信息
		case 2:
			sql="select sj.sjnum, sj.sjname, wz.cpnum, wz.zdname, wz.wztime, wz.wzinfo"
					+ " from wz, sj "
					+ " where wz.sjnum = sj.sjnum and wz.wztime between \""+key2+"\""
					+ " and \""+key3+"\""
					+ " and wz.xlname = \""+key1+"\""+";";
			break;
		// case 3 查询车队违章信息
		case 3:
			sql="select sjnum, sjname, cpnum, xlname, zdname, wztime, wzinfo"
					+ " from view_cdwz"
					+ " where wztime between \""+key2+"\""
					+ " and \""+key3+"\""
					+ " and cdnum = \""+key1+"\""+";";
			break;
		// case 4 查询车队违章统计信息————调用MySQL存储过程
		case 4:
			sql1="set @cdh = \""+key1+"\""+";";
			sql2="set @sd = \""+key2+"\""+";";
			sql3="set @ed = \""+key3+"\""+";";
			sql="CALL find_sjwz_count(@cdh,@sd,@ed);";
			// 执行sql语句获取查询结果
			try {
				stmt.executeQuery(sql1);
				stmt.executeQuery(sql2);
				stmt.executeQuery(sql3);
				ResultSet result=stmt.executeQuery(sql);
				Vector<Vector> ans=new Vector<Vector>();
				int ind=1;
				int clen=result.getMetaData().getColumnCount();
				// 遍历结果列表，逐行获取数据，最后赋给ans
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
	
	// 合成sql语句
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
	
	// 更新数据
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
	
	// 插入数据
	public static boolean insert(Vector<String> data,String name){
		switch (name) {
		// case 1 插入司机表
		case "sj":
			sql="insert into " +name+ "(sjnum,sjname,sjsex,xlname) values(";
			break;
		// case 2 插入车辆表
		case "cl":
			sql="insert into " +name+ "(cpnum,zwnum,xlname) values(";
			break;
		// case 3 插入违章表
		case "wz":
			sql="insert into " +name+ "(sjnum,cpnum,cdnum,xlname,zdname,wztime,wzinfo) values(";
			break;
		default:
			break;
		}
		// 调用add_sql函数，实现sql语句的拼接
		sql=add_sql(sql, data);
		System.out.println(sql);
			try {
				// 执行sql语句
				stmt.execute(sql);
				return true;
			} catch (SQLException e) {
				// 若捕捉到插入异常则返回
				return false;
			}
	}
	
	// 删除司机记录
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
	
	// 删除车辆记录
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
	
	// 删除违章记录
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
			System.out.print("登陆成功！");
		int a=0;
	}
	
}