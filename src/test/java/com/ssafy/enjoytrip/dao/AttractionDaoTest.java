package com.ssafy.enjoytrip.dao;

import static org.junit.Assert.assertNotNull;

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

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttractionDaoTest {
private static final Logger logger = LoggerFactory.getLogger(AttractionDaoTest.class);
	
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
    AttractionMapper attractionMapper;
    
    @Test
	public void testBeanCreation() {
		assertNotNull(ds);
		assertNotNull(attractionMapper);
	}
    
    @Test
    public void 지역별여행지검색테스트() throws SQLException {
    	List<AttractionDto> list = attractionMapper.searchAttractionBySidoAndGugunAndContentTypeId(1, 1, 12);
    	for(AttractionDto ad : list) {
    		logger.debug(ad.toString());
    	}
    }
    
    @Test
    public void 키워드검색테스트() throws SQLException {
    	List<AttractionDto> list = attractionMapper.searchAttractionByKeyword(1, 1, "공원");
    	for(AttractionDto ad : list) {
    		logger.debug(ad.toString());
    	}
    }

}
