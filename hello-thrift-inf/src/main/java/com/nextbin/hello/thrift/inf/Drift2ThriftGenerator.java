package com.nextbin.hello.thrift.inf;

import io.airlift.drift.annotations.ThriftService;
import io.airlift.drift.idl.generator.ThriftIdlGenerator;
import io.airlift.drift.idl.generator.ThriftIdlGeneratorConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zebin
 * @since 2018-12-02.
 */
@Slf4j
public class Drift2ThriftGenerator {
    public static final String PACKAGE_NAME = "com.nextbin.hello.thrift";
    public static final String OUT_FILE = "hello-thrift-inf/src/main/resources/thrift/NextbinHelloService.thrift";

    /**
     * java -cp
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Map<String, String> namespaces = new HashMap<>();
        namespaces.put("java", PACKAGE_NAME);
        namespaces.put("py", "nextbin.hello");
        ThriftIdlGeneratorConfig config = ThriftIdlGeneratorConfig.builder()
                .defaultPackage(PACKAGE_NAME).namespaces(namespaces).recursive(true).build();
        ThriftIdlGenerator generator = new ThriftIdlGenerator(config);
        String text = generator.generate(getThriftServices());
        try (PrintWriter writer = new PrintWriter(new File(OUT_FILE))) {
            writer.write(text);
        }
    }

    private static List<String> getThriftServices() throws ClassNotFoundException {
        List<String> ret = new ArrayList<>();
        getThriftServiceClass().forEach(c -> ret.add(c.getName()));
        return ret;
    }

    public static List<Class> getThriftServiceClass() throws ClassNotFoundException {
        List<Class> ret = new ArrayList<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = PACKAGE_NAME.replace(".", File.separator);
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if ("file".equals(type)) {
                List<Class> classes = getClassNameByFile(PACKAGE_NAME, url.getPath());
                for (Class c : classes) {
                    if (c.getAnnotation(ThriftService.class) != null) {
                        log.info("add service: {}", c.getName());
                        ret.add(c);
                        continue;
                    }
                    for (Class inf : c.getInterfaces()) {
                        if (inf.getAnnotation(ThriftService.class) != null) {
                            log.info("add service: {}", c.getName());
                            ret.add(c);
                            break;
                        }
                    }
                    // todo 递归
                }
            }
        } else {
            log.warn("package not found: {}", packagePath);
        }
        return ret;
    }

    private static List<Class> getClassNameByFile(String javaPackage, String fileDir) throws ClassNotFoundException {
        List<Class> ret = new ArrayList<>();
        File[] files = new File(fileDir).listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    ret.addAll(getClassNameByFile(javaPackage + "." + f.getName(), f.getPath()));
                } else {
                    if (f.getName().endsWith(".class")) {
                        String className = javaPackage + "." + f.getName().substring(0, f.getName().length() - 6);
                        ClassLoader loader = Thread.currentThread().getContextClassLoader();
                        ret.add(loader.loadClass(className));
                    }
                }
            }
        }
        return ret;
    }
}
