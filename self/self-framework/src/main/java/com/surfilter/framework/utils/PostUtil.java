package com.surfilter.framework.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * ClassName: PostUtil <br/>
 * Function: post请求公用类. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014-3-27 下午4:52:37 <br/>
 *
 * @author Alex
 * @version 
 * @since JDK 1.6
 */
public class PostUtil {

	/**
	 * invoker:(这里用一句话描述这个方法的作用). <br/>
	 * @author Alex
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static String invoker(String url,
			Map<String,String> param) throws Exception {
		String returnStr = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Iterator<String> iterator = param.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			String value = param.get(key);
			params.add(new BasicNameValuePair(key, value));  
		}
	    HttpPost post = new HttpPost(url);
	    post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		CloseableHttpClient httpclient = null;
		httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		response = httpclient.execute(post);
		if (response.getStatusLine().getStatusCode() == 404 || response.getStatusLine().getStatusCode() == 500) {
			if (response != null) {
				response.close();
			}
			returnStr = "";
		}else{
			StringBuffer sb = new StringBuffer();
			List<String> repondStrings = IOUtils.readLines(response.getEntity().getContent(),"UTF-8");
			for (Iterator iterator = repondStrings.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				sb.append(string);
			}
			returnStr =  sb.toString();
		}
		if (response != null) {
			response.close();
		}
		return returnStr;
	}

	public static void main(String[] args) throws Exception {
		String url = "http://172.31.26.1:8090/proxy/screen";
		
//		Map param = new HashMap();
//		param.put("req_zc", "{\"username\":\"admin\",\"authors\":[\"/main/index\",\"/logAnalyse/bus/main\"],\"key\":\"LTEzNTIxNjI4MjU1NzU3ODM2NDM=\r\n\",\"rand\":\"eLjur0tCOGrR6q1\",\"sessionkey\":\"sk_admin\"}");
//		System.out.println(PostUtil.invoker(url, param));
		String s="{\"type\": \"provinceResource\",\"service\":\"provinceInfo\"}";
		System.out.println(PostUtil.httpsPost(url, s,"utf-8"));
		
	}
	
	
	public static String httpsPost(String url,Map<String,String> map,String charset) throws Exception{
		 HttpClient httpClient = null;  
	        HttpPost httpPost = null;  
	        String result = null;  
	        try{  
	            httpClient = new SSLClient();  
	            httpPost = new HttpPost(url);  
	            //设置参数  
	            List<NameValuePair> list = new ArrayList<NameValuePair>();  
	            Iterator iterator = map.entrySet().iterator();  
	            while(iterator.hasNext()){  
	                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
	                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
	            }  
	            if(list.size() > 0){  
	                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
	                httpPost.setEntity(entity);  
	            }  
	            HttpResponse response = httpClient.execute(httpPost); 
	            if(response != null){  
	                HttpEntity resEntity = response.getEntity();  
	                if(resEntity != null){  
	                    result = EntityUtils.toString(resEntity,charset);  
	                }  
	            }  
	        }catch(Exception ex){  
	            ex.printStackTrace();  
	        }  
	        return result;  
	}
	
	/**
	 * 根据url 以及json参数返回数据
	 * @param url
	 * @param body
	 * @param charset 编码格式
	 * @return
	 * @throws Exception
	 */
	public static String httpsPost(String url,String body,String charset) throws Exception{
		String returnStr = null;
		StringEntity entity = new StringEntity(body.toString(),charset);//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json");    
      
	    HttpPost post = new HttpPost(url);
	    post.setEntity(entity);    
	   
		CloseableHttpClient httpclient = null;
		httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		response = httpclient.execute(post);
		if (response.getStatusLine().getStatusCode() == 404 || response.getStatusLine().getStatusCode() == 500) {
			if (response != null) {
				response.close();
			}
			returnStr = "";
		}else{
			StringBuffer sb = new StringBuffer();
			List<String> repondStrings = IOUtils.readLines(response.getEntity().getContent(),charset);
			for (Iterator iterator = repondStrings.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				sb.append(string);
			}
			returnStr =  sb.toString();
		}
		if (response != null) {
			response.close();
		}
		return returnStr; 
	}
	
	
	
	
	public static HttpResponse httpsPostResponse(String url,Map<String,String> map,String charset) throws Exception{
		 HttpClient httpClient = null;  
	        HttpPost httpPost = null;  
	        HttpResponse response  = null;
	        try{  
	            httpClient = new SSLClient();  
	            httpPost = new HttpPost(url);  
	            //设置参数  
	            List<NameValuePair> list = new ArrayList<NameValuePair>();  
	            Iterator iterator = map.entrySet().iterator();  
	            while(iterator.hasNext()){  
	                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
	                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
	            }  
	            if(list.size() > 0){  
	                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
	                httpPost.setEntity(entity);  
	            }  
	           response = httpClient.execute(httpPost); 
	           
	        }catch(Exception ex){  
	            ex.printStackTrace();  
	        }  
	        return response;  
	}
	
	
	

}
