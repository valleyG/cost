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
import java.util.List;

@WebServlet("/system/CostQuery")
public class CostQueryServlet extends HttpServlet {
    ICostService costService = new ICostServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求数据
        Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
        //调用业务逻辑层
        List<Cost> costs = costService.queryCostList(cost);
        //将数据返回页面并跳转
        req.setAttribute("costs", costs);
        req.setAttribute("cost", cost);
        req.getRequestDispatcher("/view/system/cost/cost_list.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
