package common.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * @author: wangzx
 * @Date: 2018-03-26-16:39
 */
public class RedisUtils implements Redis {
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 在表头位置插入一个值
     * @param key
     * @param value
     */
    @Override
    public Long addHead(String key , String value) {
        Long length = jedisCluster.lpush(key,value);
        return length;
    }

    /**
     * 在表头添加多个值
     * @param key
     * @param arry
     * @return
     */
    @Override
    public Long addHeads(String key, String[] arry) {
        Long size = jedisCluster.lpush(key,arry);
        return size;
    }

    /**
     * 返回list中所有元素
     * @param desList
     * @return
     */
    @Override
    public List<String> list(String desList) {
        List<String> list = jedisCluster.lrange(desList,0,-1);
        return  list;
    }

    /**
     * 初始化List
     * @param listName list名称
     * @param str
     * @return
     */
    @Override
    public Long createList(String listName, String... str) {
        Long length = jedisCluster.lpush(listName, str);
        return length;
    }

    /**
     * 根据list名称跟index获取元素
     * @param listName
     * @param index
     * @return
     */
    @Override
    public String getOne(String listName, long index) {
        String des = jedisCluster.lindex(listName, index);
        return des;
    }
}
