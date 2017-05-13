package eventManager;

/**
 * Created by shayan on 3/14/16.
 */
public interface EventListener {
    void receivedMessage(int id, Object... message);


}
