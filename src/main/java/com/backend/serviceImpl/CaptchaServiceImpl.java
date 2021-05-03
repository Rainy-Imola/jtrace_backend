package com.backend.serviceImpl;

import com.backend.dao.CaptchaDao;
import com.backend.entity.Captcha;
import com.backend.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private CaptchaDao captchaDao;

    @Override
    public Captcha findByEmail(String email) {
        return captchaDao.findByEmail(email);
    }

    @Override
    public Captcha addCaptcha(Captcha captcha) {
        return captchaDao.addCaptcha(captcha);
    }
}
