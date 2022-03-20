package DogPackage;

import java.util.Arrays;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message; 

import io.jsonwebtoken.io.IOException;

import static spark.Spark.post;

public class Dog {
	public static void main(String[] args) {
		SendSms.SendSms("Aww, I was at 2 where the couch was. Let's try again");
		sleep(20000);
		SendSms.SendSms("Type a number 1-3 and begin.(1 = Sofa, 2 = Couch, 3 = Rocking Chair");
		sleep(10000);
		SendSms.SendSms("Yay I won! The sofa is where I went because there was a 63% chance you'd do that.");
		feedDog();
		sleep(1000);
		playWithDog();
		sleep(1000);
		potty();
	}
	final static String s = "ok";
	//feed
	public static void feedDog() {
		SendSms.SendSms("Please feed me, I'm hungry");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To feed, type \"feed\"");
	}
	public static void feedDogSad() {
		SendSms.SendSms("Please feed me, I'm hungry and extremely sad");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To feed, type \"feed\"");
	}
	public static void feedDogHappy() {
		SendSms.SendSms("Woof woof, come feed me!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To feed, type \"feed\"");
	}

	//play
	public static void playWithDog() {
		SendSms.SendSms("Come play with me!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To begin playing with fAIdo, type \"ok\"");
	}
	public static void playWithDogSad() {
		SendSms.SendSms("*Whimper* Come play with me...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To begin playing with fAIdo, type \"ok\"");
	}
	public static void playWithDogHappy() {
		SendSms.SendSms("I'm super excited to play with you today!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To begin playing with fAIdo, type \"ok\"");
	}
	
	//potty
	public static void potty() {
		SendSms.SendSms("I need to use the restroom. Can you please let me go outside?");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To let fAIdo out, type \"potty\"");
	}
	public static void sadPotty() {
		SendSms.SendSms("Hey... I know you don't pay much attention to me but I was hoping you'd atleast let me out.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To let fAIdo out, type \"potty\"");
	}
	public static void happyPotty() {
		SendSms.SendSms("Can I go outside to potty and then maybe we can play a game?");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("To let fAIdo out, type \"potty\"");
	}
	
	public static void pottyAction() {
		SendSms.SendSms("I feel so much more relieved.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("You have received another heart for letting fAIdo out.");
	}
	//receive texts
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String body = request.getParameter("Body");
	}
	//converting received text to lower case
	public static String Text(String s) {
		return s.toLowerCase();
	}
	//seeing if the text sent back falls under "feed" or "ok" keywords
	public static void decision() {
		String x = Text(s);
		boolean b = false;
		while(b=false) {
			if(x.equals("ok") || x.equals("feed") || x.equals("potty")) {
				if(x.equals("ok")) {
					laserGame();
					b=true;
				}
				else if(x.equals("potty")) {
					pottyAction();
					b=true;
				}
				else {
					feed();
					b=true;
				}
			}
			else {
				SendSms.SendSms("Incorrect text, please try again.");
			}
		}
	}
	public static void sleep(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//laserGame()
	public static void laserGame() {
		SendSms.SendSms("The way this game works is that both of us will try to guess what seat I will run and sit down in");
		sleep(1000);
		SendSms.SendSms("However, because I am an AI dog, I will eventually learn where you are more likely to guess.");
		Random rand = new Random();
		int arr = rand.nextInt(2)+1;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SendSms.SendSms("Type a number 1-3 and begin.(1 = Sofa, 2 = Couch, 3 = Rocking Chair");
		post("/sms", (req, res) -> {
            res.type("application/xml");
            Body body = new Body
                    .Builder("Congrats! The sofa is where I went because there was an 80% chance you'd do that.")
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        });
		
		
	}
	//feed()
	public static void feed() {
		post("/sms", (req, res) -> {
            res.type("application/xml");
            Body body = new Body
                    .Builder("Thank you for feeding me! Here is an extra heart as a reward.")
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        });
	}
}
