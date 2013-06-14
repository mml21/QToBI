package PubSub;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMQCredentials implements Credentials {

    String userName;
    String passWord;

    public RabbitMQCredentials(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUsername() {
        return userName;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getPassword() {
        return passWord;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
