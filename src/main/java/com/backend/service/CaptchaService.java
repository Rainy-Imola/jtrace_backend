package com.backend.service;

import com.backend.entity.Captcha;

public interface CaptchaService {
    Captcha findByEmail(String email);
    Captcha addCaptcha(Captcha captcha);
}
