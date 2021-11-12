package com.lin.demo.utils;

import com.google.gson.Gson;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * 编写HttpClient发送请求
 */
public class HttpClientUtil {

	public static final Integer HTTP_POST = 1;
	public static final Integer HTTP_GET = 2;
	public static final Integer HTTP_JSON = 3;

	public static final String UTF8 = "UTF-8";
	public static final String GBK = "gbk";
	public static final String GB2312 = "gb2312";
	public static final String ISO_8859_1 = "ISO-8859-1";


	/**
	 * @param url       发送请求地址
	 * @param param     发送请求的参数信息
	 * @param httpType  发送请求的类型，post为HttpClient.HTTP_POST;get为HttpClient.HTTP_GET
	 * @param uriEncode 发送请求的字符集编码，HttpClient.UTF8；HttpClient.GBK；HttpClient.GB2312；HttpClient.ISO_8859_1；
	 * @param timeOut   发送请求的相应时间
	 * @return 发送请求的返回值或者null
	 * @throws RuntimeException
	 * @throws Exception        发送异常信息
	 */
	public static String sendHttpUrl(String url, Map<String, String> param, Integer httpType, String uriEncode, Integer timeOut) throws RuntimeException, Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
		CloseableHttpResponse response = null;
		if (HttpClientUtil.HTTP_GET.compareTo(httpType) == 0) {
			StringBuilder sb = new StringBuilder();
			String responseUrl;
			sb.append(url);
			if (param != null && param.size() > 0) {
				sb.append("?");
				for (String key : param.keySet()) {
					sb.append(key).append("=").append(param.get(key)).append("&");
				}
				responseUrl = sb.toString().trim().substring(0, sb.toString().trim().length() - 1);
			} else {
				responseUrl = url;
			}
			HttpGet httpGet = new HttpGet(responseUrl);
			httpGet.setConfig(config);
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			httpGet.setHeader("Accept-Language", "zh-cn,zh");
			switch (uriEncode) {
				case HttpClientUtil.UTF8:
					httpGet.setHeader("Accept-Charset", "utf-8");
					break;
				case HttpClientUtil.GBK:
					httpGet.setHeader("Accept-Charset", "gbk");
					break;
				case HttpClientUtil.GB2312:
					httpGet.setHeader("Accept-Charset", "gb2312");
					break;
				case HttpClientUtil.ISO_8859_1:
					httpGet.setHeader("Accept-Charset", "ISO-8859-1");
					break;
				default:
					httpGet.setHeader("Accept-Charset", "utf-8");
					break;
			}
			response = client.execute(httpGet);
		} else if (HttpClientUtil.HTTP_POST.compareTo(httpType) == 0) {
			HttpPost post = new HttpPost(url);
			if (param != null && param.size() > 0) {
				List<NameValuePair> nvpList = new ArrayList<>();
				for (String key : param.keySet()) {
					BasicNameValuePair bnvpObject = new BasicNameValuePair(key, param.get(key));
					nvpList.add(bnvpObject);
				}
				if (uriEncode != null) {
					switch (uriEncode) {
						case HttpClientUtil.UTF8:
							post.setHeader("Accept-Charset", "utf-8");
							post.setEntity(new UrlEncodedFormEntity(nvpList, UTF8));
							break;
						case HttpClientUtil.GBK:
							post.setHeader("Accept-Charset", "gbk");
							post.setEntity(new UrlEncodedFormEntity(nvpList, GBK));
							break;
						case HttpClientUtil.GB2312:
							post.setHeader("Accept-Charset", "gb2312");
							post.setEntity(new UrlEncodedFormEntity(nvpList, GB2312));
							break;
						case HttpClientUtil.ISO_8859_1:
							post.setHeader("Accept-Charset", "ISO-8859-1");
							post.setEntity(new UrlEncodedFormEntity(nvpList, ISO_8859_1));
							break;
						default:
							post.setHeader("Accept-Charset", "utf-8");
							post.setEntity(new UrlEncodedFormEntity(nvpList));
							break;
					}
				} else {
					post.setHeader("Accept-Charset", "utf-8");
					post.setEntity(new UrlEncodedFormEntity(nvpList));
				}
			}
			post.setConfig(config);
			post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			post.setHeader("Accept-Language", "zh-cn,zh");
			response = client.execute(post);
		} else if (HttpClientUtil.HTTP_JSON.compareTo(httpType) == 0) {
			HttpPost post = new HttpPost(url);
			if (param != null && param.size() > 0) {
				Gson gson = new Gson();
				post.setHeader("Accept", "application/json");
				if (uriEncode != null) {
					switch (uriEncode) {
						case HttpClientUtil.UTF8:
							post.setHeader("Accept-Charset", "utf-8");
							post.setEntity(new StringEntity(gson.toJson(param), UTF8));
							break;
						case HttpClientUtil.GBK:
							post.setHeader("Accept-Charset", "gbk");
							post.setEntity(new StringEntity(gson.toJson(param), GBK));
							break;
						case HttpClientUtil.GB2312:
							post.setHeader("Accept-Charset", "gb2312");
							post.setEntity(new StringEntity(gson.toJson(param), GB2312));
							break;
						case HttpClientUtil.ISO_8859_1:
							post.setHeader("Accept-Charset", "ISO-8859-1");
							post.setEntity(new StringEntity(gson.toJson(param), ISO_8859_1));
							break;
						default:
							post.setHeader("Accept-Charset", "utf-8");
							post.setEntity(new StringEntity(gson.toJson(param), UTF8));
							break;
					}
				} else {
					post.setHeader("Accept-Charset", "utf-8");
					post.setEntity(new StringEntity(gson.toJson(param), UTF8));
				}
			}
			post.setConfig(config);
			post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			//post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			post.setHeader("Accept-Language", "zh-cn,zh");
			post.setHeader("Content-Type", "application/json");
			response = client.execute(post);
		}
		if (response != null) {
			String responseValue;
			if (uriEncode != null) {
				responseValue = EntityUtils.toString(response.getEntity(), uriEncode);
			} else {
				responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			if (response.getStatusLine().getStatusCode() == 200) {
				/** 请求正常*/
				response.close();
				client.close();
				return responseValue;
			} else {
				throw new Exception(responseValue);
			}
		} else {
			if (client != null) {
				client.close();
			}
			return null;
		}

	}

