package com.web;

import com.pojo.Message;
import com.pojo.Page;
import com.pojo.User;
import com.service.MessageService;
import com.service.impl.MessageServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageServlet extends BaseServlet {
    private MessageService messageService = new MessageServiceImpl();

    /**
     * 分页&初始化
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
        User user = (User) req.getSession().getAttribute("user");
        //2、service层获得当前页的数据
        Page<Message> pageMessages = messageService.page(pageNo, pageSize, user.getUsername());
        pageMessages.setUrl("messageServlet?action=page");
        //3 保存Page对象到Request域中，兼具初始化的功能
        req.setAttribute("pageMessages", pageMessages);
        //4、请求转发
        req.getRequestDispatcher("/pages/user/message.jsp").forward(req, resp);
    }

    /**
     * 删除一条消息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要删除的消息id
        Integer id = Integer.parseInt(req.getParameter("deleteMessageId"));
        //删除
        messageService.deleteMessageById(id);
        //请求转发
        resp.sendRedirect(req.getContextPath() + "/messageServlet?action=page");
    }

}
