package com.allen.interceptor;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class JsonConverterInterceptor extends MappingJackson2HttpMessageConverter{

	public Object read(Object clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void writeA(Object t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

	
	
}
