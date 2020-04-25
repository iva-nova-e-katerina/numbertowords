/*
Copyright (c) 2015 Ivanova A. Ekaterina (iceja.net). All rights reserved.
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/


package wordsswitcher;

import java.util.Locale;
import java.util.ResourceBundle;
import static java.util.ResourceBundle.getBundle;

/**
 *
 * @author 2015 Ekaterina Ivanova
 * date 15 October 2015
 */
public class Settings {

    public ResourceBundle messages;
    private final Locale currentLocale;

    private Settings() {
        currentLocale = new Locale("en", "US");
        readWords(currentLocale);
    }

    private void readWords(Locale currentLocale1) {
        messages = getBundle("numbers", currentLocale1);
    }

    /*
     each call can redifine locale this will involes reloading data of
     specified locale. Null parameter will return current data
     */
    public ResourceBundle getMessages(Locale locale) {
        if (locale == null) {
            return messages;
        } else if (locale.equals(currentLocale)) {
            readWords(locale);
        }

        return messages;
    }

    public static Settings getInstance() {
        Settings tmp = inst;
        if (null == tmp) {
            synchronized (Settings.class) {

                if (inst != null) {
                    tmp = inst;
                } else {
                    tmp = new Settings();
                    inst = tmp;
                }
            }
        }
        return tmp;
    }
    private static volatile Settings inst;
   
}
