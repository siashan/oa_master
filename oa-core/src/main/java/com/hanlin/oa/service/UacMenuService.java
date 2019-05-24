package com.hanlin.oa.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hanlin.oa.common.service.BaseService;
import com.hanlin.oa.common.support.security.SecurityUtils;
import com.hanlin.oa.mapper.UacMenuMapper;
import com.hanlin.oa.model.VO.MenuEditVo;
import com.hanlin.oa.model.VO.MenuTreeVo;
import com.hanlin.oa.model.VO.MenuVo;
import com.hanlin.oa.model.domain.UacMenu;
import com.hanlin.oa.model.enums.MenuStatusEnum;
import com.hanlin.oa.model.enums.MenuTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/16 15:46
 * @Modified By:
 */
@Service
public class UacMenuService extends BaseService<UacMenu> {
    @Autowired
    private UacMenuMapper uacMenuMapper;

    public ArrayList<JSONObject> getUserMenu(Long id) {
        List<UacMenu> menus = getUserFatherMenuList(id);
        ArrayList<JSONObject> menuLift = new ArrayList<>();
        // 过滤出type 为  1,6的菜单
        List<UacMenu> menuList = menus.stream().filter((UacMenu m) -> m.getMenuType().intValue() == (MenuTypeEnum.MENU_TYPE_1.getType()) || m.getMenuType().intValue() == (MenuTypeEnum.MENU_TYPE_6.getType())).collect(Collectors.toList());
        // 循环封装
        for (UacMenu m : menuList) {
            genType11(menus, m, menuLift);
        }
        return menuLift;
    }


    public List<UacMenu> selectTree() {
        List<UacMenu> sysMenus = selectAll();
        List<UacMenu> menuTree = new ArrayList<>();

        UacMenu rootMenu = sysMenus.get(0);
        menuTree.add(rootMenu);

        fillMenu(menuTree, sysMenus, rootMenu);


        List<UacMenu> copyList = new ArrayList<UacMenu>();
        // 填充根节点
        UacMenu menu = menuTree.get(0);
        menu = fillMenu(menu, menuTree);
        copyList.add(menu);
        return copyList;
    }

    private void genType11(List<UacMenu> menus, UacMenu m, ArrayList<JSONObject> menuLift) {
        //        long l = System.currentTimeMillis();
        menuLift.add(genType1(menus, m));
        //        System.out.println("用时：" + (System.currentTimeMillis()-l));
    }

    private JSONObject genType1(List<UacMenu> menus, UacMenu m) {
        JSONObject jsonMenu = genMenu(m);
        menus.remove(m);
        List<UacMenu> children = menus.stream().filter((UacMenu mu) -> mu.getParentId().longValue() == m.getId().longValue()).collect(Collectors.toList());
        if (!children.isEmpty()) {
            JSONArray arr = new JSONArray();
            for (UacMenu sm2 : children) {
                JSONObject jsonObject = genType1(menus, sm2);
                arr.add(jsonObject);
                menus.remove(sm2);
            }
            jsonMenu.put("children", arr);
        }
        return jsonMenu;
    }

    private JSONObject genMenu(UacMenu m) {
        JSONObject jo = new JSONObject();
        jo.put("path", m.getPath());
        jo.put("redirect", m.getRedirectPath());
        jo.put("component", m.getComponent());
        jo.put("name", m.getMenuName());
        jo.put("icon", m.getIcon());
        jo.put("type", m.getMenuType());
        if (m.getMenuType().intValue() == MenuTypeEnum.MENU_TYPE_4.getType()) {
            jo.put("perm", m.getUrl());
        }
        return jo;
    }

    public List<UacMenu> getUserFatherMenuList(Long id) {

//	    return baseMapper.getUserFatherMenuList(id);
        List<UacMenu> userFatherMenuList = uacMenuMapper.getUserFatherMenuList(id);
//		long rootId = 1;
        List<UacMenu> menuTree = new ArrayList<>();

        UacMenu rootMenu = selectByPrimaryKey(1L);

        fillMenu(menuTree, userFatherMenuList, rootMenu);
        return menuTree;
    }
    private void fillMenu(List<UacMenu> reList, List<UacMenu> srcMenus, UacMenu rootMenu) {
        List<UacMenu> menuList1 = srcMenus.stream().filter((UacMenu m1) -> m1.getParentId().longValue() == rootMenu.getId().longValue()).collect(Collectors.toList());
        if (!menuList1.isEmpty()) {
            menuList1.sort(new Comparator<UacMenu>() {
                @Override
                public int compare(UacMenu o1, UacMenu o2) {
                    return o1.getOrderBy() - o2.getOrderBy();
                }
            });

            for (UacMenu m : menuList1) {
                reList.add(m);
                fillMenu(reList, srcMenus, m);
            }
        }
    }


