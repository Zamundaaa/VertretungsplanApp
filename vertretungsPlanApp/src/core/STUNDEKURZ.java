package core;

import java.util.Calendar;

public class STUNDEKURZ
{
    Calendar Datum;
    STUNDE Stunde;
    String Lehrkraft;
    String Vertretung;
    String Raum;
    
    
   public STUNDEKURZ(Calendar DatumNeu, STUNDE StundeNeu)
    {
        Datum=DatumNeu;
        Stunde=StundeNeu;
    }

   public Calendar GebeDatum()
   { return Datum;}
   
   public STUNDE GebeStunde()
   { return Stunde;}
   
   public String GebeLehrkraft()
   { return Lehrkraft;}
   
   public String GebeVertretung()
   { return Vertretung;}
   
   public String GebeRaum()
   {return Raum;}
   
   void SetzeStunde(STUNDE neueStunde)
   { Stunde = neueStunde;}
   
   void SetzeLehrkraft(String neueLehrkraft)
   {Lehrkraft = neueLehrkraft;}
   
   void SetzeVertretung(String neueVertretung)
   {Vertretung = neueVertretung;}
   
   void SetzeRaum(String neuerRaum)
   { Raum = neuerRaum;}
}
