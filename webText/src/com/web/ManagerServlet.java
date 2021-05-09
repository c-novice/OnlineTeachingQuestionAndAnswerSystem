package com.web;

import com.pojo.*;
import com.service.CommunityService;
import com.service.MessageService;
import com.service.UserService;
import com.service.impl.CommunityServiceImpl;
import com.service.impl.MessageServiceImpl;
import com.service.impl.UserServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManagerServlet extends BaseServlet {

    private final CommunityService communityService = new CommunityServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final MessageService messageService = new MessageServiceImpl();

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
        Page<User> pageUsers = userService.page(pageNo, Integer.MAX_VALUE);
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
        Question newQuestion = new Question(req.getParameter("newQuestionName"), (String) req.getSession().getAttribute("username"));
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

        //获取usernameFrom
        String usernameFrom = "amin";

        //获取usernameTo
        String usernameTo = communityService.getUsernameToByQuestionName(name).getUsername();

        //设置context和type
        Message message = new Message(usernameFrom, usernameTo, 0);
        message.setContext("管理员删除了你在问题：" + name + "中的回答:" + context);

        //删除回答
        communityService.deleteAnswerByNameAndContext(name, context);
        //发送消息
        messageService.addAnswerFromCommunity(message);

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
            communityService.updateQuestion(new Question(id, name, (String) req.getSession().getAttribute("username")));
        } else {
            //2、修改t_answer
            communityService.addAnswer(name, new Answer("admin", context, 0));
        }
        //重定向
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=page");
    }

    /**
     * 根据用户名查询
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void searchUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数
        String searchName = req.getParameter("searchName");
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE / 2);
        //2 调用CommunityServiceService.page(pageNo，pageSize)：Page对象
        Page<Question> pageManager = communityService.page(pageNo, pageSize);
        Page<User> pageUsers = userService.pageByUsername(pageNo, Integer.MAX_VALUE, searchName);
        pageManager.setUrl("managerServlet?action=searchUsername&searchName" + searchName);
        //3 保存Page对象到Request域中，兼具初始化的功能
        req.setAttribute("pageManager", pageManager);
        req.setAttribute("pageUsers", pageUsers);
        req.setAttribute("searchName", searchName);
        //4 请求转发
        req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req, resp);
    }

    /**
     * 根据问题名查询
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void searchQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数
        String searchName = req.getParameter("searchName");
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE / 2);
        //2 调用CommunityServiceService.page(pageNo，pageSize)：Page对象
        Page<Question> pageManager = communityService.pageByQuestion(pageNo, pageSize, searchName);
        Page<User> pageUsers = userService.page(pageNo, Integer.MAX_VALUE);
        pageManager.setUrl("managerServlet?action=searchQuestion&searchName=" + searchName);
        //3 保存Page对象到Request域中，兼具初始化的功能
        req.setAttribute("pageManager", pageManager);
        req.setAttribute("pageUsers", pageUsers);
        req.setAttribute("searchName", searchName);
        //4 请求转发
        req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req, resp);
    }
}
