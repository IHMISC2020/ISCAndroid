package fr.iscgroup.isc.factory;

import android.content.Context;

import fr.iscgroup.isc.factory.connexion.ConnexionFactory;

/**
 * @author emoqu on 11/01/2021
 * @project ISC
 */
public abstract class Factory {

    /**
     * Gets dao factory.
     *
     * @return the dao factory
     */
    public static ConnexionFactory getConnexionFactory(Context context) {

        return ConnexionFactory.getInstance(context);
    }
}
