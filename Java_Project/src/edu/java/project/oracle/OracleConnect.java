package edu.java.project.oracle;

public interface OracleConnect {
	// 인터페이스의 필드는 public static final만 가능하며, 생략 가능.
	
	String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 데이터베이스 서버 접속 주소(IP, port, SID)
	                                                        // localhost = 현재 개인 PC
	String USER = "scott"; // 오라클 데이터베이스 서버 계정
	String PASSWORD = "tiger"; // 오라클 데이터베이스 계정 비밀번호
}
