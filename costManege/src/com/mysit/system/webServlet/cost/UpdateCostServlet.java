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

@WebServlet("/system/updateCost")
public class UpdateCostServlet extends HttpServlet {
    ICostService costService = new ICostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传过来的cost对象信息
        Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
        //调用业务逻辑
        Cost queryCost = costService.queryOneCost(cost.getCostId());
        //将查询到的cost对象传回页面并跳转
        req.setAttribute("cost", queryCost);
        req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);

        boolean flag = costService.updateCost(cost);

        if(flag){
            req.setAttribute("tip", "费用修改成功");
        }else{
            req.setAttribute("tip", "费用修改失败");
        }
        //将修改的信息回传给页面
        req.setAttribute("cost", cost);
        req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
    }
}
