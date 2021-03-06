package day0922;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex8SelectPerson {
	public Ex8SelectPerson() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("오라클 드라이버 클래스 성공!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버가 없어요 :" + e.getMessage());
		}
	}
	
	public void method() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "angel";
		String password = "a1234";
		String sql1 = "select num 번호, name 이름, upper(blood)||'형' 혈액형,"
				+ " height 키, to_char(birthday, 'YY/MM/DD') 생년월일,"
				+ " java 자바, spring 스프링, (java+spring)/2 총점평균 from person2";
		String sql2 = "select round(avg(java),1) 자바평균,"
				+ " round(avg(spring),1) 스프링평균 from person2";
		System.out.println("번호\t이름\t혈액형\t키\t생년월일\t자바\t스프링\t총점평균");
		System.out.println("===========================================================================");
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next()) {
				String num = rs.getString(1);
				String name = rs.getString(2);
				String blood = rs.getString(3);
				String height = rs.getString(4);
				String birthday = rs.getString(5);
				String java = rs.getString(6);
				String spring = rs.getString(7);
				String sumAvg = rs.getString(8);
				
				System.out.println(num+"\t"+name+"\t"+blood+"\t"+height+"\t"+
				birthday+"\t"+java+"\t"+spring+"\t"+sumAvg);
			}
			rs = stmt.executeQuery(sql2);
			while(rs.next()) {
				String javaAvg = rs.getString(1);
				String springAvg = rs.getString(2);
				
				System.out.println("자바점수의 전체 평균 : " + javaAvg);
				System.out.println("스프링점수의 전체 평균 : " + springAvg);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Ex8SelectPerson ex = new Ex8SelectPerson();
		ex.method();
	}

}
