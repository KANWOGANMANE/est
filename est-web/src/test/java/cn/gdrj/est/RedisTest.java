package cn.gdrj.est;

import cn.gdrj.est.entity.Users;
import cn.gdrj.est.service.UsersService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Test
    // 测试存放数据
    void testSet(){
        // 获取操作string类型的值的对象
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("user1","lisi");
    }

    @Test
    // 测试获取数据
    void testGet(){
        // 获取操作string类型的值的对象
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String value = operations.get("user1");
        Assert.assertEquals("msg","lisi",value);
    }


    @Test
        // 测试存放数据
    void testSet2(){
        // 获取操作string类型的值的对象
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        operations.set("user2","lisi");
    }


    @Autowired
    UsersService usersService;
    @Test
        // 测试存放数据
    void testSet3(){
        // 获取操作string类型的值的对象
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        Users users = usersService.findById(4L);
        operations.set("user3",users);
    }

    @Test
    // 测试获取数据
    void testGet2(){
        // 获取操作string类型的值的对象
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        String value = (String) operations.get("user2");
        Assert.assertEquals("msg","lisi",value);
    }
}
