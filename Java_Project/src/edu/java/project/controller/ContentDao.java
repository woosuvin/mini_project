package edu.java.project.controller;

import java.util.List;

import edu.java.project.model.Content;

public interface ContentDao {
	
//	/**
//	 * 새 코멘트 저장
//	 * @param c 리스트에 저장할 코멘트 객체
//	 * @return 리스트에 저장 성공하면 1 실패하면 0
//	 */
//	int create(Content c);
//	
//	/**
//	 * 코멘트 전체 보기 기능. 저장된 코멘트 개수 크기의 코멘트 리스트 리턴
//	 * @return 코멘트 리스트
//	 */
//	List<Content> read();
//	
//	/**
//	 * 인덱스? 제목? 으로 검색 argument로 전달받은 인덱스 위치에 있는 코멘트 리런
//	 * @param 
//	 */
//	Content read(int index);
//	
//	/**
//	 * 
//	 */
	
	/**
	 * 컨텐츠 정보를 DB contents 테이블에 insert
	 * @param content 저장할 카테고리(ctgt), 제목(title), 컨텐츠(content) 정보를 가지고 있는 객체
	 * @return DB 테이블에 삽입된 행의 개수.
	 */
	int create(Content content);
	
	/**
	 * 주어진 검색어가 제목에 포함된 컨텐츠들을 DB contents 테이블에서 검색해서 리스트를 리턴. 검색어는 대/소문자 없음.
	 * @param keyword 검색어
	 * @return 검색 결과 리스트
	 */
	List<Content> read(String keyword);
	
	/**
	 * 컨텐츠 전체 검색
	 * @return Content 타입을 원소로 갖는 List 리턴.
	 */
	List<Content> read();
	
	/**
	 * DB contents 테이블에서 primary key로 연락처 정보를 검색.
	 * @param no 검색할 primary key.
	 * @return no가 존재하면 Content 타입 객체 리턴. no 없으면 null 리턴.
	 */
	Content read(int no);
	
	/**
	 * primary key(no)에 해당하는 연락처 정보를 수정.
	 * @param content 수정할 컨텐츠의 PK(no), 수정할 내용을 저장한 객체
	 * @return DB 테이블에서 업데이트 된 행의 개수
	 */
	int update(Content content);
	
	/**
	 * primary key(no)에 해당하는 연락처 정보를 DB 테이블에서 삭제
	 * @param no 삭제할 연락처의 PK
	 * @return 삭제된 행의 개수
	 */
	int delete(Integer no);
}
