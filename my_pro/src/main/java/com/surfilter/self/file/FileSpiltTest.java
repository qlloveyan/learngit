/**
 * Project Name:shutdown
 * File Name:FileSpiltTest.java
 * Package Name:com.surfilter.self.file
 * Date:2016年3月4日上午10:35:20
 *
*/

package com.surfilter.self.file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ClassName:FileSpiltTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月4日 上午10:35:20 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FileSpiltTest {

	public static void main(String[] args) {
		try {
			RandomAccessFile raf = new RandomAccessFile("test.csv", "r");
			long numSplits = 10; //from user input, extract it from args
			long sourceSize = raf.length();
			long bytesPerSplit = sourceSize/numSplits ;
			long remainingBytes = sourceSize % numSplits;
			
			int maxReadBufferSize = 8 * 1024; //8KB
			for(int destIx=1; destIx <= numSplits; destIx++) {
				BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("split."+destIx));
				if(bytesPerSplit > maxReadBufferSize) {
					long numReads = bytesPerSplit/maxReadBufferSize;
					long numRemainingRead = bytesPerSplit % maxReadBufferSize;
					for(int i=0; i<numReads; i++) {
						readWrite(raf, bw, maxReadBufferSize);
					}
					if(numRemainingRead > 0) {
						readWrite(raf, bw, numRemainingRead);
					}
				}else {
					readWrite(raf, bw, bytesPerSplit);
				}
				bw.close();
			}
			if(remainingBytes > 0) {
				BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("split."+(numSplits+1)));
				readWrite(raf, bw, remainingBytes);
				bw.close();
			}
			raf.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void readWrite(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException {
        byte[] buf = new byte[(int) numBytes];
        int val = raf.read(buf);
        if(val != -1) {
            bw.write(buf);
        }
    }
}

