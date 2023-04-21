package edu.java.project.controller;

import edu.java.project.model.Join;

public interface JoinDao {
	/**
	 * 아이디 중복검사
	 * @return
	 */
	public boolean checkId(String id);
	
	/**
	 * 이메일 중복검사
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email);
	
	/**
	 * 회원가입
	 * @param user
	 */
	public int userRegister(Join user);
	
	/**
	 * 로그인
	 * @param id
	 * @param password
	 * @return 성공 true 실패 false
	 */
	public boolean login(String id, String password);
	
	/**
	 * 회원탈퇴
	 */
	
	/**
	 * 회원 정보
	 * @param id 검색할 primary key
	 * @return no가 존재하면 Join 타입 객체 리턴 no 없으면 return 리턴
	 */
	Join userInfo (String userId);
	
}

