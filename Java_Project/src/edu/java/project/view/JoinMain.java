package edu.java.project.view;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.java.project.model.Join;
import oracle.jdbc.driver.OracleDriver;

import static edu.java.project.model.Join.Entity.*;
import static edu.java.project.oracle.OracleConnect.*;



public class JoinMain {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// Oracle JDBC driver(라이브러리) 등록
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("오라클 드라이버 등록 성공");
			
			// 4. DB 서버에 접속(Connection)
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("conn = " + conn);
			
			// Statement 객체 생성
			String sql = "select * from userjoin";
			stmt = conn.prepareStatement(sql);
			System.out.println("stmt = " + stmt);
			
			// Stament tlfgod
			rs = stmt.executeQuery();
			System.out.println("rs = " + rs);
			
			// 결과 처리
			while (rs.next()) {
				Integer no = rs.getInt(COL_NO);
				String name = rs.getString(COL_NAME);
				String id = rs.getString(COL_ID);
				String password = rs.getString(COL_PASSWORD);
				String email = rs.getString(COL_EMAIL);
				
				Join join = new Join(no, name, id, password, email);
				System.out.println(join);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
