/**
 * Project Name:lichen
 * File Name:KeywordHandler.java
 * Package Name:com.surfilter.framework.search
 * Date:2014-2-14上午10:45:38
 *
 */

package com.surfilter.framework.search;

import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;

/**
 * 关键词处理 ClassName:KeywordHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-2-14 上午10:45:38 <br/>
 * 
 * @author wangguohong
 * @version
 * @since JDK 1.6
 * @see
 */
public class KeywordHandler {
	
	/**
	 * TO_SIMPLE:TODO(简拼).
	 * @since JDK 1.6
	 */
	public static final int TO_SIMPLE=1;

	/**
	 * TO_FULL:TODO(全拼).
	 * @since JDK 1.6
	 */
	public static final int TO_FULL = 2;
	
	/**
	 * URL_PATH:TODO(菜单中的访问地址).
	 * @since JDK 1.6
	 */
	public static final String URL_PATH = "path";
	public static final String FUNCCODE = "funcCode";
	public static final String ID = "id";
	/**
	 * 
	 * wordToPinyin:(获取输入词语的拼音). <br/>
	 *
	 * @author wangguohong
	 * @param word 需要转换的词
	 * @param type 获取拼音的类型，
	 * KeywordHandler.TO_FULL为全拼，KeywordHandler.TO_SIMPLE为简拼
	 * @return
	 * @since JDK 1.6
	 */
	public static String wordToPinyin(String word,int type) {
	//System.out.println("菜单名称："+word);
		String pinyinStr = "";
//		if(null!=word){
//			if(word.contains(" ")){
//				System.out.println(word);
//			}
//		}
		if(null == word || word.trim().equals("")){
			return "";
		}
		try {
			char[] nameChar = word.toCharArray();
			HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
			defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			for (int i = 0; i < nameChar.length; i++) {
				if (nameChar[i] > 128) {
					try {
						String [] temp = PinyinHelper.toHanyuPinyinStringArray(
								nameChar[i], defaultFormat);
						switch (type) {
						case KeywordHandler.TO_SIMPLE:
							if(temp!=null && temp.length>0){
								
								pinyinStr += PinyinHelper.toHanyuPinyinStringArray(
										nameChar[i], defaultFormat)[0].charAt(0);
							}
							break;
						case KeywordHandler.TO_FULL:
							if(temp!=null && temp.length>0){
								
								pinyinStr += PinyinHelper.toHanyuPinyinStringArray(
										nameChar[i], defaultFormat)[0];
							}
							
							break;
						}
					} catch (BadHanyuPinyinOutputFormatCombination e) {
						e.printStackTrace();
					}
				} else {
					pinyinStr += nameChar[i];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("拼音："+pinyinStr);
		return pinyinStr;
	}

	/**
	 * setMenuWordsToSession:(设置菜单关键字到session). <br/>
	 *
	 * @author wangguohong
	 * @param uiMenus
	 * @param listkeymenu
	 * @since JDK 1.6
	 */
	public static void setMenuWordsToSession(UnCheckTreeNodeBean uiMenus,List<KeywordModel> listkeymenu){
		
		KeywordModel keyword = new KeywordModel();
		keyword.setType(KeywordModel.TYPE_MENU);
		keyword.setSimplePinyin(KeywordHandler.wordToPinyin(uiMenus.getText(), KeywordHandler.TO_SIMPLE));
		keyword.setFullPinyin(KeywordHandler.wordToPinyin(uiMenus.getText(), KeywordHandler.TO_FULL));
		keyword.setWord(uiMenus.getText());
		//System.out.println("菜单名称："+uiMenus.getText());
		if(uiMenus.getAttributes()!=null && (uiMenus.getLeaf()!=null && uiMenus.getLeaf())){
			Object url = uiMenus.getAttributes().get(URL_PATH);
			Object funcCode = uiMenus.getAttributes().get(FUNCCODE);
			Object id = uiMenus.getId();
			if(url!=null){
				keyword.setUrl(url.toString());
				keyword.setId(Long.parseLong(id.toString()));
				if(funcCode!=null){
					
					keyword.setFrameName("f_"+funcCode.toString());
				}
				//System.out.println("加入搜索的菜单名称："+uiMenus.getText());
				if(!keyword.getFrameName().startsWith("f_NS")){
					
					listkeymenu.add(keyword);
				}
			}
		}
		if(( uiMenus.getLeaf() == null || !uiMenus.getLeaf()) && 
				uiMenus.getChildren() != null && uiMenus.getChildren().size()>0){
			List<UnCheckTreeNodeBean> listuiMenustemp = uiMenus.getChildren();
			for(UnCheckTreeNodeBean node : listuiMenustemp){
				setMenuWordsToSession(node, listkeymenu);
			}
		}
		
	}

	public static void main(String[] args) {
		System.out.println(KeywordHandler.wordToPinyin("王國洪",KeywordHandler.TO_FULL));
	}
}
