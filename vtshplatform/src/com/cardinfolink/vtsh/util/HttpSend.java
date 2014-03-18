/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardinfolink.vtsh.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ClientConnectionManager;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;

import org.apache.http.params.CoreConnectionPNames;

/**
 * @author webapp
 */

public class HttpSend {
	
     	private static X509TrustManager tm = new X509TrustManager() {
     		
		public void checkClientTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
		}
		
		public void checkServerTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
		}
		
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};
	
    @SuppressWarnings("deprecation")
	public static HttpClient getInstance() throws KeyManagementException,
			NoSuchAlgorithmException {
		HttpClient client = new DefaultHttpClient();
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx);
		ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = client.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", ssf, 443));
		client = new DefaultHttpClient(ccm, client.getParams());
		return client;
	}
    
     public static HttpResponse sendbyHTTPPost(Map<String,String> mm, String serverUrl)
    {
         HttpClient hc = new DefaultHttpClient();
         if(!serverUrl.startsWith("http"))
            serverUrl = "http://" + serverUrl;
            HttpPost hp = new HttpPost(serverUrl);
        try{
            List<NameValuePair> nvp = new ArrayList<NameValuePair>();
            Iterator iter = mm.entrySet().iterator(); 
            while (iter.hasNext()) { 
                Entry<String,String> entry = (Entry<String,String>) iter.next(); 
                String key = entry.getKey(); 
                String val = entry.getValue(); 
                nvp.add(new BasicNameValuePair(key, val));
                } 

            
            hp.setEntity(new UrlEncodedFormEntity(nvp));
         
            HttpResponse response = hc.execute(hp);
            
           
            
            return response;
            
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
     
    }
    
     public static HttpResponse sendbyHTTPSPost(Map<String,String> mm, String serverUrl)
     {
           HttpClient hc;
		try {
			hc = HttpSend.getInstance();
			if(!serverUrl.startsWith("https"))
                serverUrl = "https://" + serverUrl;
            HttpPost hp = new HttpPost(serverUrl);
            List<NameValuePair> nvp = new ArrayList<NameValuePair>();
            Iterator iter = mm.entrySet().iterator();
            
            while (iter.hasNext()) { 
                Entry<String,String> entry = (Entry<String,String>) iter.next(); 
                String key = entry.getKey(); 
                String val = entry.getValue(); 
                nvp.add(new BasicNameValuePair(key, val));
            } 
            hp.setEntity(new UrlEncodedFormEntity(nvp));
            
            HttpResponse response = hc.execute(hp);
            
            return response;
		
		}catch(Exception e)
         {
             e.printStackTrace();
             return null;
         }
		
      
     }
     public static HttpResponse sendbyHTTPGET(String url)
     {
    	 System.out.println(url);
         HttpClient hc = new DefaultHttpClient();
         HttpGet hg = new HttpGet(url);
         try{
        	 HttpResponse res = hc.execute(hg);
         return res;
         }catch(Exception e)
         {
             e.printStackTrace();
             return null;
         }
     }
     
     public static HttpResponse sendbyHTTPSGET(String Url)
     {
        try{
        	HttpClient httpsClient = HttpSend.getInstance();
        	//HttpHost proxy = new HttpHost("202.101.25.184", 80);
        	//httpsClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
        	httpsClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,20000);
        	HttpGet httpGet = new HttpGet(Url);
        	HttpResponse response = httpsClient.execute(httpGet);
                return response;
            }catch(Exception e){
                 e.printStackTrace();
                 return null;
             }
     }
     
}
