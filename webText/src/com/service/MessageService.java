package com.service;

import com.pojo.Message;
import com.pojo.Page;

public interface MessageService {
    //分页&初始化
    Page<Message> page(int pageNo, int pageSize);


}
