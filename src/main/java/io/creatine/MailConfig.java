package io.creatine;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

    private final MailProperties mailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        var properties = mailSender.getJavaMailProperties();

        mailSender.setHost(mailProperties.host);
        mailSender.setPort(mailProperties.port);
        mailSender.setUsername(mailProperties.username);
        mailSender.setPassword(mailProperties.password);
        mailSender.setProtocol(mailProperties.protocol);
        properties.put("mail.transport.protocol", mailProperties.protocol);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);

        return mailSender;
    }

    @Getter
    @Setter
    @Component
    @ConfigurationProperties(prefix = "spring.mail")
    private static class MailProperties {
        private String protocol;
        private String host;
        private int port;
        private String username;
        private String password;

    }
}
