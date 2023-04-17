package edu.java.project.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Content implements Serializable {
	public interface Entity {
		String TBL_NAME = "CONTENTS";
		String COL_NO = "NO";
		String COL_ID = "ID";
		String COL_CTGR = "CTGR";
		String COL_TITLE = "TITLE";
		String COL_CONTENT = "CONTENT";
		String COL_CDATE = "CREATETIME";
		String COL_MDATE = "MODIFIEDTIME";
	}
	
	// field
	private int no;
	private String id;
	private String ctgr;
	private String title;
	private String content;
	private LocalDateTime createTime;
	private LocalDateTime modifiedTime;
	
	public Content () {}

	public Content(int no, String id, String ctgr, String title, String content, LocalDateTime createTime, LocalDateTime modifiedTime) {
		super();
		this.no = no;
		this.id = id;
		this.ctgr = ctgr;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCtgr() {
		return ctgr;
	}

	public void setCtgr(String ctgr) {
		this.ctgr = ctgr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	@Override
	public String toString() {
		return String.format("Content | no= %d, id= %s, category= %s, title= %s, content= %s, createtime= %s, modifiedtime= %s", 
				no, id, ctgr, title, content, createTime, modifiedTime);
	}
}
