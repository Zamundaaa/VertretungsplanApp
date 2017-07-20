package core;

import gui.GUI;

public class SYSTEM
{
   public String Nutzername;
   private String Passwort;
   public PLAN aktuellerPlan;
   private GUI gui;
   
   
  public SYSTEM()
   { Nutzername="Descartes";
      Passwort="2016CoGiTo";
      gui = new GUI();
     
   }

  void NutzernameÄndern(String neuerNutzername)
  { Nutzername= neuerNutzername;
   }
    
  void PlanÄndern(PLAN neuerPlan)
  {aktuellerPlan= neuerPlan;}
   
  void PasswortÄndern(String neuesPasswort)
   { Passwort = neuesPasswort;
   }
    
  boolean Anmeldung(String eingegebenerNutzername, String eingegebenesPasswort)
    { if(Nutzername!=null)
       
       {if(eingegebenerNutzername.equals(Nutzername) && (eingegebenesPasswort.equals(Passwort) ))
        { 
         gui.BenutzerAnmelden();
         return true;
        }
        else
        { return false;}
        
        
    }
    else
    { gui.BenutzerAnlegen();
      return false;
    }
  }
    
  public PLAN PlanGeben()
   { return aktuellerPlan;
   }
    
    
  void aktualisieren()
  { PlanEinfuegen(gui.NeuenPlanGeben());
    }
 
  void neuenBenutzerAnlegen(String neuerBenutzername, String neuesPasswort)
  { Nutzername=neuerBenutzername;
    Passwort= neuesPasswort;
  }
 
  void BenutzerLoeschen()
  {Nutzername=null;
   Passwort=null;
  }
     

  void PlanEinfuegen(PLAN neuerPlan)
  {aktuellerPlan=neuerPlan;
   gui.PlanAnzeigen(aktuellerPlan);
  }

  void PlanErstellt()
  {gui.PlanErstellen();}


  void ProgrammBeenden()
 {}


  
}
