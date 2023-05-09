package com.ssafy.enjoytrip.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
	@Ignore
	@Test
	public void 게시글등록테스트() throws SQLException {
		PlanBoardDto article = new PlanBoardDto();
		article.setTitle("testtest");
		article.setOrder("125266-125405-125406");
		article.setContent("테스트내용입니다");
		article.setUserId("ssafy");

		// when
		boardMapper.writeArticle(article);
		logger.debug("번호 : " + article.getArticleNo());
		article = boardMapper.getArticle(article.getArticleNo());
		
		//then
		assertNotNull(article);
		logger.debug("게시글 : " + article);
	}
	
	@Test
	public void 게시글목록테스트() throws SQLException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", "user_id");
		param.put("word", "ssafy");
		param.put("start", 0);
		param.put("listsize", 10);
		List<PlanBoardDto> list = boardMapper.listArticle(param);
		assertNotNull(list);
		for (int i = 0; i < list.size(); i++) {
			logger.debug(list.get(i).toString());
			
		}
		
		assertEquals(list.size(), boardMapper.getTotalArticleCount(param));
	}
	
	@Test
	public void 조회수업데이트테스트() throws SQLException {
		int beforeHit = boardMapper.getArticle(1).getHit();
		boardMapper.updateHit(1);
		assertEquals(boardMapper.getArticle(1).getHit(), beforeHit+1);
	}
	
	@Test
	public void 게시글수정테스트() throws SQLException {
		PlanBoardDto article = new PlanBoardDto();
		article.setTitle("수정된 제목입니당");
		article.setContent("수정된테스트내용입니다");
		article.setArticleNo(1);
		boardMapper.modifyArticle(article);
		assertEquals(article.getTitle(), boardMapper.getArticle(1).getTitle());
	}
	
	@Test
	public void 게시글삭제테스트() throws SQLException {
		int dArticleNum = 3;
		boardMapper.deleteArticle(dArticleNum);
		assertEquals(boardMapper.getArticle(dArticleNum), null);
	}
}
