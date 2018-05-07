package com.mysit.system.dao;

import com.mysit.pojo.Menu;
import com.mysit.pojo.User;

import java.util.List;

public interface IUserDao {
    /**
     * 数据操作层添加用户
     * @param user
     * @return 返回受影响的行数
     */
    int addUser(User user);

    /**
     * 检查用户账户是否存在
     * @param userAccount
     * @return 如果存在 返回该用户对象，否者返回 false
     */
    User checkUserAccount(String userAccount);

    /**
     * 获取登陆用户对象
     * @param userAccount
     * @param userPwd
     * @return
     */
    User login(String userAccount,String userPwd);


    /**
     * 获取对应角色的菜单list
     * @param roleId
     * @return
     */
    List<Menu> getUserMenuList(int roleId);

    List<User> queryUsers(User user);

    User queryOneUser(int userId);
    int update(User user);
    int delete(User user);
}
