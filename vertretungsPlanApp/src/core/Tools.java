package core;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Tools{
    
    //public static final String ordner;
    //static{
    //    ordner = System.getProperty("user.dir") + "/project0/";
    //    File f = new File(ordner);
    //    if(!f.exists())
    //        f.mkdirs();
    //}
    
    public static void createEmptyFile(String file){
        try{
            File f = new File(file);
            if(!f.exists()){
                if(!f.getParentFile().exists())
                    f.getParentFile().mkdirs();
                f.createNewFile();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static String readFile(String file){
        try{
            BufferedReader r = new BufferedReader(new FileReader(new File(file)));
            StringBuilder b = new StringBuilder();
            String line;
            while((line = r.readLine()) != null){
                b.append(line);
                b.append("\n");
            }
            r.close();
            return b.toString();
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static void writeFile(String file, String content){
        File f = new File(file);
        try{
            if(!f.exists()){
                if(!f.getParentFile().exists())
                    f.getParentFile().mkdirs();
                f.createNewFile();
            }
            BufferedWriter w = new BufferedWriter(new FileWriter(f));
            w.write(content);
            w.close();
        }catch(IOException e){
            System.err.println(f.getAbsolutePath());
            e.printStackTrace();
        }
    }
    
    public static void delete(String fileOrDirectory){
        delete(new File(fileOrDirectory));
    }
    
    public static void delete(File f){
        File[] files = f.listFiles();
            if(files != null)
        for(int i = 0; i < files.length; i++){
            delete(files[i]);
        }
        f.delete();
    }
    
    public static ImageIcon getImageIcon(String relativePathInRes){
        //try{
        //    ArrayListB list = new ArrayListB();
        //    BufferedReader in = new BufferedReader(new InputStreamReader(Tools.class.getClassLoader().getResourceAsStream(relativePath)));
        //    int i;
        //    while((i = in.read()) != -1){
        //        list.add((byte)i);
        //    }
        //    in.close();
        //    return new ImageIcon(list.capToArray(), relativePath);
        //}catch(Exception e){
        //    e.printStackTrace();
        //    return null;
        //}
        return new ImageIcon(getImage(relativePathInRes));
    }
    
    public static BufferedImage getImage(String relativePathInRes){
        try{
            return ImageIO.read(Tools.class.getClassLoader().getResourceAsStream("res/" + relativePathInRes));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}