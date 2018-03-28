package service;

import common.Exception.RecipientNotFoundException;
import vo.MailVo;

/**
 * @author: wangzx
 * @Date: 2018-01-31-10:33
 */
public interface MailService {
    /**
     * 发送邮件
     * @param name
     * @param idNo
     * @param loanNo
     * @param staffName
     * @param staffCode
     * @return
     * @throws RecipientNotFoundException
     */
    MailVo sendFinalDispatchFailedMail(String name, String idNo, String loanNo, String staffName, String staffCode) throws RecipientNotFoundException;
}