	/**
	 * @param url       发送请求地址
	 * @param param     发送请求的参数信息
	 * @param httpType  发送请求的类型，post为HttpClient.HTTP_POST;get为HttpClient.HTTP_GET
	 * @param uriEncode 发送请求的字符集编码，HttpClient.UTF8；HttpClient.GBK；HttpClient.GB2312；HttpClient.ISO_8859_1；
	 * @param timeOut   发送请求的相应时间
	 * @return 发送请求的返回值或者null
	 * @throws RuntimeException
	 * @throws Exception        发送异常信息
	 */
	public static String sendHttpUrlObj(String url, Map<String, Object> param, Integer httpType, String uriEncode, Integer timeOut) throws RuntimeException, Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
		CloseableHttpResponse response = null;
		if (HttpClientUtil.HTTP_GET.compareTo(httpType) == 0) {
			StringBuilder sb = new StringBuilder();
			String responseUrl;
			sb.append(url);
			if (param != null && param.size() > 0) {
				sb.append("?");
				for (String key : param.keySet()) {
					sb.append(key).append("=").append(param.get(key)).append("&");
				}
				responseUrl = sb.toString().trim().substring(0, sb.toString().trim().length() - 1);
			} else {
				responseUrl = url;
			}
			HttpGet httpGet = new HttpGet(responseUrl);
			httpGet.setConfig(config);
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			httpGet.setHeader("Accept-Language", "zh-cn,zh");
			switch (uriEncode) {
				case HttpClientUtil.UTF8:
					httpGet.setHeader("Accept-Charset", "utf-8");
					break;
				case HttpClientUtil.GBK:
					httpGet.setHeader("Accept-Charset", "gbk");
					break;
				case HttpClientUtil.GB2312:
					httpGet.setHeader("Accept-Charset", "gb2312");
					break;
				case HttpClientUtil.ISO_8859_1:
					httpGet.setHeader("Accept-Charset", "ISO-8859-1");
					break;
				default:
					httpGet.setHeader("Accept-Charset", "utf-8");
					break;
			}
			response = client.execute(httpGet);
		} else if (HttpClientUtil.HTTP_POST.compareTo(httpType) == 0) {
			HttpPost post = new HttpPost(url);
			if (param != null && param.size() > 0) {
				List<NameValuePair> nvpList = new ArrayList<>();
				for (String key : param.keySet()) {
					BasicNameValuePair bnvpObject = new BasicNameValuePair(key, String.valueOf(param.get(key)));
					nvpList.add(bnvpObject);
				}
				if (uriEncode != null) {
					switch (uriEncode) {
						case HttpClientUtil.UTF8:
							post.setHeader("Accept-Charset", "utf-8");
							post.setEntity(new UrlEncodedFormEntity(nvpList, UTF8));
							break;
						case HttpClientUtil.GBK:
							post.setHeader("Accept-Charset", "gbk");
							post.setEntity(new UrlEncodedFormEntity(nvpList, GBK));
							break;
						case HttpClientUtil.GB2312:
							post.setHeader("Accept-Charset", "gb2312");
							post.setEntity(new UrlEncodedFormEntity(nvpList, GB2312));
							break;
						case HttpClientUtil.ISO_8859_1:
							post.setHeader("Accept-Charset", "ISO-8859-1");
							post.setEntity(new UrlEncodedFormEntity(nvpList, ISO_8859_1));
							break;
						default:
							post.setHeader("Accept-Charset", "utf-8");
							post.setEntity(new UrlEncodedFormEntity(nvpList));
							break;
					}
				} else {
					post.setHeader("Accept-Charset", "utf-8");
					post.setEntity(new UrlEncodedFormEntity(nvpList));
				}
			}
			post.setConfig(config);
			post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			post.setHeader("Accept-Language", "zh-cn,zh");
			response = client.execute(post);
		} else if (HttpClientUtil.HTTP_JSON.compareTo(httpType) == 0) {
			HttpPost post = new HttpPost(url);
			if (param != null && param.size() > 0) {
				Gson gson = new Gson();
				post.setHeader("Accept", "application/json");
				if (uriEncode != null) {
					System.out.println(gson.toJson(param));
					switch (uriEncode) {
						case HttpClientUtil.UTF8:
							post.setHeader("Accept-Charset", "utf-8");
							post.setEntity(new StringEntity(gson.toJson(param), UTF8));
							break;
						case HttpClientUtil.GBK:
							post.setHeader("Accept-Charset", "gbk");
							post.setEntity(new StringEntity(gson.toJson(param), GBK));
							break;
						case HttpClientUtil.GB2312:
							post.setHeader("Accept-Charset", "gb2312");
							post.setEntity(new StringEntity(gson.toJson(param), GB2312));
							break;
						case HttpClientUtil.ISO_8859_1:
							post.setHeader("Accept-Charset", "ISO-8859-1");
							post.setEntity(new StringEntity(gson.toJson(param), ISO_8859_1));
							break;
						default:
							post.setHeader("Accept-Charset", "utf-8");
							post.setEntity(new StringEntity(gson.toJson(param), UTF8));
							break;
					}
				} else {
					post.setHeader("Accept-Charset", "utf-8");
					post.setEntity(new StringEntity(gson.toJson(param), UTF8));
				}
			}
			post.setConfig(config);
			post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			//post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			post.setHeader("Accept-Language", "zh-cn,zh");
			post.setHeader("Content-Type", "application/json");
			response = client.execute(post);
		}
		if (response != null) {
			String responseValue;
			if (uriEncode != null) {
				responseValue = EntityUtils.toString(response.getEntity(), uriEncode);
			} else {
				responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			if (response.getStatusLine().getStatusCode() == 200) {
				/** 请求正常*/
				response.close();
				client.close();
				return responseValue;
			} else {
				throw new Exception(responseValue);
			}
		} else {
			if (client != null) {
				client.close();
			}
			return null;
		}

	}

