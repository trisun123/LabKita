package medis.ourlab.labkita.app;

/**
 * Created by tri on 10/3/2016.
 */

public class EndPoints {

    // localhost url -
    public static final String BASE_URL = "http://209.159.145.187/ourlab/v1";
    //public static final String BASE_URL = "http://192.168.43.145/ourlab/v1";
    public static final String LOGIN = BASE_URL + "/user/login";
    public static final String USER = BASE_URL + "/user/_ID_";
    public static final String CHAT_ROOMS = BASE_URL + "/chat_rooms";
    public static final String CHAT_THREAD = BASE_URL + "/chat_rooms/_ID_";
    public static final String CHAT_ROOM_MESSAGE = BASE_URL + "/chat_rooms/_ID_/message";
}