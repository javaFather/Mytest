package impl;

import com.google.common.collect.Maps;
import common.Exception.RecipientNotFoundException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import service.MailService;
import vo.MailVo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangzx
 * @Date: 2018-01-31-10:37
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);


    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;


    @Override
    public MailVo sendFinalDispatchFailedMail(String name, String idNo, String loanNo, String staffName, String staffCode) throws RecipientNotFoundException {
        LOGGER.info("发送终审复议再申请邮件, 客户姓名:{}, 身份证号:{}, 借款编号:{}, 员工姓名:{}, 员工工号:{}", name, idNo, loanNo, staffName, staffCode);

        String templateName = "finalDispatchFailed.ftl";
        String subject = "终审复议再申请件分派";

        Map<String, String> params = Maps.newHashMap();
        params.put("name", name);
        params.put("idNo", idNo);
        params.put("loanNo", loanNo);
        params.put("staffName", staffName);
        params.put("staffCode", staffCode);

        // 终审邮件接收人
        String[] recipients = {"wangzx@yuminsoft.com"};

        // 终审邮件抄送人
        String[] ccList = new String[]{};


        return sendMail(subject, recipients, ccList, templateName, params);
    }

    public MailVo sendMail(String subject, String[] recipients, String[] ccList, String template, Map<String, String> params) {
        MailVo mailVo = new MailVo();
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);

            // 发送人
            helper.setFrom("lihm@yuminsoft.com");
            // 接收人
            helper.setTo(recipients);
            // 抄送人
            if (ccList != null && ccList.length > 0) {
                helper.setCc(ccList);
            }
            // 邮件标题
            msg.setSubject(subject);
            // 邮件内容
            String content = generateContent(template, params);
            helper.setText(content, true);
            // 开始发送
            mailSender.send(msg);

            // 返回mailVo
            mailVo.setFrom("lihm@yuminsoft.com");
            mailVo.setToList(Arrays.asList(recipients));
            if(ccList != null ){
                mailVo.setCcList(Arrays.asList(ccList));
            }
            mailVo.setContent(content);
            mailVo.setSubject(subject);
        } catch (Exception e) {
            LOGGER.error("发送邮件异常", e);
        }

        return mailVo;
    }

    private String generateContent(final String templateName, final Map<String, String> map) throws MessagingException {
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            Map<String, String> context = new HashMap<String, String>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
        } catch (IOException e) {
            throw new MessagingException("模板不存在", e);
        } catch (TemplateException e) {
            throw new MessagingException("处理失败", e);
        }
    }
}
