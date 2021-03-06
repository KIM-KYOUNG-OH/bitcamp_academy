package day0923;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracle.db.OracleConnect;

public class Ex1StuInsert {

	OracleConnect connect;
	public Ex1StuInsert() {
		connect = OracleConnect.getInstance();
	}
	
	// 데이터 입력해서 테이블 stu에 추가하는 메서드
	public void insertData() {
		Scanner sc = new Scanner(System.in);
		String name;
		int age;
		String sql;
		System.out.println("이름입력 : ");
		name=sc.nextLine();
		System.out.println("나이입력 : ");
		age=sc.nextInt();
		sql="insert into stu values (seq1.nextval, '" + name + "', " + age + ")";
		
		Connection conn = null;
		Statement stmt = null;
		
		// db연결
		conn = connect.getConnection();
		try {
			stmt = conn.createStatement();
			// 실행
			// execute : 반환타입 boolean
			// executeUpdate : 반환타입 int
			int n = stmt.executeUpdate(sql);
			System.out.println("n = " + n); // 성공한 레코드의 갯수
			System.out.println("데이터 추가 성공!!");
		} catch (SQLException e) {
			System.out.println("sql 오류 :" + e.getMessage());
		} finally {
			connect.dbClose(stmt, conn);
		}
	}
	
	public static void main(String[] args) {
		Ex1StuInsert ex = new Ex1StuInsert();
		ex.insertData();
	}

}
