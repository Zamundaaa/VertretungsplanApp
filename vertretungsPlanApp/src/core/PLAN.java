package core;
import java.util.Calendar;

public class PLAN
{
    public STUNDE Stunde[][];
    Calendar Montagsdatum;
    Calendar Aktualisierungsdatum;
    Calendar Gueltigkeitsende;
   
    public PLAN()
    {
       Stunde = new STUNDE[10][11];
    }

    
    public Calendar GebeMontagsdatum()
    { return Montagsdatum;}
    
    public Calendar GebeAktualisierungsdatum()
    { return Aktualisierungsdatum;}
    
    public Calendar GebeGueltigkeitsende()
    { return Gueltigkeitsende;}
    
    public void SetzeMontagsdatum(Calendar neuesMontagsdatum)
    { Montagsdatum = neuesMontagsdatum;
    }
    
    public void AktualisierungsdatumSetzen(Calendar neuesAktualisierungsdatum)
    { Aktualisierungsdatum = neuesAktualisierungsdatum;
    }
    
    public void GueltigkeitsendeSetzen(Calendar neuesGueltigkeitsende)
    { Gueltigkeitsende= neuesGueltigkeitsende;
    }
        
      
}
