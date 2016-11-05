package player.utilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtilities
{
    public static String getCookieValue(HttpServletRequest request, String cookieName, String valueIfNull)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie: cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return(cookie.getValue());
                }
            }
        }
        return(valueIfNull);
    }
}
