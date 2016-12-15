package com.allen.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import com.allen.utils.Strings;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class SimpleMappingException extends SimpleMappingExceptionResolver{

	
	// -- Content Type 定义 --//
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	public static final String DOWNLOAD_TYPE = "application/x-download;charset=UTF-8";

	// -- header 常量定义 --//
	public static final String HEADER_ENCODING = "encoding";
	public static final String HEADER_NOCACHE = "no-cache";
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final boolean DEFAULT_NOCACHE = true;

	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		String viewName = determineViewName(ex, request);  
        if(viewName != null) {// JSP格式返回  
            if(!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                    .getHeader("X-Requested-With")!= null && request  
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
                // 如果不是异步请求  
                // Apply HTTP status code for error views, if specified.  
                // Only apply it if we're processing a top-level request.  
                Integer statusCode = determineStatusCode(request, viewName);  
                if (statusCode != null) {  
                    applyStatusCodeIfPossible(request, response, statusCode);  
                }  
                return getModelAndView(viewName, ex, request);  
            } 
            else {// JSON格式返回  
                    //PrintWriter writer = response.getWriter();  
                    //writer.write(ex.getMessage());  
                    //writer.flush();  
                    renderJson(response, ex.getMessage(), new String[0]);
                return null;  
            }  
        } 
        else {  
        	JSONObject object = new JSONObject();
        	object.put("executeStatus", 1);
        	object.put("code", "1111111");
        	object.put("msg", ex.getMessage());
        	try {
        		/*PrintWriter writer = response.getWriter();  
                writer.write(object.toString());  
                writer.flush();
                writer.close();*/
			} catch (Exception e) {
				// TODO: handle exception
			}
        	JSON json = JSONSerializer.toJSON(object);
        	renderJson(response, json.toString(), new String[0]);
            return null;  
        }  
		
		//return super.doResolveException(request, response, handler, ex);
	}
	
	
	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
	 * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
	 * "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(HttpServletResponse res, final String contentType, final String content,
			final String... headers) {
		HttpServletResponse response = initResponseHeader(res, contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	
	/**
	 * 分析并设置contentType与headers.
	 */
	static HttpServletResponse initResponseHeader(HttpServletResponse response, final String contentType,
			final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			
			String headerName = Strings.substringBefore(header, ":");
			String headerValue = Strings.substringAfter(header, ":");

			if (Strings.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (Strings.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + " is not valid  headerType!");
			}
		}

		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			setNoCacheHeader(response);
		}
		return response;
	}
	

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}
	
	
	public static void renderJson(HttpServletResponse res, final String jsonString, final String... headers) {
		render(res, JSON_TYPE, jsonString, headers);
	}
	
	
	
	

}
