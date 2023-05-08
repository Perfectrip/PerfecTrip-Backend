package com.ssafy.enjoytrip.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.planboard.model.mapper.BoardMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanBoardDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@BeforeClass
	public static void beforeClass() {
		logger.debug("----- Class Test Start!!! -----");
	}

	@AfterClass
	public static void afterClass() {
		logger.debug("----- Class Test End!!! -----");
	}

	@Before
	public void beforeMethod() {
		logger.debug("----- Method Test Start!!! -----");
	}

	@After
	public void afterMethod() {
		logger.debug("----- Method Test End!!! -----");
	}

	@Autowired
	DataSource ds;
	@Autowired
	BoardMapper boardMapper;

	@Test
	public void testBeanCreation() {
		assertNotNull(ds);
		assertNotNull(boardMapper);
	}

	@Test
	public void 게시글등록테스트() throws SQLException {
		PlanBoardDto article = new PlanBoardDto();
		article.setTitle("테스트제목입니다");
		article.setOrder("125266-125405-125406");
		article.setContent("테스트내용입니다");
		article.setUserId("ssafy");
		logger.debug(article.toString());
		// when
		boardMapper.writeArticle(article);

		// article = boardMapper.getArticle(0);

		// assertNotNull(article);
	}

}
