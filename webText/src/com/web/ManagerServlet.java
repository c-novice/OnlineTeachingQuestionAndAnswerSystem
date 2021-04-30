package com.web;

import com.pojo.Answer;
import com.pojo.Page;
import com.pojo.Question;
import com.pojo.User;
import com.service.CommunityService;
import com.service.UserService;
import com.service.impl.CommunityServiceImpl;
import com.service.impl.UserServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ManagerServlet extends BaseServlet {

    private CommunityService communityService = new CommunityServiceImpl();
    private UserService userService = new UserServiceImpl();


    /**
     * 初始化、保存数据和处理分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE / 2);
        //2 调用CommunityServiceService.page(pageNo，pageSize)：Page对象
        Page<Question> pageManager = communityService.page(pageNo, pageSize);
        Page<User> pageUsers = userService.page(pageNo, pageSize);
        pageManager.setUrl("managerServlet?action=page");
        //3 保存Page对象到Request域中，兼具初始化的功能
        req.setAttribute("pageManager", pageManager);
        req.setAttribute("pageUsers", pageUsers);
        //4 请求转发
        req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req, resp);
    }

    /**
     * 添加一个新用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    /**
     * 删除一个用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要删除的用户id
        Integer id = Integer.parseInt(req.getParameter("deleteUserId"));

        //删除
        userService.deleteUserById(id);

        //重定向
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=page");
    }

    /**
     * 修改一个用户信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void changeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注入对象
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //更新信息
        userService.updateUser(user);

        //重定向
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=page");
    }

    /**
     * 添加一个新问题
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取新问题的名字
        Question newQuestion = new Question(req.getParameter("newQuestionName"));
        //调用service层add方法
        communityService.addQuestion(newQuestion);
        //重定向
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=page");
    }

    /**
     * 删除一个问题
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("deleteQuestion"));
        String context = req.getParameter("deleteAnswer");
        String name = req.getParameter("deleteName");
        //若context为空说明需要删除t_question内容
        if (context.equals(""))
            communityService.deleteQuestionById(id);
        else
            communityService.deleteAnswerByNameAndContext(name, context);
        //若context不为空说明需要删除t_answer内容

        //重定向
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=page");
    }

    /**
     * 修改一个问题信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void changeQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        Integer id = Integer.parseInt(req.getParameter("changeQuestion"));
        String name = req.getParameter("changeName");
        String context = req.getParameter("changeAnswer");

        Question preQuestion = communityService.queryQuestionById(id);
        if (!preQuestion.getName().equals(name)) {
            //1、修改t_question
            communityService.updateQuestion(new Question(id, name));
        } else {
            //2、修改t_answer
            communityService.addAnswer(name, new Answer("admin", context, 0));
        }

        //重定向
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=page");
    }

}
