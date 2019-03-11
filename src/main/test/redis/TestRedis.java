package redis;

//import com.lx.animation.util.JedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root on 18-6-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestRedis {
    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Test
    public void test() {
//
//        JedisCache jedisCache = new JedisCache();
//        System.out.println(jedisCache.getJedisPool());
//        jedisCache.setJedisPool(null);
//        JedisCache jedisCache1=new JedisCache();
//        System.out.println(jedisCache1.getJedisPool().getPoolConfig().getMaxTotal());
        //        Jedis jedis = new Jedis("127.0.0.1");
//        String keys = "name";
//        jedis.del(keys);
//
//        jedis.set(keys, "snowolf");
//
//        String value = jedis.get(keys);
//
//        System.out.println(value);
    }
}
