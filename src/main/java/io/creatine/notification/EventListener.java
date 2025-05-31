package io.creatine.notification;

import io.creatine.account.domain.event.AccountCreated;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    @Value("${spring.mail.username}")
    private String fromEmail;
    private final JavaMailSender mailSender;

    @ApplicationModuleListener
    public void on(AccountCreated event) throws MessagingException {
        var mimeMessage = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(event.email());
        messageHelper.setSubject("Verify Your Email Address - " + event.username());


    }

    private String emailVerificationTemplate(String username, String verificationCode, String email) {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Email Verification</title>
                <style>
                    body { 
                        font-family: Arial, sans-serif; 
                        line-height: 1.6; 
                        color: #333; 
                        max-width: 600px; 
                        margin: 0 auto; 
                        padding: 20px; 
                    }
                    .header { 
                        background-color: #4285f4; 
                        color: white; 
                        padding: 20px; 
                        text-align: center; 
                        border-radius: 8px 8px 0 0; 
                    }
                    .content { 
                        background-color: #f9f9f9; 
                        padding: 30px; 
                        border-radius: 0 0 8px 8px; 
                    }
                    .verification-code { 
                        background-color: #fff; 
                        border: 2px solid #4285f4; 
                        border-radius: 8px; 
                        padding: 20px; 
                        text-align: center; 
                        margin: 25px 0; 
                    }
                    .code { 
                        font-size: 36px; 
                        font-weight: bold; 
                        color: #4285f4; 
                        letter-spacing: 8px; 
                    }
                    .warning { 
                        background-color: #fff3cd; 
                        border: 1px solid #ffeaa7; 
                        border-radius: 4px; 
                        padding: 15px; 
                        margin: 20px 0; 
                    }
                    .footer { 
                        margin-top: 30px; 
                        padding-top: 20px; 
                        border-top: 1px solid #ddd; 
                        font-size: 14px; 
                        color: #666; 
                    }
                    .button { 
                        display: inline-block; 
                        background-color: #4285f4; 
                        color: white; 
                        padding: 12px 30px; 
                        text-decoration: none; 
                        border-radius: 5px; 
                        margin: 15px 0; 
                    }
                </style>
            </head>
            <body>
                <div class="header">
                    <h1>%s</h1>
                    <h2>Email Verification</h2>
                </div>
                <div class="content">
                    <p>Hello <strong>%s</strong>,</p>
                    <p>Thank you for signing up with %s! To complete your registration and verify your email address, please use the verification code below:</p>
                    
                    <div class="verification-code">
                        <p><strong>Your Verification Code:</strong></p>
                        <div class="code">%s</div>
                        <p><em>This code will expire in 10 minutes</em></p>
                    </div>
                    
                    <p><strong>How to use this code:</strong></p>
                    <ol>
                        <li>Go back to the verification page in your browser</li>
                        <li>Enter this 6-digit code: <strong>%s</strong></li>
                        <li>Click 'Verify Email' to complete the process</li>
                    </ol>
                    
                    <div class="warning">
                        <p><strong>⚠️ Security Notice:</strong></p>
                        <ul>
                            <li>This code is valid for 10 minutes only</li>
                            <li>Do not share this code with anyone</li>
                            <li>If you didn't request this verification, please ignore this email</li>
                        </ul>
                    </div>
                    
                    <p>If you're having trouble with verification, you can:</p>
                    <ul>
                        <li>Request a new verification code</li>
                        <li>Contact our support team at <a href="mailto:%s">%s</a></li>
                    </ul>
                    
                    <div class="footer">
                        <p><strong>Account Information:</strong></p>
                        <p>Email: %s</p>
                        <p>Verification requested: %s</p>
                        
                        <hr>
                        <p>Best regards,<br>The %s Team</p>
                        
                        <p style="font-size: 12px; color: #999;">
                            This is an automated message. Please do not reply to this email. 
                            If you need assistance, contact us at <a href="mailto:%s">%s</a>
                        </p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted();
    }

}
