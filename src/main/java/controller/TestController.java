package controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.deploy.net.HttpResponse;
import common.Excel.ExcelUtil;
import domain.Book;
import impl.MailServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;
import service.MailService;
import service.ShareService;
import vo.MailVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("/main")
/**
 * @author wangzhenxing
 */
public class TestController {
    @Autowired
    private ShareService shareService;
    @Autowired
    private MailService mailService;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 获取数据合并分支
     * @return
     */
    @RequestMapping("/userData")
    @ResponseBody
    @SneakyThrows
    public List<Book> userData(){
        HashMap<String, Object> map = Maps.newHashMap();
        List<Book> list = shareService.findShare(map);
        return list;
    }


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
