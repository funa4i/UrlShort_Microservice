package org.urlshort.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.urlshort.domain.data.CreateLinkInfo;
import org.urlshort.domain.data.ExpiredLinkInfo;

import java.text.Format;
import java.util.Formatter;

@Service
@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender javaMailSender;
    @Async
    public void sendExpiredLinkNotification(ExpiredLinkInfo expiredLinkInfo){
        var smm = new SimpleMailMessage();
        smm.setTo(expiredLinkInfo.getEmail());
        smm.setFrom("UrlShort service");
        smm.setSubject("Expired link");
        var formatter = new Formatter();
        var message = "Yout link <%s> to long <%s> is expired or the number of transitions is over";
        smm.setText(formatter.format(message, expiredLinkInfo.getShortUrl(), expiredLinkInfo.getFullUrl()).toString());
        javaMailSender.send(smm);
    }
    @Async
    public void sendCreateLinkInfoNotification(CreateLinkInfo createLinkInfo){
        var smm = new SimpleMailMessage();
        smm.setTo(createLinkInfo.getUserEmail());
        var formatter = new Formatter();
        var message = "There was created %d links on %t";
        smm.setText(formatter.format(message, createLinkInfo.getCreateAmount(), createLinkInfo.getDate()).toString());
        javaMailSender.send(smm);
    }
}
