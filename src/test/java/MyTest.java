import com.alibaba.druid.pool.DruidDataSource;
import com.lisz.bean.User;
import com.lisz.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class MyTest {

	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private UserDao userDao = context.getBean("userDao", UserDao.class);

	@Test
	public void test01() throws Exception{
		DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
		System.out.println(dataSource);
		System.out.println(dataSource.getConnection());
	}

	@Test
	public void test02(){
		User user = userDao.selectById(1);
		System.out.println(user);
	}

	@Test
	public void test03(){
		List<User> users = userDao.selectList(null);
		users.forEach(System.out::println);
	}

	@Test
	public void test04(){
		User user = new User();
		user.setName("cassiel");
		user.setEmail("cassiel@gmail.com");
		user.setScore(100.00);
		user.setBirthDate(new Date("1986/08/05"));
		user.setCreatedAt(new Date());
		user.setModifiedAt(new Date());
		user.setJob("artist");
		user.setVersion(0L);
		int insert = userDao.insert(user);
		System.out.println(insert);
	}

	@Test
	public void test0x(){

	}
}
