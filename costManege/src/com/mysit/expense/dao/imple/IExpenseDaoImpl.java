package com.mysit.expense.dao.imple;

import com.mysit.expense.dao.IExpenseDao;
import com.mysit.pojo.AuditRecord;
import com.mysit.pojo.Expense;
import com.mysit.pojo.ExpenseCondition;
import com.mysit.pojo.ExpenseDetail;
import com.mysit.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IExpenseDaoImpl implements IExpenseDao {
    @Override
    public int addExpense(Expense expense) {
        //多条记录操作需要用到事物
        Connection conn = null;
        QueryRunner qr = new QueryRunner();//数据库操作对象
        int rows = 0;//记录操作的行数
        try {
            conn = C3p0Util.getConn();//获取连接
            conn.setAutoCommit(false);//打开事物
            //1.添加报销单
            String addExpenseSql = "insert into t_expense(userId,expenseName,expenseTotal,expenseDate,expenseDesc,expenseState) " +
                    "values(?,?,?,now(),?,?)";
            rows = qr.update(conn, addExpenseSql, expense.getUserId(), expense.getExpenseName(), expense.getExpenseTotal()
                    , expense.getExpenseDesc(), expense.getExpenseState());

            //2.添加报销明细
            String addDetailSql = "insert into t_expense_detail(expenseId,costId,expenseDesc,expenseMoney) values(?,?,?,?)";

            //获取最后插入的expenseId
            String queryExpenseId = "select Last_insert_id()";
            int expenseId = qr.query(conn,queryExpenseId, new ScalarHandler<BigInteger>()).intValue();//获取到当前插入的expenseId
            for (int i = 0; i < expense.getCostIds().length; i++) {
                rows += qr.update(conn, addDetailSql, expenseId, expense.getCostIds()[i], expense.getDetailDescs()[i],
                        expense.getDetailMoneys()[i]);
            }
            conn.commit();//提交事物
            conn.setAutoCommit(true);//将自动提交打开
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

    /**
     * 根据不同的查询状态查询不同的报销单列表
     * state：
     *      1：表示老总
     *      2：表示经理
     *      3：表示财务
     *      4：表示普通员工
     * @param expense
     * @return
     */
    @Override
    public List<Expense> queryExpenseList(Expense expense) {
        //编写sql语句
        String sql="select te.*,userName,expenseStateName as stateName from t_expense te ,t_user tu," +
                "t_expensestate_statename tes where tu.userId = te.userId and te.expenseState=" +
                "tes.expenseStateId  and expenseState = ?";
        try {
            return C3p0Util.queryList(sql, Expense.class,expense.getExpenseState());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据报销单号获取报销单信息
      * @param expenseId 报销单号
     * @return 返回对应的报销单信息
     */
    @Override
    public Expense queryOneExpense(int expenseId) {
        String sql="select * from t_expense where expenseId = ?";
        try {
            return  C3p0Util.queryOne(sql, Expense.class,expenseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据报销单号获取对应的报销明细列表
     * @param expenseId
     * @return
     */
    @Override
    public List<ExpenseDetail> queryExpenseDetails(int expenseId) {
        String sql="select ted.*,costName from t_expense_detail ted,t_cost tc where ted.costId=tc.costId " +
                "and expenseId = ?";
        try {
            return C3p0Util.queryList(sql, ExpenseDetail.class,expenseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param expenseId
     * @return
     */
    @Override
    public List<AuditRecord> queryAuditRecordList(int expenseId) {
        String sql ="select tar.*,userName,expenseStateName as auditStateName from t_audit_record tar,t_user tu,t_expensestate_statename " +
                "tes where" +
                " tar.userId=tu.userId and tar.auditState=tes.expenseStateId and tar.expenseId = ?";
        try {
            return C3p0Util.queryList(sql, AuditRecord.class,expenseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateAuditRecord(AuditRecord auditRecord) {
        String sql="insert into t_audit_record(userId,expenseId,auditSugg,auditState,auditDate) " +
                "values(?,?,?,?,now())";
        try {
            return C3p0Util.update(sql, auditRecord.getUserId(),auditRecord.getExpenseId(),auditRecord.getAuditSugg(),
                    auditRecord.getAuditState());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateExpense(AuditRecord auditRecord) {
        String sql="update t_expense set expenseState = ? where expenseId = ?";
        try {
            return C3p0Util.update(sql, auditRecord.getAuditState(),auditRecord.getExpenseId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Expense> queryExpenseList(ExpenseCondition expenseCondition) {
        StringBuffer sql=new StringBuffer("select te.*,userName,expenseStateName as stateName from t_expense te ,t_user tu,t_expensestate_statename tes where " +
                "te.userId=tu.userId and te.expenseState=tes.expenseStateId " );
        List<Object> params=new ArrayList<Object>();
        if(expenseCondition.getUserId()!=0 && (!"".equals(expenseCondition.getUserId()))){
            sql.append(" and tu.userId = ?");
            params.add(expenseCondition.getUserId());

        }
        if (expenseCondition.getExpenseState()!=null && !"".equals(expenseCondition.getExpenseState())){
            sql.append(" and expenseState = ?");
            params.add(expenseCondition.getExpenseState());
        }
        if(expenseCondition.getUserName()!=null&&(!expenseCondition.getUserName().equals(""))){//添加报销人姓名条件
            sql.append("and userName like ?");
            params.add("%"+expenseCondition.getUserName()+"%");
        }
        if (expenseCondition.getStartDate()!=null && !"".equals(expenseCondition.getStartDate())) {
            sql.append(" and expenseDate >= ?");
            params.add(expenseCondition.getStartDate());
        }
        if (expenseCondition.getEndDate()!=null && !"".equals(expenseCondition.getEndDate())){
            sql.append(" and expenseDate <= ?");
            params.add(expenseCondition.getEndDate());
        }
        if(expenseCondition.getExpenseName()!=null && !"".equals(expenseCondition.getExpenseName())){
            sql.append(" and te.expenseName like ?");
            params.add("%"+expenseCondition.getExpenseName()+"%");
        }

        try {
           return C3p0Util.queryList(sql.toString(), Expense.class, params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public int updateExpense(Expense expense) {
        Connection conn = null;
        QueryRunner qr = new QueryRunner();//数据库操作对象
        int rows = 0;//受影响的行数
        try {
          conn = C3p0Util.getConn();
            conn.setAutoCommit(false);//打开事物提交

            //修改报销状态
            String sql = "update t_expense set expenseTotal = ? , expenseDesc = ?," +
                    " expenseState = ? where expenseId = ?";
            rows=qr.update(conn, sql,expense.getExpenseTotal(),expense.getExpenseDesc(),
                    expense.getExpenseState(),expense.getExpenseId());

            //修改报销单明细，涉及到删除，更新，添加
            //这种情况，先删除所有关联的数据，在重新添加信息
            //删除报销单相关联的数据
            String deleteSql ="delete from t_expense_detail where expenseId = ?";
            rows += qr.update(conn, deleteSql, expense.getExpenseId());
            //再循环添加新的报销明细
            String insertSql = "insert into t_expense_detail(costId,expenseId,expenseDesc,expenseMoney) values(?,?,?,?)";

            for (int i = 0; i < expense.getCostIds().length; i++) {
                rows += qr.update(conn, insertSql, expense.getCostIds()[i],expense.getExpenseId(),expense.getDetailDescs()[i],expense.getDetailMoneys()[i]);
            }
            conn.commit();
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


}
