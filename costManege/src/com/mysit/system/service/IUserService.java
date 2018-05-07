package com.mysit.system.service;

import com.mysit.pojo.Menu;
import com.mysit.pojo.User;

import java.util.List;


public interface IUserService {
    /**
     * 服务层添加用户
     * @param user
     * @return 添加成功返回true，否者返回false
     */
    boolean addUser(User user);

    /**
     * 员工账户检测
     * @param userAccount
     * @return 账户可用返回 true，账户不可用返回false
     */
    boolean checkAccount(String userAccount);

    /**
     * 员工登陆
     * @param userAccont 员工账号
     * @param userPwd 员工密码
     * @return 返回当前登录的员工对象信息，否者返回为null
     */
    User login(String userAccont,String userPwd);

    /**
     *
     * @param roleId
     * @return
     */
    List<Menu> getUserMenuList(int roleId);

    List<User> queryUsers(User user);
    User queryUserOne(int userId);
    boolean update (User user);
    boolean delete(User user);
}
