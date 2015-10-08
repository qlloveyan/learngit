package base_pro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ql.basepro.system.utils.RedisSpringClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/application.xml",
		"classpath:spring/spring-mybatis.xml",
		"classpath:spring/spring-redis.xml"})
public class TestFrame {

	@Autowired
	private RedisSpringClient redisSpringClient;
	
	@Test
	public void testRedis(){
		System.out.println(redisSpringClient.strRead(RedisSpringClient.DEVELOP, "quanli"));
		
	}
}
