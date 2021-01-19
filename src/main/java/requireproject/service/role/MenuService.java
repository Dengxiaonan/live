package requireproject.service.role;

import java.util.List;
import java.util.Map;

/**
 *
 * 用户信息接口类
 */


public interface MenuService {

    List<Map<String, Object>> getMenuList(String userId);
}
