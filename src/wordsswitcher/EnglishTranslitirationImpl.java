/*
Copyright (c) 2015 Ivanova A. Ekaterina (iceja.net). All rights reserved.
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package wordsswitcher;

import static java.lang.String.valueOf;
import java.util.Locale;
import java.util.ResourceBundle;
import static wordsswitcher.Settings.getInstance;

/**
 *
 * @author 2015 Ekaterina Ivanova
 * date 15 October 2015
 */
public class EnglishTranslitirationImpl implements TranliterationFacade {

    private final Locale locale;
    private final ResourceBundle messages;
    //int millions, thousands, hundreds, tens, teen, ones;

    public EnglishTranslitirationImpl(Locale locale1) {
        this.locale = locale1;
        this.messages = getInstance().getMessages(locale);
    }

    @Override
    public String transliterate(Long input) {
        String result;
        if (input > 9_999 || input < 0) {
            return "out of range";
        } else if (input == 0) {
            return "zero";
        }
        int thousands = (int) (input / 1_000);
        result = parseThoudhands(thousands);
        int hundreds = (int) (input - (thousands * 1_000)) / 100;
        result = result.concat(parseHundreds(hundreds));
        int tens = (int) (input - thousands * 1_000 - hundreds * 100);
        result = result.concat(parseTens(tens));
        return result.trim();

    }

//    protected String parseMillions(int input) {
//        if(input == 0){
//            return "";
//        }
//        String result = "";
//        int millions = (int) (input / 1000);
//        int hundreds = (int) (input - (millions * 1000)) / 100;
//        result = result.concat(parseHundreds(hundreds));
//        int tens = (int) (input - millions * 1000 - hundreds * 100);
//        result = result.concat(parseTens(tens));
//        return result.concat(" million ");
//    }

    protected String parseThoudhands(int thnd) {
        if (thnd == 0) {
            return "";
        }
        return messages.getString(valueOf(thnd)) + " thousand ";
    }

    protected String parseHundreds(int thnd) {

        if (thnd == 0) {
            return "";
        } else {
            return messages.getString(String.valueOf(thnd)) + " hundred ";
        }

    }

    protected String parseTens(int thnd) {
        if (thnd == 0) {
            return "";
        } else if (thnd < 20) {
            return messages.getString(String.valueOf(thnd));
        }
        String result;
        int dec = (thnd/10) * 10;
        result =  messages.getString(String.valueOf(dec));
        int ones = thnd - dec;
        if(ones == 0)
            return result;
        else 
            result = result.concat("-").concat(messages.getString(String.valueOf(ones)));
        return result;
    }


}
