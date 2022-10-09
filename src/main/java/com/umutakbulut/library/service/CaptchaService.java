package com.umutakbulut.library.service;

import com.umutakbulut.library.domain.model.CaptchaModel;
import net.util.recaptcha.ReCaptchaImpl;
import org.springframework.stereotype.Service;

@Service
public class CaptchaService {

    private final String GOOGLE_API_PRIVATE_KEY = "6LcSSP0SAAAAAK0J0l_-kIVKigwiiFcnFR4crITY";

    public boolean captchaIsValid(CaptchaModel captchaModel){
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey(GOOGLE_API_PRIVATE_KEY);
        return reCaptcha.checkAnswer(captchaModel.getIpAddress(), captchaModel.getImageWord(), captchaModel.getAnswer()).isValid();
    }

    public boolean captchaIsNotValid(CaptchaModel captchaModel) {
        return !captchaIsValid(captchaModel);
    }

}
