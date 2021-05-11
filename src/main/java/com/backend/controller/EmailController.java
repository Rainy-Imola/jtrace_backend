package com.backend.controller;

import com.backend.entity.Captcha;
import com.backend.entity.User;
import com.backend.service.CaptchaService;
import com.backend.service.MailService;
import com.backend.service.UserService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaService captchaService;

    private JSONObject data;

    @PostMapping("/sendemail")
    public Msg sendEmail(@RequestBody JSONObject jsonObject) {
        String email = jsonObject.getString("email");

        User user = userService.findByEmail(email);
        Logger logger = Logger.getLogger(EmailController.class);

        if (user != null) {
            logger.error("Path: /email/sendemail, status: failed, error: email has been used");
            return MsgUtils.makeMsg(MsgUtils.ERROR, MsgUtils.ERROR_MSG);
        }

        data = mailService.sendMimeMail(email);
        String code = data.getString("code");

        Captcha captcha = captchaService.findByEmail(email);
        if (captcha != null) {
            captcha.setCaptcha(code);
            captchaService.addCaptcha(captcha);
        } else {
            Captcha captcha1 = new Captcha();
            captcha1.setEmail(email);
            captcha1.setCaptcha(code);
            captchaService.addCaptcha(captcha1);
        }

        logger.info("Path: /email/sendemail, status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    @PostMapping("/checkemail")
    public Msg checkEmail(@RequestBody JSONObject jsonObject) {
        String email = jsonObject.getString("email");
        Captcha captcha = captchaService.findByEmail(email);
        String code = captcha.getCaptcha();

        JSONObject checkJson = new JSONObject();
        checkJson.put("email", email);
        checkJson.put("code", code);

        if (mailService.checkCode(checkJson, jsonObject)) {
            return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
        } else {
            return MsgUtils.makeMsg(MsgUtils.ERROR, MsgUtils.ERROR_MSG);
        }
    }
}
