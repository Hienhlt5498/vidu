package com.thucpham.shopweb.common;

import org.springframework.stereotype.Component;

@Component
public class SendEmail {

	public boolean sendToSenderEmailSendGrid(final String email, final String subject, final String message)
    {
        boolean sendState = false;
        try
        {

            Sendgrid mail = new Sendgrid("quydv98", "quycntt1998");

            mail.setTo(email)
                    .setFrom("dangvanquy200498@gmail.com")
                    .setSubject(subject)
                    .setText("")
                    .setHtml(message)
                    .send();
            System.out.println(mail.getServerResponse());
            if (mail.getServerResponse() == "success")
            {
                sendState = true;
            }
            else
            {
                sendState = false;
            }
        }
        catch (Exception e)
        {
        	sendState = false;
        }

        return sendState;
    }
	
//	public static void main(String[] args) {
//		SendEmail sendEmail = new SendEmail();
//		boolean check = sendEmail.sendToSenderEmailSendGrid("dangvanquy200498@gmail.com", "test", "test");
//		System.out.println(check);
//	}
}
