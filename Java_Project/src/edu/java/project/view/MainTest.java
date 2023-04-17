package edu.java.project.view;

import edu.java.project.controller.JoinDaoImpl;

public class MainTest {

	public static void main(String[] args) {
		JoinDaoImpl dao = new JoinDaoImpl();
		dao.checkId("test");

	}

}
