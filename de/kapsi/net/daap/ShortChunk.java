
package de.kapsi.net.daap;

import java.io.OutputStream;
import java.io.IOException;

/**
 *
 */
public class ShortChunk extends AbstractChunk {
    
    private int value;
    
    protected ShortChunk(String type, String name, int value) {
        super(type, name);
        
        this.value = (value & 0xFFFF);
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = (value & 0xFFFF);
    }
    
    public int getLength() {
        return 2;
    }
    
    public int getType() {
        return Chunk.SHORT_TYPE;
    }
    
    public void serialize(OutputStream out) throws IOException {
        
        super.serialize(out);
        
        byte[] dst= new byte[2];
        ByteUtil.toByte16BE(value, dst, 0);
        out.write(dst, 0, dst.length);
    }
    
    public String toString() {
        return super.toString() + "=" + value;
    }
}
