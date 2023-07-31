package com.example.EcoSystemProject.Service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;



@Service
@Slf4j
public class TwilioService {
	
	@Value ("${ACCOUNTSID}")
	private String Accountsid;
	
	@Value ("${AUTHTOKEN}")
	private String Authtoken;
	
	@Value ("${FROMNUMBER}")
	private String Fromnumber;
	
	
	public void sendmsg(String toNumber,String messageBody) {
		
		Twilio.init(Accountsid, Authtoken);
		Message message=Message.creator(new PhoneNumber(toNumber), new PhoneNumber(Fromnumber), messageBody).create();
	
		log.info("message sid"+message.getSid());
	}
	

}
