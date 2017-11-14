package org.xi.quick.codebuilder;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

@SpringBootApplication
public class CodebuilderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CodebuilderApplication.class, args);
	}

	@Autowired
	Map<Object, Object> commonPropertiesMap;

	@Autowired
	freemarker.template.Configuration freeMarkerConfiguration;

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("============================");
		commonPropertiesMap.entrySet().forEach(o-> System.out.println(o.getKey()+":"+o.getValue()));
		System.out.println("============================");


		Template template = freeMarkerConfiguration.getTemplate("${className}.java");

		System.out.println("===================================");

		OutputStream stream = new FileOutputStream("/Workspace/test.java");
		Writer out = new OutputStreamWriter(stream);
		template.process(commonPropertiesMap, out);
		System.out.println("===================================");
	}
}
