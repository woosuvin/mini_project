package edu.java.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.java.project.model.Content;
import oracle.jdbc.OracleDriver;

import static edu.java.project.model.Content.Entity.*;
import static edu.java.project.oracle.OracleConnect.*;

public class ContentDaoImpl implements ContentDao {
	
	//singleton
	private static ContentDaoImpl instance = null;
	private ContentDaoImpl () {}
	public static ContentDaoImpl getInstance() {
		if (instance == null) {
			instance = new ContentDaoImpl();
		}
		return instance;
	}
	
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

	
	private static final String SQL_INSERT = String.format("insert into %s (%s, %s, %s) values(?, ?, ?)", 
			TBL_NAME, COL_CTGR, COL_TITLE, COL_CONTENT);
	@Override
	public int create(Content content) {
		int result = 0; // inset 결과 저장하고 리턴할 변수.
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, content.getCtgr());
			stmt.setString(2, content.getTitle());
			stmt.setString(3, content.getContent());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	private static final String SQL_SELECT_BY_KEYWORD = String.format("select * from %s where lower(%s) like lower(?) order by %s",
			TBL_NAME, COL_TITLE, COL_NO);
	@Override
	public List<Content> read(String keyword) {
		ArrayList<Content> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_BY_KEYWORD);
			stmt = conn.prepareStatement(SQL_SELECT_BY_KEYWORD);
			stmt.setString(1, "%" + keyword + "%");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt(COL_NO);
				String id = rs.getString(COL_ID);
				String ctgr = rs.getString(COL_CTGR);
				String title = rs.getString(COL_TITLE);
				String contents = rs.getString(COL_CONTENT);
				LocalDateTime createTime = rs.getTimestamp(COL_CDATE).toLocalDateTime();
				LocalDateTime modifiedTime = rs.getTimestamp(COL_MDATE).toLocalDateTime();
				
				Content content = new Content(no, id, ctgr, title, contents, createTime, modifiedTime);
				list.add(content);
				System.out.println(content);
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
		return list;
	}

	private static final String SQL_SELECT_ALL = String.format("select * from %s order by %s", 
			TBL_NAME, COL_NO);
	@Override
	public List<Content> read() {
		ArrayList<Content> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt(COL_NO);
//				String id = rs.getString(COL_ID);
				String ctgr = rs.getString(COL_CTGR);
				String title = rs.getString(COL_TITLE);
				String contents = rs.getString(COL_CONTENT);
				LocalDateTime createTime = rs.getTimestamp(COL_CDATE).toLocalDateTime();
				LocalDateTime modifiedTime = rs.getTimestamp(COL_MDATE).toLocalDateTime();
				
				Content content = new Content(no, ctgr, title, contents, createTime, modifiedTime);
				list.add(content);
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
		return list;
	}

	
	private static final String SQL_SELECT_BY_NO = String.format("select * from %s where %s = ?", 
			TBL_NAME, COL_NO);
	@Override
	public Content read(int no) {
		Content content = null; // select 결과를 저장하고 리턴하기 위한 변수.
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_NO);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			if (rs.next()) { // 검색된 행(row) 데이터가 있다면
				int number = rs.getInt(COL_NO);
				String id = rs.getString(COL_ID);
				String ctgr = rs.getString(COL_CTGR);
				String title = rs.getString(COL_TITLE);
				String contents = rs.getString(COL_CONTENT);
				LocalDateTime createTime = rs.getTimestamp(COL_CDATE).toLocalDateTime();
				LocalDateTime modifiedTime = rs.getTimestamp(COL_MDATE).toLocalDateTime(); 
				
				content = new Content(no, id, ctgr, title, contents, createTime, modifiedTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	
	private static final String SQL_UPDATE = String.format("update %s set %s = ?, %s = ?, %s = ? where %s = ?", 
			TBL_NAME, COL_CTGR, COL_TITLE, COL_CONTENT, COL_NO);
	@Override
	public int update(Content content) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			
			stmt.setString(1, content.getCtgr());
			stmt.setString(2, content.getTitle());
			stmt.setString(3, content.getContent());
			stmt.setInt(4, content.getNo());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	private static final String SQL_DELETE = String.format("delete from %s where %s = ?", TBL_NAME, COL_NO);
	@Override
	public int delete(Integer no) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, no);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	

}
