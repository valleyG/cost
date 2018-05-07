package com.mysit.system.dao.impl;

import com.mysit.pojo.Cost;
import com.mysit.system.dao.ICostDao;
import com.mysit.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ICostDaoImpl implements ICostDao {

    /**
     * 返回一个费用对象的list对象
     *
     * @param cost
     * @return
     */
    @Override
    public List<Cost> queryCostList(Cost cost) {
        //编写sql语句
        StringBuffer sql = new StringBuffer("select * from t_cost where costMark='0'");
        List<Object> params = new ArrayList<Object>();
        if (cost.getCostId() != 0) {
            sql.append(" and costId=?");
            params.add(cost.getCostId());
        }
        if (cost.getCostName() != null && (!cost.getCostName().equals(""))) {
            sql.append(" and costName like ?");
            params.add("%" + cost.getCostName() + "%");
        }
        //执行sql语句
        try {
            return C3p0Util.queryList(sql.toString(), Cost.class, params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加一个cost对象
     *
     * @param cost
     * @return 返回受影响的行数
     */
    @Override
    public int addCost(Cost cost) {
        //写sql语句
        String sql = "insert into t_cost(costName,costDesc,costMark) values(?,?,'0')";
        try {
            return C3p0Util.update(sql, cost.getCostName(), cost.getCostDesc());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateCost(Cost cost) {
        String sql = "update t_cost set costName=?,costDesc=? where costId=?";
        try {
            return C3p0Util.update(sql, cost.getCostName(), cost.getCostDesc(), cost.getCostId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteCost(Cost cost) {
        Connection conn = null;

        try {
            conn = C3p0Util.getConn();
            conn.setAutoCommit(false);//打开事物
            QueryRunner qr = new QueryRunner();//数据操作对象
            String sql = "update t_cost set costMark='1' where costId = ?";
            int rows = 0;
            //循环执行
            for (int i = 0; i < cost.getIds().length; i++) {
                rows += qr.update(conn, sql, cost.getIds()[i]);
            }
            conn.commit();//提交事物
            conn.setAutoCommit(true);
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public Cost queryOneCost(int costId) {
        String sql = "select * from t_cost where costId=?";
        try {
            return C3p0Util.queryOne(sql, Cost.class, costId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
