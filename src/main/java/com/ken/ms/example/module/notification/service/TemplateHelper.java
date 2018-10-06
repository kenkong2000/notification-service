package com.ken.ms.example.module.notification.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class TemplateHelper {
	
	
	protected String buildByTemplate(final Map<String, String> velocityMap, final String source, final String name) 
			throws ParseException {
		RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();            
		StringReader reader = new StringReader(source);			 
		Template template = new Template();		
		template.setRuntimeServices(runtimeServices);
		template.setData(runtimeServices.parse(reader, name));
		template.initDocument();
		return mergeTemplate(velocityMap, template); 	
	}
	
	
	protected String buildByTemplate (final Map<String, String> velocityMap, final String templateFilePath) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate(templateFilePath);
		return mergeTemplate(velocityMap, t);
	}
	
	
	
	private String mergeTemplate(final Map<String, String> velocityMap, final Template template) {
		 
		VelocityContext vc = new VelocityContext();
		
		velocityMap.forEach((k, v) -> 
				vc.put(k, v)		
		);
		
		StringWriter sw = new StringWriter();
		template.merge(vc, sw);
		     
		return sw.toString();		
	}

}
