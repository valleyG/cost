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
import java.util.Arrays;

@WebServlet("/system/deleteCost")
public class DeleteCostsServlet extends HttpServlet {
    ICostService costService = new ICostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
        boolean flag = costService.deleteCost(cost);
        if(flag){
            req.setAttribute("tip", "删除成功");
        }else {
            req.setAttribute("tip", "删除失败");
        }
        req.getRequestDispatcher("/system/CostQuery").forward(req, resp);
    }
}
