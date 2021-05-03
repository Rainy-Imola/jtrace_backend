package com.backend.controller;

import com.backend.entity.Captcha;
import com.backend.service.CaptchaService;
import com.backend.service.MailService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSONObject;
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
    private CaptchaService captchaService;

    private JSONObject data;

    @PostMapping("/sendemail")
    public void sendEmail(@RequestBody JSONObject jsonObject) {
        String email = jsonObject.getString("email");
        data = mailService.sendMimeMail(email);
        String captcha = data.getString("code");

        Captcha cap = new Captcha();
        cap.setEmail(email);
        cap.setCaptcha(captcha);
        captchaService.addCaptcha(cap);
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
