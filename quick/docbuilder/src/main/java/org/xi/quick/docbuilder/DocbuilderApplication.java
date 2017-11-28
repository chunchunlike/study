package org.xi.quick.docbuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xi.quick.docbuilder.generator.DocGenerator;
import org.xi.quick.utils.io.DirectoryUtil;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DocbuilderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DocbuilderApplication.class, args);
    }

    @Autowired
    DocGenerator docGenerator;

    @Autowired
    String outputPath;

    @Override
    public void run(String... strings) throws Exception {

        DirectoryUtil.createIfNotExists(outputPath);

        docGenerator.generateModelDoc();
        docGenerator.generateVoDoc();
        docGenerator.generateParameterDoc();
        docGenerator.generateApiDoc();
    }
}
