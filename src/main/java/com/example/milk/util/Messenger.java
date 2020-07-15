package com.example.milk.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class Messenger {

    private static final String ACCOUNT_SID = "ACf3113e7d0881b47a428c4b9d89efa597";

    private static final String AUTH_TOKEN = "e7a6841692544c58e17f67d384e6ce47";

    private static final String TWILIO_NUMBER = "+19143687285";

    private static boolean inited;

    private static void checkInited() throws Exception
    {
        if(!inited) throw new Exception("Twilio has been not inited!");
    }

    public static void init()
    {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        inited = true;
    }

    public static MessageCreator createMessageCreator(String recipientNumber, String message) throws Exception
    {
        init();
        checkInited();
        return Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber(TWILIO_NUMBER),
                message);
    }

    public static Message sendMessage(String recipientNumber, String message) throws Exception
    {
        return createMessageCreator(recipientNumber, message).create();
    }


}
