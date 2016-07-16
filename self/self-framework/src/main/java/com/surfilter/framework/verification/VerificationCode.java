/**
 * Project Name:lichen
 * File Name:VerificationCode.java
 * Package Name:com.surfilter.framework.verification
 * Date:2014-3-19上午10:21:41
 *
*/

package com.surfilter.framework.verification;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.jhlabs.image.WaterFilter;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.LineTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.model.FileHandle;

/**
 * 验证码引擎
 * ClassName:VerificationCode <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-3-19 上午10:21:41 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class VerificationCode extends ListImageCaptchaEngine{
	protected void buildInitialFactories() {  
		
		String rangeVcode = FileUtil.getResouseValue("rangeVcode");
		boolean rangeVcodeFlag = false; //是否需要干扰线
		if(!rangeVcode.equals("")){
			try {
				rangeVcodeFlag = Boolean.parseBoolean(rangeVcode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        // build filters  
        WaterFilter water = new WaterFilter();  
  
        water.setAmplitude(3d);  
        water.setAntialias(true);  
        water.setPhase(20d);  
        water.setWavelength(70d);  
  
        ImageDeformation backDef = new ImageDeformationByFilters(  
                new ImageFilter[] {});  
        ImageDeformation textDef = new ImageDeformationByFilters(  
                new ImageFilter[] {});  
        ImageDeformation postDef = new ImageDeformationByFilters(  
                new ImageFilter[] { water });  
  
        // word generator  
        WordGenerator dictionnaryWords = new RandomWordGenerator(  
                "abcdefhjkmnprstuvwxyz23456789");  
        // wordtoimage components  
        RandomRangeColorGenerator colors = new RandomRangeColorGenerator(  
                new int[] { 255, 255 }, new int[] { 255, 255 },  
                new int[] { 255, 255 });  
  
        // Arial,Tahoma,Verdana,Helvetica,宋体,黑体,幼圆  
        Font[] fonts = new Font[] { new Font("Arial", 0, 15),  
                new Font("Tahoma", 0, 15), new Font("Verdana", 0, 15),  
                new Font("Helvetica", 0, 15), new Font("宋体", 0, 15),  
                new Font("黑体", 0, 15), new Font("幼圆", 0, 15) };  
  
        // 设置字符以及干扰线  
        RandomRangeColorGenerator lineColors = new RandomRangeColorGenerator(  
                new int[] { 150, 255 }, new int[] { 150, 255 }, new int[] {  
                        150, 255 });  
        //随机字符
        TextPaster randomPaster = null;
        if(rangeVcodeFlag){
        	randomPaster= new DecoratedRandomTextPaster(new Integer(4),  
        			new Integer(4), colors, true,  
        			new TextDecorator[] { new LineTextDecorator(new Integer(1),  
        					lineColors) });  
        }else{
        	randomPaster= new DecoratedRandomTextPaster(new Integer(4),  
        			new Integer(4), colors, true,  
        			null);  
        }
        
        Color c = Color.white;
        String rgb = FileUtil.getResouseValue("vcodeColor_RGB");
        if(!rgb.equals("")){
        	int r = 0;
        	int g = 0;
        	int b = 0;
        	try {
        		String[] rgbs = rgb.split(",");
        		r = Integer.parseInt(rgbs[0]);
        		g = Integer.parseInt(rgbs[1]);
        		b = Integer.parseInt(rgbs[2]);
				c = new Color(r, g, b);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        BackgroundGenerator back = new UniColorBackgroundGenerator(new Integer(  
                140), new Integer(45), c);  
  
        FontGenerator shearedFont = new RandomFontGenerator(new Integer(30),  
                new Integer(0), fonts);  
        // word2image 1  
        WordToImage word2image;  
        word2image = new DeformedComposedWordToImage(shearedFont, back,  
                randomPaster, backDef, textDef, postDef);  
  
        this.addFactory(new GimpyFactory(dictionnaryWords, word2image));  
  
    }  
}

