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

import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotPlaceParamDto;
import com.ssafy.enjoytrip.hotplace.model.mapper.HotPlaceMapper;
import com.ssafy.enjoytrip.planboard.model.BoardParameterDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.planboard.model.mapper.BoardMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotPlaceDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(HotPlaceDaoTest.class);

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
	HotPlaceMapper hotPlaceMapper;

	@Test
	public void testBeanCreation() {
		assertNotNull(ds);
		assertNotNull(hotPlaceMapper);
	}
	
	@Ignore
	@Test
	public void 게시글목록테스트() throws SQLException {
		HotPlaceParamDto param = new HotPlaceParamDto();
		// 원하는 페이지 번호
		int pg = 2;
		param.setPg(pg);
		int start = param.getPg() == 0 ? 0 : (param.getPg() - 1) * param.getSpp();
		param.setStart(start);
		

		List<HotPlaceDto> list = hotPlaceMapper.listHotPlace(param);
		assertNotNull(list);
		for (int i = 0; i < list.size(); i++) {
			logger.debug(list.get(i).toString());
		}
		logger.debug("현재 페이지 글 개수 : " + list.size());
		System.out.println("관광지 아이디" + list.get(0).getContentId());
		System.out.println(hotPlaceMapper.getHotPlace(list.get(0).getContentId()));
	}
	
	@Test
	public void 핫플조회카운트증가테스트() throws SQLException {
		int contentId = 125266;
		int before = hotPlaceMapper.getHotPlace(contentId).getReadCount();
		hotPlaceMapper.increaseReadCount(contentId);
		int after = hotPlaceMapper.getHotPlace(contentId).getReadCount();
		System.out.println(before);
		System.out.println(after);
	}

}
