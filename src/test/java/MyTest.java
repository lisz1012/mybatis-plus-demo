import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lisz.bean.User;
import com.lisz.dao.UserDao;
import org.apache.ibatis.session.RowBounds;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

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
		System.out.println("插入了 " + insert + " 条数据");
		System.out.println("被插入的数据的主键是：" + user.getId());
	}

	// 少设置一个Nullable的属性，会灵活的在SQL中留空，动态改变SQL
	@Test
	public void test05(){
		User user = new User();
		user.setName("cassiel");
		user.setEmail("cassiel@gmail.com");
		user.setScore(100.00);
		//user.setBirthDate(new Date("1986/08/05"));
		user.setCreatedAt(new Date());
		user.setModifiedAt(new Date());
		user.setJob("artist");
		user.setVersion(0L);
		int insert = userDao.insert(user);
		System.out.println("插入了 " + insert + " 条数据");
		System.out.println("被插入的数据的主键是：" + user.getId());
	}

	@Test
	public void test06(){
		User user = new User();
		user.setId(11);
		user.setName("cassiel");
		user.setEmail("cassiel@gmail.com");
		user.setScore(100.00);
		user.setBirthDate(new Date("1986/08/05"));
		user.setCreatedAt(new Date());
		user.setModifiedAt(new Date());
		user.setJob("model");
		user.setVersion(1L);
		int update = userDao.updateById(user);
		System.out.println(update);
	}

	@Test
	public void test07(){
		int delete = userDao.deleteById(12);
		System.out.println(delete);
	}

	// DELETE FROM user WHERE id IN ( ? , ? , ? )  -- 批量删除
	@Test
	public void test08(){
		int delete = userDao.deleteBatchIds(Arrays.asList(13, 14, 15));
		System.out.println(delete);
	}

	// 根据Map结构删除，要注意，key为列的名称, 多个key意味着必须都要满足
	@Test
	public void test09(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", 17);
		int delete = userDao.deleteByMap(map);
		System.out.println(delete);
	}

	@Test
	public void test10(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", 17);
		int delete = userDao.deleteByMap(map);
		System.out.println(delete);
	}

	@Test
	public void test11(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("id", 22);
		int delete = userDao.delete(wrapper);
		System.out.println(delete);
	}

	// selectOne要保证结果最多有一条
	@Test
	public void test12(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("id", 8);
		wrapper.eq("name", "yijing");
		User user = userDao.selectOne(wrapper);
		System.out.println(user);
	}

	@Test
	public void test13(){
		List<User> users = userDao.selectList(null);
		System.out.println(users);
	}

	@Test
	public void test14(){
		List<User> users = userDao.selectBatchIds(Arrays.asList(1, 8));
		System.out.println(users);
	}

	// 查询结果结合封装成一个List，里面的对象是map。而@MapKey 对应的结果是Map<Object, User>
	@Test
	public void test15(){
		List<Map<String, Object>> maps = userDao.selectMaps(null);
		System.out.println(maps);
	}

	// select count(*) where ...
	@Test
	public void test16(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("name", "cassiel");
		Integer selectCount = userDao.selectCount(wrapper);
		System.out.println(selectCount);
	}

	// 使用分页的时候，必须要添加一个插件,Mybatis配置文件里:
	/*
	<configuration>
		...
	    <plugins>
	        <plugin interceptor="com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor"></plugin>
	    </plugins>
	</configuration>
	 */
	@Test
	public void test17(){
		Page<User> page = new Page<>(1, 3);
		Page<User> users = userDao.selectPage(page, null);
		for (User user : users.getRecords()) {
			System.out.println(user);
		}
	}

	@Test
	public void test18(){
		Page<User> page = new Page<>(1, 3);
		Page<User> users = userDao.selectPage(page, null);
		for (User user : users.getRecords()) {
			System.out.println(user);
		}
	}

	@Test
	public void test19(){
		List<User> users = userDao.selectUserByCondition();
		System.out.println(users);
	}

	@Test
	public void test20(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		User user = new User();
		user.setName("lishuzheng"); //将要改成什么
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", 10); // 哪（个）些需要被更改？
		userDao.update(user, updateWrapper);
	}

	@Test
	public void test0(){

	}
}
