package core;


public class Vertretung{
    
    private String Lehrer, Stunde, Message, Raum, vertretungsLehrer;
    private int stunde;
    
    public Vertretung(String lehrer, String stunde, String vertretungsLehrer, String message, String raum){
        this.Lehrer = lehrer;
        this.Stunde = stunde;
        this.Message = message;
        this.Raum = raum;
        this.vertretungsLehrer = vertretungsLehrer;
        this.stunde = Integer.parseInt(Stunde);
    }
    
    public int stunde(){
        return stunde;
    }
    
    @Override
    public String toString(){
        return "Lehrer: " + Lehrer + " Stunde: " + Stunde + " VertretungsLehrer: " + vertretungsLehrer + " Raum: " + Raum + " Message: " + Message;
    }
    
}