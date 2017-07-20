package HelperTools;


public class ArrayListB{
    
    private byte[] data;
    private int size;
    
    public ArrayListB(){
        this(10);
    }
    
    public ArrayListB(int startSize){
        data = new byte[startSize];
    }
    
    public ArrayListB(byte[] data){
        this.data = data;
        size = data.length;
    }
    
    public void add(byte b){
        ensureCapacity(size+1);
        data[size] = b;
        size++;
    }
    
    public void add(int i, byte b){
        size++;
        ensureCapacity(size);
        for(int i2 = size; i2 > i; i2--)
            data[i2] = data[i2-1];
        data[i] = b;
    }
    
    public void ensureCapacity(int c){
        if(c > data.length){
            byte[] newData = new byte[data.length*2];
            for(int i2 = 0; i2 < data.length; i2++)
                newData[i2] = data[i2];
            data = newData;
        }
    }
    
    public byte remove(int i){
        byte b = data[i];
        for(int i2 = i; i2 < size-1; i2++){
            data[i2] = data[i2+1];
        }
        size--;
        return b;
    }
    
    public void set(int i, byte b){
        data[i] = b;
    }
    
    public byte get(int i){
        return data[i];
    }
    
    public byte[] capToArray(){
        byte[] ret = new byte[size];
        for(int i = 0; i < ret.length; i++)
            ret[i] = data[i];
        return ret;
    }
    
    public ArrayListB clone(){
        byte[] newData = new byte[data.length];
        for(int i = 0; i < data.length; i++){
            newData[i] = data[i];
        }
        return new ArrayListB(newData);
    }
    
    /**
     * uses {@ArrayListB#capToArray} to make a new ArrayListB with an array which internal length is size
     */
    public ArrayListB copy(){
        return new ArrayListB(capToArray());
    }
    
}