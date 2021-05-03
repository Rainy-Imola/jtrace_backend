package com.backend.serviceImpl;

import com.backend.service.MailService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public JSONObject sendMimeMail(String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("CAPTCHA");

            String code = randomCode();

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("email", email);
            jsonObject.put("code", code);

            mailMessage.setText("Your CAPTCHA is: " + code);
            mailMessage.setTo(email);
            mailMessage.setFrom(from);

            mailSender.send(mailMessage);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (Integer i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    @Override
    public boolean checkCode(JSONObject checkJson, JSONObject jsonObject) {
        String code = jsonObject.getString("code");
        String email = jsonObject.getString("email");

        if (email == null || !email.equals(checkJson.getString("email"))) {
            return false;
        }

        if (!code.equals(checkJson.getString("code"))) {
            return false;
        }

        return true;
    }
}
