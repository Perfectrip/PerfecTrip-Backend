package com.ssafy.enjoytrip.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

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

import com.ssafy.enjoytrip.hotplace.model.CommentDto;
import com.ssafy.enjoytrip.hotplace.model.mapper.CommentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(CommentDaoTest.class);

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
	CommentMapper commentMapper;

	@Test
	public void testBeanCreation() {
		assertNotNull(ds);
		assertNotNull(commentMapper);
	}

	@Test
	public void 댓글등록테스트() throws SQLException {
		CommentDto comment = new CommentDto();
		int contentId = 129940;
		comment.setContentId(contentId);
		comment.setUserId("ssafy");
		comment.setCommentText("테스트댓글내용입니다");

		// when
		commentMapper.writeComment(comment);
		logger.debug("번호 : " + comment.getCommentId());
		int n = commentMapper.getTotalCommentCount(contentId);

		// then
		assertNotNull(n);
		logger.debug("개수 : " + n);
	}

	@Test
	public void 댓글목록테스트() throws SQLException {
		List<CommentDto> list = commentMapper.listComment(2030782);
		assertNotNull(list);
		for (int i = 0; i < list.size(); i++) {
			logger.debug(list.get(i).toString());
		}
		logger.debug("해당 관광지 댓글  개수 : " + list.size());
	}

	@Test
	public void 댓글수정테스트() throws SQLException {
		CommentDto comment = new CommentDto();
		comment.setCommentId(23);
		comment.setCommentText("수정된테스트내용입니다");
		commentMapper.modifyComment(comment);
	}

	@Test
	public void 댓글삭제테스트() throws SQLException {
		int dCommentNum = 24;
		commentMapper.deleteComment(dCommentNum);
	}
}