	/**
	 * @param url       发送请求地址
	 * @param param     发送请求的参数信息
	 * @param httpType  发送请求的类型，post为HttpClient.HTTP_POST;get为HttpClient.HTTP_GET
	 * @param uriEncode 发送请求的字符集编码，HttpClient.UTF8；HttpClient.GBK；HttpClient.GB2312；HttpClient.ISO_8859_1；
	 * @param timeOut   发送请求的相应时间
	 * @return 发送请求的返回值或者null
	 * @throws RuntimeException
	 * @throws Exception        发送异常信息
	 */
	public static String sendHttpPostBody(String url, String param, Integer httpType, String uriEncode, Integer timeOut) throws RuntimeException, Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
		CloseableHttpResponse response = null;
		if (HttpClientUtil.HTTP_POST.compareTo(httpType) == 0) {
			HttpPost post = new HttpPost(url);
			if (StringUtils.hasText(param)) {
				List<NameValuePair> nvpList = new ArrayList<>();
				if (uriEncode != null) {
					switch (uriEncode) {
						case HttpClientUtil.UTF8:
							post.setHeader("Accept-Charset", "utf-8");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, UTF8));
							break;
						case HttpClientUtil.GBK:
							post.setHeader("Accept-Charset", "gbk");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, GBK));
							break;
						case HttpClientUtil.GB2312:
							post.setHeader("Accept-Charset", "gb2312");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, GB2312));
							break;
						case HttpClientUtil.ISO_8859_1:
							post.setHeader("Accept-Charset", "ISO-8859-1");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, ISO_8859_1));
							break;
						default:
							post.setHeader("Accept-Charset", "utf-8");
							//post.setEntity(new UrlEncodedFormEntity(nvpList));
							break;
					}
				} else {
					post.setHeader("Accept-Charset", "utf-8");
					//post.setEntity(new UrlEncodedFormEntity(nvpList));
				}
			}
			post.setConfig(config);
			//post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			//post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			//post.setHeader("Accept-Language", "zh-cn,zh");
			post.setHeader("Content-Type", "text/xml; charset=UTF-8");
			post.setEntity(new StringEntity(param, "UTF-8"));
			response = client.execute(post);
		}
		if (response != null) {
			String responseValue;
			if (uriEncode != null) {
				responseValue = EntityUtils.toString(response.getEntity(), uriEncode);
			} else {
				responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			if (response.getStatusLine().getStatusCode() == 200) {
				/** 请求正常*/
				response.close();
				client.close();
				return responseValue;
			} else {
				throw new Exception(responseValue);
			}
		} else {
			if (client != null) {
				client.close();
			}
			return null;
		}
	}

	/**
	 * @param url       发送请求地址
	 * @param param     发送请求的参数信息
	 * @param httpType  发送请求的类型，post为HttpClient.HTTP_POST;get为HttpClient.HTTP_GET
	 * @param uriEncode 发送请求的字符集编码，HttpClient.UTF8；HttpClient.GBK；HttpClient.GB2312；HttpClient.ISO_8859_1；
	 * @param timeOut   发送请求的相应时间
	 * @return 发送请求的返回值或者null
	 * @throws RuntimeException
	 * @throws Exception        发送异常信息
	 */
	public static String sendHttpPostJsonBody(String url, String param, Integer httpType, String uriEncode, Integer timeOut) throws RuntimeException, Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
		CloseableHttpResponse response = null;
		if (HttpClientUtil.HTTP_POST.compareTo(httpType) == 0) {
			HttpPost post = new HttpPost(url);
			if (StringUtils.hasText(param)) {
				List<NameValuePair> nvpList = new ArrayList<>();
				if (uriEncode != null) {
					switch (uriEncode) {
						case HttpClientUtil.UTF8:
							post.setHeader("Accept-Charset", "utf-8");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, UTF8));
							break;
						case HttpClientUtil.GBK:
							post.setHeader("Accept-Charset", "gbk");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, GBK));
							break;
						case HttpClientUtil.GB2312:
							post.setHeader("Accept-Charset", "gb2312");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, GB2312));
							break;
						case HttpClientUtil.ISO_8859_1:
							post.setHeader("Accept-Charset", "ISO-8859-1");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, ISO_8859_1));
							break;
						default:
							post.setHeader("Accept-Charset", "utf-8");
							//post.setEntity(new UrlEncodedFormEntity(nvpList));
							break;
					}
				} else {
					post.setHeader("Accept-Charset", "utf-8");
					//post.setEntity(new UrlEncodedFormEntity(nvpList));
				}
			}
			post.setConfig(config);
			//post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			//post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			//post.setHeader("Accept-Language", "zh-cn,zh");
			post.setHeader("Content-Type", "application/json;charset=UTF-8");
			System.out.println("====================================================");
			System.out.println(param);
			System.out.println(new StringEntity(param, "UTF-8"));
			post.setEntity(new StringEntity(param, "application/json", "UTF-8"));
			response = client.execute(post);
		}
		if (response != null) {
			String responseValue;
			if (uriEncode != null) {
				responseValue = EntityUtils.toString(response.getEntity(), uriEncode);
			} else {
				responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			if (response.getStatusLine().getStatusCode() == 200) {
				/** 请求正常*/
				response.close();
				client.close();
				return responseValue;
			} else {
				throw new Exception(responseValue);
			}
		} else {
			if (client != null) {
				client.close();
			}
			return null;
		}
	}


	public static String sendHttpPostBodyForCrmDownloadFile(String url, String param,String localPath,Integer timeOut) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		if (StringUtils.hasText(param)){
			List<NameValuePair> nvpList = new ArrayList<>();
		}
		post.setConfig(config);
		post.setHeader("Content-Type", "application/json;charset=UTF-8");
		post.setEntity(new StringEntity(param, "UTF-8"));
		response = client.execute(post);

		Header header =response.getFirstHeader("Content-Type");
		if(header.getValue().contains("application/json")){
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		}

		if(header.getValue().contains("application/x-msdownload")){
//			header=response.getFirstHeader("Content-Disposition");
//			String fileNamePattern="filename=";
//			String fileName = header.getValue().substring(header.getValue().indexOf(fileNamePattern)+fileNamePattern.length());
//			fileName =  java.net.URLDecoder.decode(fileName, "UTF-8");

//			if(!localPath.endsWith("/")){
//				localPath+="/";
//			}
//			String filePath= localPath+fileName;
			File file = new File(localPath);
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);
			byte[] buffer=new byte[1024*4];
			int ch = 0;
			InputStream is = response.getEntity().getContent();
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer,0,ch);
			}
			is.close();
			fileout.flush();
			fileout.close();
			return null;
		}
		throw new RuntimeException("无法解析:"+header);
	}


	/**
	 * @param url       发送请求地址
	 * @param uriEncode 发送请求的字符集编码，HttpClient.UTF8；HttpClient.GBK；HttpClient.GB2312；HttpClient.ISO_8859_1；
	 * @param timeOut   发送请求的相应时间
	 * @return 发送请求的返回值或者null
	 * @throws RuntimeException
	 * @throws Exception        发送异常信息
	 */
	public static String sendHttpPostBodyByForm(String url, byte[] img, String fileName, String uriEncode, Integer timeOut) {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		File file = null;
		try {
			RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
			HttpPost post = new HttpPost(url);
			file = new File(fileName);
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
			CloseableHttpClient httpclient = HttpClients.createDefault();
			Calendar now = Calendar.getInstance();
			String BoundaryStr = "------------" + now.getTimeInMillis();
			post.addHeader("Connection", "keep-alive");
			post.addHeader("Accept", "*/*");
			post.addHeader("Content-Type", "multipart/form-data;boundary=" + BoundaryStr);
			MultipartEntityBuilder meb = MultipartEntityBuilder.create();
			meb.setBoundary(BoundaryStr).setCharset(Charset.forName("utf-8")).setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			meb.addBinaryBody("media", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
			HttpEntity entity = meb.build();
			post.setEntity(entity);
			post.setConfig(config);
			response = httpclient.execute(post);
			if (response != null) {
				String responseValue;
				if (uriEncode != null) {
					responseValue = EntityUtils.toString(response.getEntity(), uriEncode);
				} else {
					responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
				}
				if (response.getStatusLine().getStatusCode() == 200) {
					/** 请求正常*/
					response.close();
					client.close();
					return responseValue;
				} else {
					throw new Exception(responseValue);
				}
			} else {
				if (client != null) {
					client.close();
				}
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (file != null) {
				file.delete();
			}
		}
		return null;
	}

	/**
	 * @param url       发送请求地址
	 * @param param     发送请求的参数信息
	 * @param httpType  发送请求的类型，post为HttpClient.HTTP_POST;get为HttpClient.HTTP_GET
	 * @param uriEncode 发送请求的字符集编码，HttpClient.UTF8；HttpClient.GBK；HttpClient.GB2312；HttpClient.ISO_8859_1；
	 * @param timeOut   发送请求的相应时间
	 * @return 发送请求的返回值或者null
	 * @throws RuntimeException
	 * @throws Exception        发送异常信息
	 */
	public static String sendHttpPostBodyTianAn(String url, String param, Integer httpType, String uriEncode, Integer timeOut) throws RuntimeException, Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
		CloseableHttpResponse response = null;
		if (HttpClientUtil.HTTP_POST.compareTo(httpType) == 0) {
			HttpPost post = new HttpPost(url);
			if (StringUtils.hasText(param)) {
				List<NameValuePair> nvpList = new ArrayList<>();
				if (uriEncode != null) {
					switch (uriEncode) {
						case HttpClientUtil.UTF8:
							post.setHeader("Accept-Charset", "utf-8");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, UTF8));
							break;
						case HttpClientUtil.GBK:
							post.setHeader("Accept-Charset", "gbk");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, GBK));
							break;
						case HttpClientUtil.GB2312:
							post.setHeader("Accept-Charset", "gb2312");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, GB2312));
							break;
						case HttpClientUtil.ISO_8859_1:
							post.setHeader("Accept-Charset", "ISO-8859-1");
							//post.setEntity(new UrlEncodedFormEntity(nvpList, ISO_8859_1));
							break;
						default:
							post.setHeader("Accept-Charset", "utf-8");
							//post.setEntity(new UrlEncodedFormEntity(nvpList));
							break;
					}
				} else {
					post.setHeader("Accept-Charset", "utf-8");
					//post.setEntity(new UrlEncodedFormEntity(nvpList));
				}
			}
			post.setConfig(config);
			//post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
			//post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
			//post.setHeader("Accept-Language", "zh-cn,zh");
			//post.setHeader("Content-Type", "text/xml; charset=UTF-8");
			post.setEntity(new StringEntity(param, "UTF-8"));
			response = client.execute(post);
		}
		if (response != null) {
			String responseValue;
			if (uriEncode != null) {
				responseValue = EntityUtils.toString(response.getEntity(), uriEncode);
			} else {
				responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			if (response.getStatusLine().getStatusCode() == 200) {
				/** 请求正常*/
				response.close();
				client.close();
				return responseValue;
			} else {
				throw new Exception(responseValue);
			}
		} else {
			if (client != null) {
				client.close();
			}
			return null;
		}

	}

	private static TrustManager manager = new X509TrustManager() {
		@Override
		public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	private static void enableSSL() {
		try {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[]{manager}, null);
			socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}

	private static SSLConnectionSocketFactory socketFactory;

	public static String doHttpsPost(String url, Map<String, String> param) throws Exception {
		enableSSL();
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
				.setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();

		HttpPost post = new HttpPost(url);
		List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
		if (param != null && param.size() > 0) {
			for (String key : param.keySet()) {
				BasicNameValuePair bnvpObject = new BasicNameValuePair(key, param.get(key));
				nvpList.add(bnvpObject);
			}
		}

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvpList, Consts.UTF_8);
		post.setEntity(entity);
		CloseableHttpResponse response = httpClient.execute(post);

		if (response != null) {
			String responseValue;
			responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
			if (response.getStatusLine().getStatusCode() == 200) {
				return responseValue;
			} else {
				throw new Exception(responseValue);
			}
		} else {
			return null;
		}
	}

	private static void trustAllHttpsCertificates() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[1];
		TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	static class miTM implements TrustManager, X509TrustManager {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] certs, String authType)
				throws CertificateException {
			return;
		}

		@Override
		public void checkClientTrusted(X509Certificate[] certs, String authType)
				throws CertificateException {
			return;
		}
	}


	/**
	 * 太保专用
	 *
	 * @param url
	 * @param requestMsg
	 * @return
	 */
	public static String sentHttpsPostRequestTaiBao(String requestMsg, String url) {
		HttpClient httpclient = new DefaultHttpClient();

		// 导入数字证书并注册SSLSocketFactory
		try {
			registerSSLSocketFactory(httpclient);
		} catch (Exception e) {
			e.printStackTrace();
		}

/*		HostnameVerifier hv = new HostnameVerifier() {
			@Override
			public boolean verify(String urlHostName, SSLSession session) {
				System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
				return true;
			}
		};
		try {
			trustAllHttpsCertificates();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultHostnameVerifier(hv);*/


		// 设置超时时间
		int timeout = 60000;
		HttpConnectionParams.setSoTimeout(httpclient.getParams(), timeout);
		// 注意：必须以post方式发送请求
		HttpPost httppost = new HttpPost(url);

		// 设置请求参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 版本标识
		params.add(new BasicNameValuePair("messageRouter", "3"));
		// 业务伙伴代码
		params.add(new BasicNameValuePair("tradingPartner", "BJDLBX"));
		// 业务协议
		params.add(new BasicNameValuePair("documentProtocol", "CPIC_ECOM"));
		// xml请求报文
		params.add(new BasicNameValuePair("requestMessage", requestMsg));
		// 注意：编码必须是UTF-8
		HttpEntity request = null;
		try {
			request = new UrlEncodedFormEntity(params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httppost.setEntity(request);
		// 返回内容为xml，请使用xml解析工具对返回内容进行解析
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httppost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = httpResponse.getEntity();
		String result = null;
		if (entity != null) {
			try {
				result = EntityUtils.toString(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	/**
	 * 中华专用
	 *
	 * @param url
	 * @param bodyXml
	 * @return
	 */
	public static String httpsPostBody(String url, String bodyXml) throws Exception {
		enableSSL();
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
				.setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();

		HttpPost post = new HttpPost(url);
		/*List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
		if (param != null && param.size() > 0) {
			for (String key : param.keySet()) {
				BasicNameValuePair bnvpObject = new BasicNameValuePair(key, param.get(key));
				nvpList.add(bnvpObject);
			}
		}*/

		//UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvpList, Consts.UTF_8);
		post.setHeader("Accept-Charset", "gbk");
		post.setEntity(new StringEntity(bodyXml, "GBK"));
		CloseableHttpResponse response = httpClient.execute(post);

		if (response != null) {
			String responseValue;
			responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
			if (response.getStatusLine().getStatusCode() == 200) {
				return responseValue;
			} else {
				throw new Exception(responseValue);
			}
		} else {
			return null;
		}

	}

	/**
	 * 太保专用
	 *
	 * @param requestMsg
	 * @return
	 * @throws Exception
	 */
	public static String sentHttpPostRequestTaiBao(String requestMsg, String url) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		// 设置超时时间
		int timeout = 60000;
		HttpConnectionParams.setSoTimeout(httpclient.getParams(), timeout);

		// 注意：必须以post方式发送请求
		HttpPost httppost = new HttpPost(url);

		// 设置请求参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 版本标识
		params.add(new BasicNameValuePair("messageRouter", "3"));
		// 业务伙伴代码
		params.add(new BasicNameValuePair("tradingPartner", "BJDLBX"));
		// 业务协议
		params.add(new BasicNameValuePair("documentProtocol", "CPIC_ECOM"));
		// xml请求报文
		params.add(new BasicNameValuePair("requestMessage", requestMsg));

		// 注意：编码必须是UTF-8
		HttpEntity request = new UrlEncodedFormEntity(params, "UTF-8");
		httppost.setEntity(request);

		// 返回内容为xml，请使用xml解析工具对返回内容进行解析
		HttpResponse httpResponse = httpclient.execute(httppost);
		HttpEntity entity = httpResponse.getEntity();
		String result = null;
		if (entity != null) {
			result = EntityUtils.toString(entity);
		}
		return result;
	}

	/**
	 * 长安专用
	 *
	 * @param requestMsg
	 * @return
	 * @throws Exception
	 */
	public static String sentHttpsPostRequestChangAn(String requestMsg, String url, String trustStorePath, String trustStorePwd) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();

		// 导入数字证书并注册SSLSocketFactory
		try {
			registerSSLSocketFactoryChangAn(httpclient, trustStorePath, trustStorePwd);
			SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 设置超时时间
		int timeout = 60000;
		HttpConnectionParams.setSoTimeout(httpclient.getParams(), timeout);
		// 注意：必须以post方式发送请求
		HttpPost httppost = new HttpPost(url);

		// 设置请求参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// xml请求报文
		//params.add(new BasicNameValuePair("requestMessage", requestMsg));
		// 注意：编码必须是UTF-8
		/*HttpEntity request = null;
		try {
			request = new UrlEncodedFormEntity(params, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httppost.setEntity(request);*/
		httppost.setHeader("Accept-Charset", "gbk");
		httppost.setEntity(new StringEntity(requestMsg, "GBK"));

		// 返回内容为xml，请使用xml解析工具对返回内容进行解析
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httppost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = httpResponse.getEntity();
		String result = null;
		if (entity != null) {
			try {
				result = EntityUtils.toString(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	/**
	 * 中华专用
	 *
	 * @param url         访问地址
	 * @param busCode     业务
	 * @param partnerCode
	 * @param user
	 * @param pwd
	 * @param requestMsg
	 * @return
	 * @throws Exception
	 */
	public static String sentHttpPostRequestZhongHua(String url, String busCode, String partnerCode,
													 String user, String pwd, String requestMsg) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		// 设置超时时间
		int timeout = 60000;
		HttpConnectionParams.setSoTimeout(httpclient.getParams(), timeout);

		// 注意：必须以post方式发送请求
		HttpPost httppost = new HttpPost(url);

		httppost.setHeader("Accept-Charset", "gbk");

		httppost.setHeader("GW_CH_TX", busCode);
		httppost.setHeader("GW_CH_CODE", partnerCode);
		httppost.setHeader("GW_CH_USER", user);
		httppost.setHeader("GW_CH_PWD", pwd);
		//httppost.setHeader("GW_FACADE_FLAG", "4");
		httppost.setHeader("Content-Type", "text/xml; charset=GBK");
		httppost.setEntity(new StringEntity(requestMsg, "gbk"));

		// 返回内容为xml，请使用xml解析工具对返回内容进行解析
		HttpResponse httpResponse = httpclient.execute(httppost);
		HttpEntity entity = httpResponse.getEntity();
		String result = null;
		if (entity != null) {
			result = EntityUtils.toString(entity);
		}
		/*if (entity != null){
			InputStream instreams = entity.getContent();
			result = ConvertStreamToString(instreams);
			System.out.println("Response:" + "\n" + result);
		}*/
		return result;
	}

	// Convert stream to string
	private static String ConvertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			System.out.println("Error=" + e.toString());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				System.out.println("Error=" + e.toString());
			}
		}
		return sb.toString();

	}


	private static void registerSSLSocketFactory(HttpClient httpclient) throws Exception {
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream instream = new FileInputStream(new File("/data/work/ins-ssl/cpicJttp.keystore"));

		try {
			trustStore.load(instream, "cpicJttp".toCharArray());
		} finally {
			instream.close();
		}
		SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
		Scheme sch = new Scheme("https", socketFactory, 443);
		httpclient.getConnectionManager().getSchemeRegistry().register(sch);

	}

	private static void registerSSLSocketFactoryChangAn(HttpClient httpclient, String truststorePath, String truststorePwd) throws Exception {
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream instream = new FileInputStream(new File(truststorePath));

		try {
			trustStore.load(instream, truststorePwd.toCharArray());
		} finally {
			instream.close();
		}

		SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
		socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme sch = new Scheme("https", socketFactory, 443);
		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
	}

	/**
	 * 根据地址获得数据的字节流
	 *
	 * @param strUrl 网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 *
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	/**
	 * @param url       发送请求地址
	 * @param param     发送请求的参数信息
	 * @param httpType  发送请求的类型，post为HttpClient.HTTP_POST;get为HttpClient.HTTP_GET
	 * @param uriEncode 发送请求的字符集编码，HttpClient.UTF8；HttpClient.GBK；HttpClient.GB2312；HttpClient.ISO_8859_1；
	 * @param timeOut   发送请求的相应时间
	 * @return 发送请求的返回值或者null
	 * @throws RuntimeException
	 * @throws Exception        发送异常信息
	 */
	public static byte[] sendHttpPostJsonBodyByWechat(String url, String param, Integer httpType, String uriEncode, Integer timeOut) throws RuntimeException, Exception {
		enableSSL();
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
				.setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
		CloseableHttpResponse response = null;
		if (HttpClientUtil.HTTP_POST.compareTo(httpType) == 0) {
			HttpPost post = new HttpPost(url);
			if (StringUtils.hasText(param)) {
				if (uriEncode != null) {
					switch (uriEncode) {
						case HttpClientUtil.UTF8:
							post.setHeader("Accept-Charset", "utf-8");
							break;
						case HttpClientUtil.GBK:
							post.setHeader("Accept-Charset", "gbk");
							break;
						case HttpClientUtil.GB2312:
							post.setHeader("Accept-Charset", "gb2312");
							break;
						case HttpClientUtil.ISO_8859_1:
							post.setHeader("Accept-Charset", "ISO-8859-1");
							break;
						default:
							post.setHeader("Accept-Charset", "utf-8");
							break;
					}
				} else {
					post.setHeader("Accept-Charset", "utf-8");
				}
			}
			post.setConfig(config);
			post.setHeader("Content-Type", "application/json;charset=UTF-8");
			StringEntity s = new StringEntity(param, ContentType.APPLICATION_JSON.getMimeType(), "utf-8");
			post.setEntity(s);
			response = client.execute(post);
		}
		if (response != null) {
			if (response.getStatusLine().getStatusCode() == 200) {
				InputStream in = response.getEntity().getContent();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = in.read(buffer)) > -1) {
					os.write(buffer, 0, len);
				}
				/** 请求正常*/
				response.close();
				client.close();
				return os.toByteArray();
			} else {
				String responseValue;
				if (uriEncode != null) {
					responseValue = EntityUtils.toString(response.getEntity(), uriEncode);
				} else {
					responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
				}
				throw new Exception(responseValue);
			}
		} else {
			if (client != null) {
				client.close();
			}
			return null;
		}
	}

	public static  void main(String[] args) throws Exception {
		String ss = "<?xml version=\"1.0\" encoding=\"gbk\"?>\n" +
				"<root>\n" +
				"  <Sign>7D15FF4B7EE31782637D342708739260</Sign>\n" +
				"  <body>\n" +
				"    <BidderName>test</BidderName>\n" +
				"    <BidderNumber>test</BidderNumber>\n" +
				"    <RequestUUID>test</RequestUUID>\n" +
				"    <Amount>100</Amount>\n" +
				"<BidderPhoneNo>18521033021</BidderPhoneNo>\n" +
				"<BidderAddress>test</BidderAddress>\n" +
				"<TendereeName>test</TendereeName>\n" +
				"<TendereeNumber>123456789012345678</TendereeNumber>\n" +
				"<TendereePhone>18521033022</TendereePhone>\n" +
				"<TendereeAddress>test</TendereeAddress>\n" +
				"<ProjectName>test</ProjectName>\n" +
				"<ProjectType>test</ProjectType>\n" +
				"<ProjectNo>test</ProjectNo>\n" +
				"<OpenDate>2019-06-10 09:00:00</OpenDate>\n" +
				"<filePath>https://cngb.oss-cn-hangzhou.aliyuncs.com/gongbao/company/2019-05-16/7HnjGQE2jj.jpg</filePath>\n" +
				"  </body>\n" +
				"</root>";
		String rs = HttpClientUtil.sendHttpPostBody("http://110.16.73.171:7021/testser6",ss,HttpClientUtil.HTTP_POST,HttpClientUtil.GBK,100000);
		System.out.println(rs);
	}
}