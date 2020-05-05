
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;


public class Word {
    public static final int SIZE = 4; //bytes
    private final byte[] data;
    
    public Word(){
        data = new byte[SIZE];
    }
    
    public Word(Word src){
        data = src.data.clone();
    }
    
    public byte getByte(int index){
        return data[index];
    }
    public void setByte(int index, byte info){
        data[index] = info;
    }
    @Override
    public Word clone(){
        return new Word(this);
    }
    
    public boolean equals(Word another){
        return Arrays.equals(data, another.data);
    }

    byte[] getBytes() {
        return Arrays.copyOf(data, SIZE);
    }
    
    public static int wordToInt(Word word) {
        ByteBuffer bb = ByteBuffer.allocateDirect(SIZE*8);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.clear();
        for(int i = 0; i < SIZE; i++){
            bb.put(word.getByte(i));
        }
        bb.position(0);
        return bb.getInt();
    }

    public static int writeToS(int S, int x, int value) {
        ByteBuffer bb = ByteBuffer.allocateDirect(8);
        ByteBuffer aa = ByteBuffer.allocateDirect(8);
        ByteBuffer cc = ByteBuffer.allocateDirect(8);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        aa.order(ByteOrder.LITTLE_ENDIAN);
        aa.order(ByteOrder.LITTLE_ENDIAN);
        bb.clear();
        aa.clear();
        aa.clear();
        bb.putInt(S);
        if (value == 0)
            cc.putInt(256);
        else cc.putInt(0);
        for(int i = 0; i < 8; i++){
            if (i != x){
                aa.put(bb.get(i));
            }
            else{
                aa.put(cc.get(i));
            }
        }
        aa.position(0);
        return aa.getInt();
    }

    public static int getFromS(int S, int x) {
        ByteBuffer bb = ByteBuffer.allocateDirect(8);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.clear();
        bb.putInt(S);
        int value = bb.get(x);
        return value;
    }
    
    public static Word intToWord(int value) {
        ByteBuffer bb = ByteBuffer.allocateDirect(SIZE*8);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.clear();
        bb.putInt(value);
        Word word = new Word();
        for(int i = 0; i < SIZE; i++){
            word.setByte(i, bb.get(i));
        }
        return word;
    }

    public static Word charToWord(char value) {
        ByteBuffer bb = ByteBuffer.allocateDirect(SIZE*8);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.clear();
        bb.putInt(value);
        Word word = new Word();
        for(int i = 0; i < SIZE; i++){
            word.setByte(i, bb.get(i));
        }
        return word;
    }
}
