package com.ssafy.enjoytrip.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.model.mapper.UserMapper;

/**
 * https://www.javaguides.net/p/junit-4.html
 * business layer(Service), persistence layer(Dao)
 * 단위 테스트 
 * @Before, @After, @Test  Assert.assertXxx()
 * @author SSAFY
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
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
    UserMapper userMapper;
    
    @Test
	public void testBeanCreation() {
		assertNotNull(ds);
		assertNotNull(userMapper);
	}
    
    /**
	 * userMapper insert test
	 * 테스트 후 다시 DB를 원래대로 돌리기 위해  @Transactional을 선언
     * @throws SQLException 
	 */
    @Ignore
    @Test
	@Transactional
	public void 유저등록테스트() throws SQLException {
		//before : user data 생성
		UserDto user = new UserDto("test123", "1234", "test@test.com", "testName", 10); 
		
		//when 
		userMapper.joinUser(user);
		user = userMapper.loginUser("test123"); // id와 이름만 반환함
		
		//then
		assertNotNull(user);
		assertEquals(user.getName(), "testName");
		assertEquals(user.getId(), "test123");
		
		user = userMapper.getUser("test123");
		assertNotNull(user);
		assertEquals(user.getId(), "test123");
		assertEquals(user.getPassword(), "1234");
		assertEquals(user.getEmail(), "test@test.com");
		assertEquals(user.getName(), "testName");
		assertEquals(user.getAge(), 10);
		
//		
//		bookDao.delete("isbn");
//		book = bookDao.findByIsbn("isbn");
//		assertNull(book);
	}
    
    @Ignore
    @Test
	@Transactional
	public void 아이디중복테스트() throws SQLException {
		
    	//when 
		int cnt = userMapper.idCheck("ssafy");
		
		//then
		assertEquals(cnt, 1);
		//assertTrue(cnt==1);
	}
    
    @Ignore
    @Test
	@Transactional
	public void 유저업데이트테스트() throws SQLException {
    	//before : user data 생성
    	UserDto user = new UserDto("ssafy", "$2a$10$5d.Wkvg/rYr/PY2dkmjlIuPhGMDk52.7SHJHiYys3Zy8.91MpPUbe", "ssafy@ssafy.com", "김싸피", 21); 
    	//when 
		userMapper.updateUser(user);
		
		//then
		assertEquals(userMapper.getUser("ssafy").getAge(), 21);
		
	}
    
    @Test
//	@Transactional
	public void 유저삭제테스트() throws SQLException { 
    	//when 
		userMapper.deleteUser("admin");
		
		//then
		int cnt = userMapper.idCheck("admin");
		assertEquals(cnt, 0);
		
	}
}
