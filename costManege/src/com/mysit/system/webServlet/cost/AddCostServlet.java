package com.mysit.system.webServlet.cost;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.pojo.Cost;
import com.mysit.system.service.ICostService;
import com.mysit.system.service.impl.ICostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/system/addCost")
public class AddCostServlet extends HttpServlet{
    ICostService costService=new ICostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面请求数据
        Cost costAddedIfo= RequestBeanUtils.requestToBean(req, Cost.class);
        //提交业务逻辑层
        boolean flag=costService.addCost(costAddedIfo);
        if(flag){//插入成功
            req.setAttribute("tip", "费用信息添加成功");
            req.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(req, resp);
        }else {//插入失败
            req.setAttribute("tip", "费用信息添加失败");
            req.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(req, resp);
        }
    }
}
