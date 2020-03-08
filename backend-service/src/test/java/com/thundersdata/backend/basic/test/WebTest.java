package com.thundersdata.backend.basic.test;

import com.thundersdata.backend.basic.ServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class WebTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test() throws Exception {
        mvc.perform(post("/hello/index")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "xiaoming")    //传入参数 
                .accept(MediaType.APPLICATION_FORM_URLENCODED))  //接收的类型
                .andExpect(status().isOk())         //判断接收到的状态是否是200
                .andDo(print())        //打印内容
                .andExpect(content().string("Hello:xiaoming"));
    }
}
