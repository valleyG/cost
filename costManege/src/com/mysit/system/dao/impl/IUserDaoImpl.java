package com.mysit.system.dao.impl;

import com.mysit.pojo.Menu;
import com.mysit.pojo.User;
import com.mysit.system.dao.IUserDao;
import com.mysit.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import sun.security.krb5.internal.PAData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IUserDaoImpl implements IUserDao {
    @Override
    public int addUser(User user) {
        //写sql语句
        String sql = "insert into t_user(roleId,userName,userSex,userAge,userAccount,userPwd,userBasic,userPhone,userMark) " +
                "values(?,?,?,?,?,?,?,?,?)";
//        执行sql语句
        try {
            return C3p0Util.update(sql, user.getRoleId(), user.getUserName(), user.getUserSex(), user.getUserAge(), user.getUserAccount()
                    , user.getUserPwd(), user.getUserBasic(), user.getUserPhone(), "0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User checkUserAccount(String userAccount) {
//       1.写sql语句
        String sql = "select * from t_user where userAccount=?";
        try {
            return C3p0Util.queryOne(sql, User.class, userAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取登陆对象信息
     *
     * @param userAccount 用户账号
     * @param userPwd     用户密码
     * @return 如果存在该用户则返回该用户对象，否者直接返回null
     */
    @Override
    public User login(String userAccount, String userPwd) {
//        写sql语句
        String sql = "select tu.*,roleName as userRole from t_user tu inner join t_role tr on tu.roleId=tr.roleId where userAccount =? and userPwd=? and userMark=?";
        try {
            return C3p0Util.queryOne(sql, User.class, userAccount, userPwd, "0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取角色对应的权限菜单目录
     *
     * @param roleId 角色id
     * @return 返回相对应的菜单目录
     */
    @Override
    public List<Menu> getUserMenuList(int roleId) {
        //sql语句
        String sql = "select tm.menuId,tm.menuName,tm.menuUrl,tm.pMenuId from t_menu tm " +
                "INNER JOIN t_role_menu trm ON tm.menuId=trm.menuId where roleId=?";
        try {
            return C3p0Util.queryList(sql, Menu.class, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 用户查询列表
     *
     * @param user 传入用户对象
     * @return 根据用户传入对象去查询用户列表
     */
    @Override
    public List<User> queryUsers(User user) {
        //不定条件查询
        StringBuffer sql = new StringBuffer("select tu.*,roleName as userRole from t_user tu inner join t_role tr" +
                " on tu.roleId=tr.roleId where userMark='0'");

        //2.拼接查询条件,用stringbuffer去拼接条件
        List<Object> params = new ArrayList<Object>();//接受参数列表
        if (user.getUserId() != 0) {
            sql.append(" and userId=?");
            params.add(user.getUserId());
        }
        if (user.getUserName() != null && !user.getUserName().equals("")) {
            sql.append(" and userName like ?");
            params.add("%" + user.getUserName() + "%");
        }
        //执行sql,注意 不定参数:要么一个个写参数，要么就给一个数组，不能给一个集合
        try {
            return C3p0Util.queryList(sql.toString(), User.class, params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过用户id去查询相对应的user用户
     *
     * @param userId 用户id
     * @return 返回查询到的user对象
     */
    @Override
    public User queryOneUser(int userId) {
        //写sql语句
        String sql = "select tu.*,tr.roleName as userRole from t_user tu inner join t_role tr on tu.roleId=tr.roleId where userId=?";
        try {
            return C3p0Util.queryOne(sql, User.class, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过传入用户对象更新用户
     *
     * @param user
     * @return 返回受影响的行数
     */
    @Override
    public int update(User user) {
        //写sql语句
        String sql = "update t_user set roleId=?,userName=?,userSex=?,userAge=?,userPhone=?,userPwd=?" +
                ",userBasic=? where userId=?";
        try {
            return C3p0Util.update(sql, user.getRoleId(), user.getUserName(), user.getUserSex(),
                    user.getUserAge(), user.getUserPhone(), user.getUserPwd(), user.getUserBasic(), user.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除用户
     *
     * @param user
     * @return 返回受影响的行数
     */
    @Override
    public int delete(User user) {
        //受影响的行数
        int row = 0;

        //sql语句
        String sql = "update t_user set userMark='1' where userId=?";

        //当多条更新的时候必须用到事物
        Connection conn = null;
        try {
            conn = C3p0Util.getConn();//建立连接

            conn.setAutoCommit(false);//关闭自动提交
            QueryRunner qr=new QueryRunner();//获取数据操作对象

            int rows=0;//受影响的行数
            //循环修改
            for (int i = 0; i < user.getIds().length; i++) {
                rows+=qr.update(conn, sql,user.getIds()[i]);
            }
            conn.commit();//提交事物
            conn.setAutoCommit(true);
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();//出现异常事物回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
//        for (int userId:user.getIds()){
//            try {
//                if(C3p0Util.update(sql,userId)>0){
//                    row++;
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        return row;
    }
}
