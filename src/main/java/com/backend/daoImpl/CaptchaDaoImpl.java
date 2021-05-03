package com.backend.daoImpl;

import com.backend.dao.CaptchaDao;
import com.backend.entity.Captcha;
import com.backend.repository.jpa.CaptchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CaptchaDaoImpl implements CaptchaDao {
    @Autowired
    private CaptchaRepository captchaRepository;

    @Override
    public Captcha findByEmail(String email) {
        return captchaRepository.findByEmail(email);
    }

    @Override
    public Captcha addCaptcha(Captcha captcha) {
        return captchaRepository.saveAndFlush(captcha);
    }
}
