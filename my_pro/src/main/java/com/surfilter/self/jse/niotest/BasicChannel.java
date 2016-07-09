/**
 * Project Name:my_pro
 * File Name:BasicChannel.java
 * Package Name:com.surfilter.self.jse.niotest
 * Date:2016年5月24日下午8:10:55
 *
*/

package com.surfilter.self.jse.niotest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * ClassName:BasicChannel <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年5月24日 下午8:10:55 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BasicChannel {

	public static void main(String[] args) {
		try {
			
			Charset charset = Charset.forName("UTF-8");//Java.nio.charset.Charset处理了字符转换问题。它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。  
	        CharsetDecoder decoder = charset.newDecoder();  
			
			RandomAccessFile afile = new RandomAccessFile("E:\\git\\repository\\my_pro\\src\\main\\java\\com\\surfilter\\self\\jse\\niotest\\ReadMe.md", "r");
			FileChannel channel = afile.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			CharBuffer charBuffer = CharBuffer.allocate(1024);
			int bytesRead = channel.read(buffer);
			while( bytesRead != -1 ){
				buffer.flip();
				decoder.decode(buffer, charBuffer, false);
				System.out.println(new String(buffer.array()));
				charBuffer.clear();
				buffer.clear();
				bytesRead = channel.read(buffer);
			}
			afile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

