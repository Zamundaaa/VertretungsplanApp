package core;
import java.util.Calendar;

public class STUNDE
{
    String Kurs;
    String Fach;
    String Lehrer;
    String Raum;
    String HA;
    String Buch;
    String Notizen;
    Calendar SATermin;
    int year;
    int month;
    int date;
    int Farbnummer;
    
    public STUNDE()
    {
        
    }
    
    public void KursEinfuegen(String neuerKurs)
    {
        Kurs = neuerKurs;
    }
    public String GebeKurs()
    {
        return Kurs;
    }
    
    public void FachEinfuegen(String neuesFach)
    {
        Fach = neuesFach;
    }
    public String GebeFach()
    {
        return Fach;
    }
    
    public void LehrerEinfuegen(String neuerLehrer)
    {
        Lehrer = neuerLehrer;
    }
    public String GebeLehrer()
    {
        return Lehrer;
    }
    
    public void RaumEinfuegen(String neuerRaum)
    {
        Raum = neuerRaum;
    }
    public String GebeRaum()
    {
        return Raum;
    }

    public void HAEinfuegen(String neueHA)
    {
        HA = neueHA;
    }
    public String GebeHA()
    {
        return HA;
    }
    
    public void BuchEinfuegen(String neuesBuch)
    {
        Buch = neuesBuch;
    }
    public String GebeBuch()
    {
        return Buch;
    }
    
    public void NotizenEinfuegen(String neueNotizen)
    {
        Notizen = neueNotizen;
    }
    public String GebeNotizen()
    {
        return Notizen;
    }
    
    public void SATerminEinfuegen(Calendar neueSA)
    {
        SATermin = neueSA;
    }
    Calendar GebeSATermin()
    {
        return SATermin;
    }
    public void set(int year, int month, int date)
    {
        
    }
    
    public void FarbnummerEinfuegen(int neueFarbnummer)
    {
        Farbnummer = neueFarbnummer;
    }
    public int GebeFarbnummer()
    {
        return Farbnummer;
    }
}
