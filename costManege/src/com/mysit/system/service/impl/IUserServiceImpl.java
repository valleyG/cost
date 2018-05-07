package com.mysit.system.service.impl;

import com.mysit.pojo.Menu;
import com.mysit.pojo.User;
import com.mysit.system.dao.IUserDao;
import com.mysit.system.dao.impl.IUserDaoImpl;
import com.mysit.system.service.IUserService;

import java.util.List;

public class IUserServiceImpl implements IUserService {
//  dao层数据操作对象
    IUserDao userDao=new IUserDaoImpl();
    @Override
    public boolean addUser(User user) {
        int row=userDao.addUser(user);
        if (row>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAccount(String userAccount) {
    User user=userDao.checkUserAccount(userAccount);
        if(user==null){
            return true;
        }
        return false;
    }

    @Override
    public User login(String userAccont, String userPwd) {
        return userDao.login(userAccont, userPwd);
    }

    /**
     * 通过用户的角色名称来获取相对应的菜单目录
     * @param roleId
     * @return 返回该用户角色对应的菜单目录
     */
    @Override
    public List<Menu> getUserMenuList(int roleId) {
        return userDao.getUserMenuList(roleId);

    }

    /**
     *
     * 通过传的的user对象查询user列表
     * @param user
     * @return
     */
    @Override
    public List<User> queryUsers(User user) {
        return userDao.queryUsers(user);
    }

    /**
     * 通过用户id去查询用户信息
     * @param userId 用户id
     * @return 返回查询到的user对象
     */
    @Override
    public User queryUserOne(int userId) {
        return userDao.queryOneUser(userId);
    }

    /**
     * 修改用户信息
     * @param user
     * @return  修改成功返回true 否则返回false
     */
    @Override
    public boolean update(User user) {
        int rows=userDao.update(user);
        if (rows!=0) {
            return true;
        }
        return false;
    }

    //删除用户
    @Override
    public boolean delete(User user) {
        int rows=userDao.delete(user);
        if(rows>0){
            return true;
        }
        return false;
    }
}
