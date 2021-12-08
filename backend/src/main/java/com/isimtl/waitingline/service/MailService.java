package com.isimtl.waitingline.service;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${VERIFICATION_URL}")
    private String verificationURL;

    @Value("${SENDGRID_API_KEY}")
    private String sendgridKey;

    @Value("${FROM_EMAIL}")
    private String emailFrom;

    @Value("${TEMPLATE_ID}")
    private String templateId;

    public String sendTextEmail(String otp, String email,String userData) throws IOException {
            // the sender email should be the same as we used to Create a Single Sender Verification
            Email from = new Email(emailFrom);
            Email to = new Email(email);
            Mail mail = new Mail();
            // we create an object of our static class feel free to change the class on it's own file
            // I try to keep every think simple
            DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
            personalization.addTo(to);
            mail.setFrom(from);
            mail.setSubject("Login Verification");
            // This is the first_name variable that we created on the template
        personalization.addDynamicTemplateData("VERIFICATION_CODE", otp);
        personalization.addDynamicTemplateData("VERIFICATION_URL", verificationURL+userData);
            mail.addPersonalization(personalization);
            mail.setTemplateId(templateId);
            // this is the api key
            SendGrid sg = new SendGrid(sendgridKey);
            Request request = new Request();

            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                logger.info(response.getBody());
                return response.getBody();
            } catch (IOException ex) {
                throw ex;
            }
        }

        // This class handels the dynamic data for the template
        // Feel free to customise this class our to putted on other file
        private static class DynamicTemplatePersonalization extends Personalization {

            @JsonProperty(value = "dynamic_template_data")
            private Map<String, String> dynamic_template_data;

            @JsonProperty("dynamic_template_data")
            public Map<String, String> getTemplateData() {
                if (dynamic_template_data == null) {
                    return Collections.<String, String>emptyMap();
                }
                return dynamic_template_data;
            }

            public void addDynamicTemplateData(String key, String value) {
                if (dynamic_template_data == null) {
                    dynamic_template_data = new HashMap<String, String>();
                    dynamic_template_data.put(key, value);
                } else {
                    dynamic_template_data.put(key, value);
                }
            }

        }
}