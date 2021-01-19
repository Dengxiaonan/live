package requireproject.service.role.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import requireproject.repository.role.mapper.MenuMapper;
import requireproject.service.role.MenuService;
import requireproject.service.role.dao.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ldy
 * @Descriptions 管理员用户接口实现类
 * @Date 2020/11/25 11:13
 * @Version: V1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public List<Menu> menuList(List<Menu> menu) {
        List<Menu> list = new ArrayList<>();
        menu.forEach(x ->{
            if (x.getPid().equals("0")) {
                x.setComponent("Layout");
                x.setChildren(menuChild(x.getId(),menu));
                list.add(x);
            }
        });
        return list;
    }

    public List<Menu> menuChild(String id,List<Menu> menu) {
        List<Menu> lists = new ArrayList<>();
        menu.forEach(a ->{
            if (a.getPid().equals(id)) {
                a.setChildren(menuChild(a.getId(),menu));
                lists.add(a);
            }
        });
        return lists;
    }

    @Override
    public List<Map<String, Object>> getMenuList(String userId) {
        List<Menu> menuList = menuMapper.getRoleList(menuMapper.getUserAuthCode(userId));
        System.out.println(menuList);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < menuList.size(); i++) {
            map.put(""+i+"", menuList.get(i));
        }
        map1.put("admininuser_top_item_list" , map);

        list.add(map1);
        return list;
    }



}
