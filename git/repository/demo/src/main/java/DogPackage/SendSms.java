package DogPackage;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
public class SendSms {
	public static final String accountSid = "AC93f9ae5aefdbb51bb4901e01d86bd9aa";
	public static final String authToken = "d696ec1f6e048b56e6ac5db78feed4a7";
	
	
	public static void SendSms(String string) {
		Twilio.init(accountSid,authToken);
		PhoneNumber to = new PhoneNumber("4044572380");//in a realtime app, we will have a way to access a database with signed up numbers
		PhoneNumber from = new PhoneNumber("+12766249079");
		Message message = Message.creator(to, from, string).create();
		System.out.println(message.getSid());
	}
}
