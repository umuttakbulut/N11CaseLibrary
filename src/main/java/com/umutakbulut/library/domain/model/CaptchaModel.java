package com.umutakbulut.library.domain.model;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class CaptchaModel {

    private String imageWord;
    private String ipAddress;
    private String answer;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getImageWord() {
        return imageWord;
    }

    public void setImageWord(String imageWord) {
        this.imageWord = imageWord;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
