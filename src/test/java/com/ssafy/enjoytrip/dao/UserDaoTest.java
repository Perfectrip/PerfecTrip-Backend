package com.ssafy.enjoytrip.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
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
	
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
	
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
	 * 테스트 후 다시 DB를 원래대로 돌리기 위해 @Transactional을 선언
     * @throws SQLException 
	 */
    
    @Test
	@Transactional
	public void testInsert() throws SQLException {
		//before : user data 생성
		UserDto user = new UserDto("testId", "1234", "test@test.com", "testName", 10); 
		
		//when 
		userMapper.joinUser(user);
		user = userMapper.loginUser("testId");
		//then
		assertNotNull(user);
		assertEquals(user.getName(), "testName");
		assertEquals(user.getAge(), 10);
//		
//		bookDao.delete("isbn");
//		book = bookDao.findByIsbn("isbn");
//		assertNull(book);
	}
}
