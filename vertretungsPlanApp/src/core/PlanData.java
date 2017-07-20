package core;

import java.util.*;

/**
*  Enthält alle Daten über die Vertretungen
**/
public class PlanData{
    
    private Map<Calendar, ArrayList<Vertretung>> data = new HashMap<Calendar, ArrayList<Vertretung>>();
    private String zuletztAktualisiert = null;
    private Map<Calendar, ArrayList<String>> zusatzInfosFürTag = new HashMap<Calendar, ArrayList<String>>();
    
    public PlanData(){
        
    }
    
    public void setZuletztAktualisiert(String d){
        zuletztAktualisiert = d;
    }
    
    public String zuletztAktualisiert(){
        return zuletztAktualisiert;
    }
    
    protected void addZusatzInfoFürTag(Calendar c, String info){
        ArrayList<String> infos = zusatzInfosFürTag.get(c);
        if(infos == null){
            infos = new ArrayList<String>();
            zusatzInfosFürTag.put(c, infos);
        }
        infos.add(info);
    }
    
    public ArrayList<String> zusatzInfosFürTag(Calendar c){
        return zusatzInfosFürTag.get(c);
    }
    
    protected void add(Calendar d, Vertretung v){
        ArrayList<Vertretung> vs = data.get(d);
        if(vs == null){
            vs = new ArrayList<>();
            data.put(d, vs);
        }
        vs.add(v);
    }
    
    public void printout(){
        Set<Calendar> set = data.keySet();
        for(Calendar s : set){
            for(Vertretung al : data.get(s)){
                System.out.println(al.toString());
            }
        }
    }
    
    public ArrayList<Vertretung> get(Calendar tag, int stunde, ArrayList<Vertretung> list){
        ArrayList<Vertretung> g = data.get(tag);
        if(g == null){
            System.out.println("Found nothing!");
            return list;
        }else{
            if(list == null)
                list = new ArrayList<Vertretung>();
            for(int i = 0; i < g.size(); i++)
                if(g.get(i).stunde() == stunde){
                    list.add(g.get(i));
                }
            return list;
        }
    }
    
    public void sortByHour(){
        Set<Calendar> set = data.keySet();
        for(Calendar s : set){
            sortByHour(data.get(s));
        }
    }
    
    private void sortByHour(ArrayList<Vertretung> v){
        boolean foundSomeThing = true;
        while(foundSomeThing){
            foundSomeThing = false;
            for(int i = 0; i < v.size()-1; i++){
                if(v.get(i).stunde() > v.get(i+1).stunde()){
                    Vertretung buffer = v.get(i);
                    v.set(i, v.get(i+1));
                    v.set(i+1, buffer);
                    foundSomeThing = true;
                }
            }
        }
    }
    
    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        Set<Calendar> set = data.keySet();
        for(Calendar s : set){
            for(Vertretung al : data.get(s)){
                ret.append(al.toString());
            }
        }
        return ret.toString();
    }
    
}