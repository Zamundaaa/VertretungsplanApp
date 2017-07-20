package core;
import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.Date;

public abstract class PlanParser{
    
    /**
     * Speichert alle Vertretungen auf dem Vertretungsplan, die vorhanden sind, und return dst
     */
    public static PlanData parse(String klasse, PlanData dst){
        Calendar c = datum(0);
        if(dst == null){
            dst = new PlanData();
        }
        for(int i = 0; i < 7; i++){
            parse(klasse, dayOfWeek(c), dst);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dst;
    }
    
    /**
     * Speichert alle Vertretungen am nächsten Tag, der ein "wochentag" ist, in dst und gibt dieses zurück; 
     * falls der angegebene Wochentag nicht im Vertretungsplan steht, wird null zurückgegeben
     */
    public static PlanData parse(String klasse, String wochentag, PlanData dst){
        Calendar c = datum(wochentag);
        String datum = dateToParse(c);
        
        String data = Tools.readFile("Vertretungsplan Quellcode.txt");// sollte durch den Download der Website ersetzt werden!!!
        
        String[] ka = data.split(datum);
        if(ka.length < 2){
            return null;
        }
        if(dst == null){
            dst = new PlanData();
        }
        String[] ka2 = ka[1].split("\n");
        int i = 0;
        String s = ka2[0].substring(5);
        //System.out.println(s.substring(0, s.length()-5));
        //if(dst.zuletztAktualisiert() == null){
            dst.setZuletztAktualisiert(s.substring(17, s.length()-5));
        //}
        for(; i < ka2.length && !ka2[i].startsWith(klasse); i++) {
        
        }
        if(i >= ka2.length){
            return dst;
        }
        //System.out.println(ka2[i].substring(0, ka2[i].length()-5));
        i += 2;
        s = removeAllAndParseMessage(ka2[i]);
        while(!s.startsWith("10") && !s.startsWith("9") && !s.startsWith("8") && !s.startsWith("7")
            && !s.startsWith("6") && !s.startsWith("5") && !s.startsWith("Q") && !s.startsWith("--")){
            //System.out.println("Lehrer: " + s);
            i += 2;
            String stunde = removeAllAndParseMessage(ka2[i]);
            //System.out.println("Stunde: " + stunde);
            i += 2;
            String vertretungsLehrer = removeAllAndParseMessage(ka2[i]);
            //System.out.println("Vertretung: " + vertretungsLehrer);
            i += 2;
            String raum = removeAllAndParseMessage(ka2[i]);
            //System.out.println("Raum: " + raum);
            i += 2;
            String message = removeAllAndParseMessage(ka2[i]);
            //System.out.println("Message: " + message);
            i++;
            dst.add(c, new Vertretung(s, stunde, vertretungsLehrer, message, raum));
            i += 2;
            s = removeAllAndParseMessage(ka2[i]);
        }
        dst.sortByHour();
        boolean noextraMessage = false;
        while(!ka2[i].startsWith("<p class=\"seite\" style=\"text-align:left\">")){
            i++;
            if(ka2[i].contains("Vertretungsplan für")){
                noextraMessage = true;
                break;
            }
        
        }
        
        if(noextraMessage){
            return dst;
        }
        
        i += 2;
        
        while(!ka2[i].startsWith("<")){
            dst.addZusatzInfoFürTag(c, removeAllAndParseMessage(ka2[i]));
            i += 2;
        }
        
        //while(...) !!!
        //    dst.addZusatzInfoFürTag(c, removeAllAndParseMessage(ka2[i]));
        // in extra Methode! Weil sonst wird das immer wieder neu reingeschrieben!!!
        
        //<p class="seite" style="text-align:left">
        //<table class="F"><colgroup><col width="899"/></colgroup> <tr class="F"><th rowspan="1" class="F">
        return dst;
    }
    
    public static String dayOfWeek(Calendar c){
        int tag = c.get(Calendar.DAY_OF_WEEK);
        tag -= 2;
        tag %= 7;
        String t;
        switch(tag){
            case 0: t = "Montag"; break;
            case 1: t = "Dienstag"; break;
            case 2: t = "Mittwoch"; break;
            case 3: t = "Donnerstag"; break;
            case 4: t = "Freitag"; break;
            case 5: t = "Samstag"; break;
            default: t = "Sonntag"; break;
        }
        return t;
    }
    
    public static Calendar datum(int tagVonHeute){
        Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, tagVonHeute);
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.HOUR);
        return cal;
        //int tag = cal.get(Calendar.DAY_OF_WEEK);
        //tag -= 2;
        //tag += tagVonHeute;
        //tag %= 7;
        //String t;
        //switch(tag){
        //    case 0: t = "Montag"; break;
        //    case 1: t = "Dienstag"; break;
        //    case 2: t = "Mittwoch"; break;
        //    case 3: t = "Donnerstag"; break;
        //    case 4: t = "Freitag"; break;
        //    case 5: t = "Samstag"; break;
        //    default: t = "Sonntag"; break;
        //}
        //return t + ", " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.YEAR);
    }
    
    private static String dateToParse(Calendar cal){
        int tag = cal.get(Calendar.DAY_OF_WEEK);
        tag -= 2;
        tag %= 7;
        String t;
        switch(tag){
            case 0: t = "Montag"; break;
            case 1: t = "Dienstag"; break;
            case 2: t = "Mittwoch"; break;
            case 3: t = "Donnerstag"; break;
            case 4: t = "Freitag"; break;
            case 5: t = "Samstag"; break;
            default: t = "Sonntag"; break;
        }
        return t + ", " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.YEAR);
    }
    
    public static Calendar datum(String wochentag){
        Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int tag = cal.get(Calendar.DAY_OF_WEEK);
        tag -= 2;
        tag %= 7;
        String t;
        switch(tag){
            case 0: t = "Montag"; break;
            case 1: t = "Dienstag"; break;
            case 2: t = "Mittwoch"; break;
            case 3: t = "Donnerstag"; break;
            case 4: t = "Freitag"; break;
            case 5: t = "Samstag"; break;
            default: t = "Sonntag"; break;
        }
        int i;
        for(i = 0; i < 7 && !wochentag.equals(t); i++){
            tag++;
            tag %= 7;
            switch(tag){
                case 0: t = "Montag"; break;
                case 1: t = "Dienstag"; break;
                case 2: t = "Mittwoch"; break;
                case 3: t = "Donnerstag"; break;
                case 4: t = "Freitag"; break;
                case 5: t = "Samstag"; break;
                default: t = "Sonntag"; break;
            }
        }
        cal.add(Calendar.DAY_OF_MONTH, i);
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.HOUR);
        //return t + ", " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.YEAR);
        return cal;
    }
    
    private static String removeAllAndParseMessage(String s){
        String buff;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '<'){
                for(int x = 0; x < s.length(); x++){
                    if(s.charAt(x) == '>'){
                        buff =  s.substring(0, i) + s.substring(x+1, s.length());
                        s = buff;
                        i--;
                        break;
                    }
                }
            }
        }
        s = s.replace("&nbsp;", "");
        s = s.replace("&auml;", "ae");
        s = s.replace("&uuml;", "ue");
        s = s.replace("&ouml;", "oe");
        
        s = s.replace("&Auml;", "Ae");
        s = s.replace("&Uuml;", "Ue");
        s = s.replace("&Ouml;", "Oe");
        
        //String[] ka = s.split("&auml;");
        //StringBuilder ret = new StringBuilder();
        //for(int i = 0; i < ka.length-1; i++){
        //    ret.append(ka[i]);
        //    ret.append("ae");
        //}
        //ret.append(ka[ka.length-1]);
        //buff = ret.toString();
        //ka = buff.split("&nbsp;");
        //ret = new StringBuilder();
        //for(int i = 0; i < ka.length; i++){
        //    ret.append(ka[i]);
        //}
        //buff = ret.toString();
        //ret = new StringBuilder();
        //ka = buff.split("&uuml;");
        //for(int i = 0; i < ka.length-1; i++){
        //    ret.append(ka[i]);
        //    ret.append("ue");
        //}
        //ret.append(ka[ka.length-1]);
        //buff = ret.toString();
        //ret = new StringBuilder();
        //ka = buff.split("&ouml;");
        //for(int i = 0; i < ka.length-1; i++){
        //    ret.append(ka[i]);
        //    ret.append("oe");
        //}
        //ret.append(ka[ka.length-1]);
        return s;
    }
    
    public static String downloadWebsiteCode(String site){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        StringBuilder ret = new StringBuilder();
    
        try {
            url = new URL(site);
            System.out.println(url.toString());
            is = url.openStream();  // throws an IOException
            System.out.println("connected!");
            br = new BufferedReader(new InputStreamReader(is));
    
            while ((line = br.readLine()) != null) {
                ret.append(line);
            }
        } catch (MalformedURLException mue) {
             mue.printStackTrace();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return ret.toString();
    }
    
    public static void main(String[] args){
    	System.setProperty("jsse.enableSNIExtension", "false");
    	System.out.println(executePost("http://descartes-gym.de/index.php", "username=descartes&password=2016CoGiTo&Submit=Anmelden&option=com_users&task=user.login&return=aW5kZXgucGhwP0l0ZW1pZD0xMzY%3D&fa84812824021076f55538647567b276=1"));
    }
    
    public static String executePost(String targetURL, String urlParameters) {
    	  HttpURLConnection connection = null;

    	  try {
    	    //Create connection
    	    URL url = new URL(targetURL);
    	    connection = (HttpURLConnection) url.openConnection();
    	    connection.setRequestMethod("POST");
    	    connection.setRequestProperty("Content-Type", 
    	        "application/x-www-form-urlencoded");

    	    connection.setRequestProperty("Content-Length", 
    	        Integer.toString(urlParameters.getBytes().length));
    	    connection.setRequestProperty("Content-Language", "en-US");  

    	    connection.setUseCaches(false);
    	    connection.setDoOutput(true);

    	    //Send request
    	    DataOutputStream wr = new DataOutputStream (
    	        connection.getOutputStream());
    	    wr.writeBytes(urlParameters);
    	    wr.close();

    	    //Get Response  
    	    InputStream is = connection.getInputStream();
    	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    	    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
    	    String line;
    	    while ((line = rd.readLine()) != null) {
    	      response.append(line);
    	      response.append('\r');
    	    }
    	    rd.close();
    	    return response.toString();
    	  } catch (Exception e) {
    	    e.printStackTrace();
    	    return null;
    	  } finally {
    	    if (connection != null) {
    	      connection.disconnect();
    	    }
    	  }
    	}
    
}