package com.mysit.system.webServlet.user;

import com.mysit.finance.service.ISalaryService;
import com.mysit.finance.service.impl.ISalaryServiceImp;
import com.mysit.pojo.Menu;
import com.mysit.pojo.SalaryChart;
import com.mysit.pojo.User;
import com.mysit.system.service.IUserService;
import com.mysit.system.service.impl.IUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/system/doLogin")
public class DoLoginServlet extends HttpServlet {
    private IUserService userService = new IUserServiceImpl();
    ISalaryService salaryService = new ISalaryServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//  1.接受参数
        String account = req.getParameter("userAccount");
        String pwd = req.getParameter("userPwd");
//        2.提交业务逻辑层

//        用户登陆不仅要获取用户信息，还要获取用户的权限信息

        User user = userService.login(account, pwd);//这里获取用户用户信息

//         3.返回数据和参数
        if (user == null) {
            req.setAttribute("tip", "账号或者密码不正确");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } else {
            //当该用户对象存在的时候获取他相对应的目录菜单。
            List<Menu> menus=userService.getUserMenuList(user.getRoleId());
            //获取请求的session
            HttpSession session = req.getSession();
            //将员工信息加入sesstion
            session.setAttribute("userInfo", user);
            //将该员工的权限菜单加入session
            session.setAttribute("menus", menus);
            //薪资报表信息
            List<SalaryChart> salaryCharts = salaryService.querySalaryChart();
            //将薪资报表放入session中去，不能像下面这样
//            req.setAttribute("salaryChart", salaryCharts);
            session.setAttribute("salaryChart", salaryCharts);
            req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
