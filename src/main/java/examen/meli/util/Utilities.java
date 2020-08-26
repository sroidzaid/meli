package examen.meli.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Utilities {

    private static final double Latitud_Bs_As= -34.6083;
    private static final double Longitud_Bs_As= -58.3712;

    private static final String ipv4Pattern = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";


    public static boolean isIpAdress(String ip) {
        if (ip == null || ip.isEmpty()) return false;
        ip = ip.trim();
        if ((ip.length() < 6) & (ip.length() > 15)) return false;

        try {
            Pattern pattern = Pattern.compile(ipv4Pattern);
            Matcher matcher = pattern.matcher(ip);
            if(matcher.matches()){
                return true;
            }else{
                Pattern pattern6 = Pattern.compile(ipv6Pattern);
                Matcher matcher6 = pattern6.matcher(ip);
                return matcher6.matches();
            }

        } catch (PatternSyntaxException ex) {
            return false;
        }
    }

    public static double distanceFromBsAs(double lat2, double lon2) {
        double theta = Longitud_Bs_As - lon2;
        double dist = Math.sin(deg2rad(Latitud_Bs_As)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(Latitud_Bs_As)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return (dist);
    }

    //Esta función convierte grados decimales a radianes
    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    //Esta función convierte  radianes a grados decimales
    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
