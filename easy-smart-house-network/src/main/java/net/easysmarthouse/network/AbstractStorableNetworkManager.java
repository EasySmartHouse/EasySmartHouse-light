/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.network;

/**
 *
 * @author mirash
 */
public abstract class AbstractStorableNetworkManager extends AbstractNetworkManager implements NetworkManagerStorable {

    @Override
    public void setStorage(NetworkManagerStorage storage) {
        storage.add(this);
    }
}
