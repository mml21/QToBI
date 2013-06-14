package PubSub;

import PubSub.Exceptions.InvalidCredentialsException;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mm111
 * Date: 26/01/12
 * Time: 11:44
 * To change this template use File | Settings | File Templates.
 */
public interface Client {

    void login(Credentials credentials) throws InvalidCredentialsException, IOException;
    public String getUsername();
}
