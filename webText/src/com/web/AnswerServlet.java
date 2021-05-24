package com.web;

import com.pojo.Message;
import com.pojo.Page;
import com.pojo.Question;
import com.pojo.User;
import com.service.AnswerService;
import com.service.MessageService;
import com.service.impl.AnswerServiceImpl;
import com.service.impl.MessageServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AnswerServlet extends BaseServlet {
    private final AnswerService answerService = new AnswerServiceImpl();
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
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        User user = (User) req.getSession().getAttribute("user");
        //2 调用CommunityServiceService.page(pageNo，pageSize)：Page对象
        Page<Question> page = answerService.page(pageNo, pageSize, user.getUsername());
        page.setUrl("answerServlet?action=page");
        //3 保存Page对象到Request域中，兼具初始化的功能
        req.setAttribute("page", page);
        //4 请求转发
        req.getRequestDispatcher("/pages/user/question_answer.jsp").forward(req, resp);
    }

    /**
     * 按关键词搜索
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void searchQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("searchName");       //获取关键词名
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE / 2);
        User user = (User) req.getSession().getAttribute("user");
        //2 调用answerService.page(pageNo，pageSize,searchName,username)：Page对象
        Page<Question> page = answerService.pageByQuestion(pageNo, pageSize, searchName, user.getUsername());
        page.setUrl("answerServlet?action=searchQuestion&searchName=" + searchName);
        //3 保存Page对象到Request域中，兼具初始化的功能
        req.setAttribute("page", page);
        //4 请求转发
        req.getRequestDispatcher("/pages/user/question_answer.jsp").forward(req, resp);
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
        User user = (User) req.getSession().getAttribute("user");
        String username = user.getUsername();

        //要删除的问题id
        Integer id = Integer.parseInt(req.getParameter("deleteQuestionId"));

        //设置context和type
        Message message = new Message(username, username, 0);
        message.setContext("你自己删除了你的问题 : " + req.getParameter("deleteContext"));

        //删除问题
        answerService.deleteQuestionById(id);

        //发送消息
        messageService.addAnswerFromCommunity(message);

        //重定向
        resp.sendRedirect(req.getContextPath() + "/answerServlet?action=page");
    }
}
