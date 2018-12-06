package com.nextbin.hello.thrift.inf;

import com.facebook.swift.generator.swift2thrift.Main;
import com.facebook.swift.service.ThriftService;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zebin
 * @since 2018-12-02.
 */
@Slf4j
public class Swift2ThriftGenerator {
    public static final String PACKAGE_NAME = "com.nextbin.hello.thrift.inf";
    public static final String OUT_FILE = "hello-thrift-inf/src/main/resources/thrift/NextbinHelloService.thrift";

    /**
     * java -cp
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //1.设置参数
        List<String> list = new LinkedList<>();
        list.add("-package");
        list.add(PACKAGE_NAME);
        list.add("-namespace");
        list.add("java");
        list.add(PACKAGE_NAME);
        list.add("-namespace");
        list.add("py");
        list.add("nextbin.hello");
        list.add("-out");
        list.add(OUT_FILE);
        list.add("-recursive");
        //2.获取所有服务类
        list.addAll(getThriftServices());
        //3.生成thrift文件
        Main.main(list.toArray(new String[]{}));
    }

    private static List<String> getThriftServices() throws ClassNotFoundException {
        List<String> ret = new ArrayList<>();
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
                        ret.add(c.getName());
                    }
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
