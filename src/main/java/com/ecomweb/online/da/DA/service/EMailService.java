package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;

@Service
public class EMailService {

    /**
     * Simulates sending an e-mail by logging to the console.
     *
     * @param userId The ID of the user to whom the e-mail is sent.
     */
    public void sendEMail(int userId) {
        System.out.println("Simulated: Email sent to user with id: " + userId);
    }
}
