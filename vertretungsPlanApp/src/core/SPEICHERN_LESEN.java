package core;
import java.io.*;


public class SPEICHERN_LESEN
{    
    File file;
    
    
    public SPEICHERN_LESEN()
    {
      
    }
    
   
    public void SchreibeBenutzer(String Benutzer, String PW)
    {
        try
        {
            File Datei = new File(Benutzer);
            Datei.createNewFile();            
            try{
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(PW.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i)
                {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                }
            

            BufferedWriter Puffer = new BufferedWriter(new FileWriter(Datei)); 
            Puffer.write(sb.toString());
            Puffer.close();
            } catch (java.security.NoSuchAlgorithmException e) {}
        }
        catch(IOException e)
        {
            System.err.println("schreibfehler");
        }
    }
       
    public void SchreibeDaten(PLAN[] Plaene)
    {
        try
        {
            File Datei = new File("Plan.txt");
            Datei.createNewFile();
            BufferedWriter Puffer = new BufferedWriter(new FileWriter(Datei));
            
            
            
            Puffer.write(Plaene[0].GebeMontagsdatum().toString());
            //Puffer.write(Plaene.GebeMontagsdatum().toString() + "," + Plaene.GebeAktualisierungsdatum().toString() + ";" + Plaene.GebeGueltigkeitsende().toString);
            Puffer.close();
        }
        catch(IOException e)
        {
            System.err.println("schreibfehler");
        }
    }
    
    
       public void lesen(String Name)
    {
        try
        {
            File Datei = new File(Name);
            
            LineNumberReader Puffer = new LineNumberReader(new FileReader(Datei));
            String zeile = "";
            while((zeile = Puffer.readLine()) != null)
            {
                System.out.println(zeile);
            }
            
            Puffer.close();
        }
        catch(IOException e)
        {
            System.err.println("lesefehler");
        }
    }  
}
