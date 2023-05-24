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

import com.ssafy.enjoytrip.planboard.model.BoardParameterDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.planboard.model.mapper.BoardMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanBoardDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardDaoTest.class);

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
		BoardParameterDto param = new BoardParameterDto();
		// 원하는 페이지 번호
		int pg = 1;
		param.setPg(pg);
		int start = param.getPg() == 0 ? 0 : (param.getPg() - 1) * param.getSpp();
		param.setStart(start);
		
//		검색 조건
//		key = article_title or user_id or article_content
		String key = "article_title ";
		String word = "오늘";
		param.setKey(key);
		param.setWord(word);

		List<PlanBoardDto> list = boardMapper.listArticle(param);
		assertNotNull(list);
		for (int i = 0; i < list.size(); i++) {
			logger.debug(list.get(i).toString());
		}
		logger.debug("현재 페이지 글 개수 : " + list.size());
	}
	
	@Ignore
	@Test
	public void 조회수업데이트테스트() throws SQLException {
		int beforeHit = boardMapper.getArticle(1).getHit();
		boardMapper.updateHit(1);
		assertEquals(boardMapper.getArticle(1).getHit(), beforeHit+1);
	}
	
	@Ignore
	@Test
	public void 게시글수정테스트() throws SQLException {
		PlanBoardDto article = new PlanBoardDto();
		article.setTitle("수정된 제목입니당");
		article.setContent("수정된테스트내용입니다");
		article.setArticleNo(1);
		boardMapper.modifyArticle(article);
		assertEquals(article.getTitle(), boardMapper.getArticle(1).getTitle());
	}
	
	@Ignore
	@Test
	public void 게시글삭제테스트() throws SQLException {
		int dArticleNum = 3;
		boardMapper.deleteArticle(dArticleNum);
		assertEquals(boardMapper.getArticle(dArticleNum), null);
	}
}
