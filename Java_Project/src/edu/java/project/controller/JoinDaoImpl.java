package edu.java.project.controller;

import edu.java.project.model.Join;
import oracle.jdbc.OracleDriver;

import static edu.java.project.model.Join.Entity.*;
import static edu.java.project.oracle.OracleConnect.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JoinDaoImpl implements JoinDao {
	
	private boolean idCheckResult = false;
	private boolean emailCheckResult = false;
	
	private Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		return conn;
	}

	private void closeResources(Connection conn, Statement stmt) throws SQLException {
		stmt.close();
		conn.close();
	}
	
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		rs.close();
		closeResources(conn, stmt);
	}
	
	private static final String SQL_CHECKID = String.format("select %s from %s", 
			COL_ID, TBL_NAME);
	@Override // id 중복 체크
	public boolean checkId(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_CHECKID);
			System.out.println(SQL_CHECKID);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				if (rs.getString(COL_ID).equals(id)) { // 중복되면
					idCheckResult = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return idCheckResult;
	}

	private static final String SQL_CHECKEMAIL = String.format("select %s from %s", 
			COL_EMAIL, TBL_NAME);
	@Override // 이메일 중복 체크
	public boolean checkEmail(String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_CHECKEMAIL);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				if (rs.getString(COL_EMAIL).equals(email)) { // 중복되면
					emailCheckResult = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return emailCheckResult;
	}
	
	private static final String SQL_USER_REGISTER = String.format("insert into %s (%s, %s, %s, %s) values (?, ?, ?, ?)", 
			TBL_NAME, COL_NAME, COL_ID, COL_PASSWORD, COL_EMAIL);
	@Override // 회원가입
	public int userRegister (Join user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_USER_REGISTER);
			System.out.println(SQL_USER_REGISTER);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getId());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			result = stmt.executeUpdate();
			
			System.out.println("회원가입 완료");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		return result;
	}

	private static final String SQL_LOGIN = String.format("select * from %s where %s = ? and %s = ?", 
			TBL_NAME, COL_ID, COL_PASSWORD);
	@Override
	public boolean login(String id, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_LOGIN);
			System.out.println(SQL_LOGIN);
			stmt.setString(1, id);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("로그인 완료");
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("login sql문 오류");
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		return false;
	}

}
