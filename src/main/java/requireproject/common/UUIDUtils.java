package requireproject.common;



import requireproject.common.redis.RedisEnum;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 工具类
 */
public final class UUIDUtils {

    private UUIDUtils() {
    }

    /**
     * 获取UUID，不含有-
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 批量获取UUID
     *
     * @param size
     * @return
     */
    public static ArrayList<String> getUUIDList(int size) {
        return Stream.iterate(1, item -> item + 1)
                .limit(size)
                .map(item -> getUUID())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     　* @description: 生成reids 的key
     　* @param [redisKay]
     　* @return java.lang.String
     　* @throws
     　* @author biangonglei
     　* @date 2019/6/21 23:06
     　*/
    public static String getRedisKeyByUUID(String redisKay){
        return "EASY_"+redisKay+"_"+UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     　* @description: 生成用户reids 的key
     　* @param [redisKay]
     　* @return java.lang.String
     　* @throws
     　* @author biangonglei
     　* @date 2019/6/21 23:06
     　*/
    public static String getRedisKeyByUserId(RedisEnum type, String userId){
        return "EASY_"+type+"_"+userId;
    }



}
