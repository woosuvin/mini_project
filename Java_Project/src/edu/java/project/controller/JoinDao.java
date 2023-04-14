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
	public void join(Join user);
	
	/**
	 * 로그인
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean login(String id, String password);
	
	/**
	 * 회원탈퇴
	 */
	
}