    private UacMenu fillMenu(UacMenu menu, List<UacMenu> menuTree) {
        List<UacMenu> children = menuTree.stream().filter((UacMenu m) -> m.getParentId().longValue() == menu.getId().longValue()).collect(Collectors.toList());
        if (!children.isEmpty()) {
            menu.setChildren((ArrayList<UacMenu>) children);
            for (UacMenu child : menu.getChildren()) {
                fillMenu(child, menuTree);
            }
        }
        return menu;
    }



    public ArrayList<MenuVo> findMenuVoListByUserId(Long userId){
        // 查询出用户全部MenuVo列表
        List<MenuVo> menuVoList = uacMenuMapper.findMenuVoListByUserId(userId);
        // 去重
        if (!menuVoList.isEmpty()){
            HashSet<MenuVo> menuVoSets = new HashSet<>(menuVoList);
            return  new ArrayList<MenuVo>(menuVoSets);
        }
        return   null;
    }


    public ArrayList<MenuTreeVo> selectMenuVoTree() {
//        // 查询出用户
//        ArrayList<MenuVo> menuVoSets = findMenuVoListByUserId(userId);
//        // 过滤出type 为  1,6的菜单
//        List<MenuVo> menuList = menuVoSets.stream().filter((MenuVo m) -> m.getMenuType().intValue() == (MenuTypeEnum.MENU_TYPE_1.getType()) || m.getMenuType().intValue() == (MenuTypeEnum.MENU_TYPE_6.getType())).collect(Collectors.toList());
        List<MenuTreeVo> srcMenuTreeVo = selectMenuTreeVoAll();
        ArrayList<MenuTreeVo> distMenuTreeVo = Lists.newArrayList();
//        取出根目录
        MenuTreeVo rootMenuTreeVo = srcMenuTreeVo.get(0);
        fillMenuTreeVo(distMenuTreeVo,srcMenuTreeVo,rootMenuTreeVo);
        distMenuTreeVo.add(rootMenuTreeVo);
        return distMenuTreeVo;
    }


    public ArrayList<MenuTreeVo> selectMenuTreeVoAll(){
        return uacMenuMapper.selectMenuTreeVoAll();
    }



    /**
     * Description: 递归封装菜单Tree结构 <br/>
     *
     * @param:dist  目标集合
     * @param src  源集合
     * @param root  跟目录
     * @return:
     * @Author: kfc
     * @Date: 2019/5/17 10:26
     */
    private void fillMenuTreeVo(List<MenuTreeVo> dist, List<MenuTreeVo> src, MenuTreeVo root) {
        List<MenuTreeVo> menuTreeVoList = src.stream().filter((MenuTreeVo m1) -> m1.getParentId().longValue() == root.getId().longValue()).collect(Collectors.toList());
        if (!menuTreeVoList.isEmpty()) {
            menuTreeVoList.sort(new Comparator<MenuTreeVo>() {
                @Override
                public int compare(MenuTreeVo o1, MenuTreeVo o2) {
                    return o1.getOrderBy() - o2.getOrderBy();
                }
            });
            root.setChildren(menuTreeVoList);
            src.removeAll(menuTreeVoList);
            for (MenuTreeVo m : menuTreeVoList) {
                fillMenuTreeVo(dist, src, m);
            }
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void save(UacMenu menu) {
        long currentUserId = SecurityUtils.getCurrentUserId();
        String currentLoginName = SecurityUtils.getCurrentLoginName();
        menu.setLastOperatorId(currentUserId);
        menu.setLastOpertor(currentLoginName);
        Date date = new Date();
        menu.setLastUpdateTime(date);
        if (null == menu.getId()){
            menu.setCreator(currentLoginName);
            menu.setCreatorId(currentUserId);
            menu.setCreateTime(date);
            menu.setStatus(MenuStatusEnum.ENABLE.getCode());
            uacMenuMapper.insertSelective(menu);
        }else{
            uacMenuMapper.updateByPrimaryKeySelective(menu);
        }
    }

    public MenuEditVo selectMenuEditVo(Long id) {
        UacMenu uacMenu = selectByPrimaryKey(id);
        return new ModelMapper().map(uacMenu,MenuEditVo.class);
    }
}
