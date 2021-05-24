package com.web;

import com.pojo.*;
import com.service.CommunityService;
import com.service.MessageService;
import com.service.impl.CommunityServiceImpl;
import com.service.impl.MessageServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommunityServlet extends BaseServlet {


    private final CommunityService communityService = new CommunityServiceImpl();
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
        //2 调用CommunityServiceService.page(pageNo，pageSize)：Page对象
        Page<Question> page = communityService.page(pageNo, pageSize);
        page.setUrl("communityServlet?action=page");
        //3 保存Page对象到Request域中，兼具初始化的功能
        req.setAttribute("page", page);
        //4 请求转发
        req.getRequestDispatcher("/pages/community/community.jsp").forward(req, resp);
    }


    /**
     * 新建问题
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注入对象
        Question question = WebUtils.copyParamToBean(req.getParameterMap(), new Question());
        //添加问题
        communityService.addQuestion(question);
        //重定向
        resp.sendRedirect(req.getContextPath() + "/communityServlet?action=page");
    }

    /**
     * 新建回答
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createAnswer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注入对象
        Answer answer = WebUtils.copyParamToBean(req.getParameterMap(), new Answer());
        String questionName = req.getParameter("questionName");
        Question question = communityService.getUsernameToByQuestionName(questionName);

        Message message = new Message(req.getParameter("username"), question.getUsername(), 1);
        message.setContext("用户" + message.getUsernameFrom() + "回答了您的问题" + questionName);

        //添加回答
        communityService.addAnswer(req.getParameter("questionName"), answer);
        //发送消息
        messageService.addAnswerFromCommunity(message);
        //重定向
        resp.sendRedirect(req.getContextPath() + "/communityServlet?action=page");
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
        //2 调用CommunityServiceService.page(pageNo，pageSize)：Page对象
        Page<Question> page = communityService.pageByQuestion(pageNo, pageSize, searchName);
        page.setUrl("communityServlet?action=searchQuestion&searchName=" + searchName);
        //3 保存Page对象到Request域中，兼具初始化的功能
        req.setAttribute("page", page);
        //4 请求转发
        req.getRequestDispatcher("/pages/community/community.jsp").forward(req, resp);
    }
}
