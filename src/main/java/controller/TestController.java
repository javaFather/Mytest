package controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Maps;
import domain.Book;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;
import service.MailService;
import service.CustomerShareService;
import vo.MailVo;

import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("/main")
/**
 * @author wangzhenxing
 */
public class TestController {
    @Autowired
    private CustomerShareService customerShareService;
    @Autowired
    private MailService mailService;

    @Autowired
    private JedisCluster jedisCluster;

    @Reference
//    ShareService shareService;

    /**
     * 获取数据
     * @return
     */
    @RequestMapping("/userData")
    @ResponseBody
    @SneakyThrows
    public List<Book> userData(){
        HashMap<String, Object> map = Maps.newHashMap();
        List<Book> list = customerShareService.findShare(map);
        return list;
    }

    /**
     * 主页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index_v1";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    /**
     * 发送邮件
     */
    @RequestMapping("/message")
    @ResponseBody
    public void message(){
        try {

            MailVo mailVo = mailService.sendFinalDispatchFailedMail("嘿嘿", "ss", "12156416545", "好名字哦", "编号");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
