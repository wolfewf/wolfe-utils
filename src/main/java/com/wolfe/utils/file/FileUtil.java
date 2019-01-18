package com.wolfe.utils.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * apache的commons的FileUtils类基本操作(具体代码可以查看官方的API)
 * 官方的API地址：http://commons.apache.org/proper/commons-io/javadocs/api-2.4/index.html
 *
 * @author Wolfe
 * @since 1.0.0
 */
public class FileUtil {

    /**
     * 写文件
     * 1.这里只列出3种方式全参数形式，api提供部分参数的方法重载
     * 2.最后一个布尔参数都是是否是追加模式
     * 3.如果目标文件不存在，FileUtils会自动创建
     */
    public void writer() throws IOException {
        FileUtils.write(new File("E:/test/test.txt"), "test api", "UTF-8", true);

        List<String> lines = new ArrayList<>();
        lines.add("test:");
        lines.add("www.baidu.com");
        FileUtils.writeLines(new File("E:/test/test.txt"), lines, true);

        FileUtils.writeStringToFile(new File("E:/test/test.txt"), "test：wolfe", "UTF-8", true);

    }

    /**
     * 读文件
     */
    public void read() throws IOException {
        System.out.println(FileUtils.readFileToString(new File("E:/test/test.txt"), "UTF-8"));
        //返回一个list
        System.out.println(FileUtils.readLines(new File("E:/test/test.txt"), "UTF-8"));
    }

    /**
     * 删除 文件/文件夹
     */
    public static void delete() throws IOException {
        //删除目录
        FileUtils.deleteDirectory(new File("E:/test/"));
        //文件夹不是空也可以被删除，不会抛出异常
        FileUtils.deleteQuietly(new File("E:/test/"));
    }

    /**
     * 移动 文件/文件夹
     * moveDirectory：E:/test2里的内容是E:/test的内容。
     * moveDirectoryToDirectory：E:/test2文件夹移动到到E:/test3里
     */
    public static void move() throws IOException {
        //移动文件 或 文件夹
        //注意这里 第二个参数文件不存在会引发异常
        FileUtils.moveDirectory(new File("E:/test"), new File("E:/test2"));
        FileUtils.moveDirectoryToDirectory(new File("E:/test2"), new File("E:/test3"), true);
        //还有一些其他操作的API
        //moveFileToDirectory(srcFile, destDir, createDestDir)
        //moveFile(File srcFile, File destFile)
        //moveToDirectory(File src, File destDir, boolean createDestDir)
        //moveToDirectory和其他的区别是 它能自动识别操作文件还是文件夹
    }

    /**
     * 复制
     */
    public static void copy() throws IOException {
        //结果是test和test1在同一目录
        FileUtils.copyDirectory(new File("E:/test"), new File("E:/test1"));
        //结果是将test拷贝到test2下
        FileUtils.copyDirectoryToDirectory(new File("E:/test"), new File("E:/test2"));
        //拷贝文件
        FileUtils.copyFile(new File("E:/test.xml"), new File("E:/test.xml.bak"));
        //拷贝文件到目录中
        FileUtils.copyFileToDirectory(new File("E:/test.xml"), new File("E:/test"));
        //拷贝url到文件
        FileUtils.copyURLToFile(new URL("http://www.test.com/rss/test.xml"), new File("E:/test.xml"));
    }

    /**
     * 其他操作
     */
    public static void other() throws IOException {
        //判断是否包含文件或者文件夹
        boolean b = FileUtils.directoryContains(new File("E:/test"), new File("E:/test/test.txt"));
        System.out.println(b);

        //获得临时目录 和 用户目录
        System.out.println(FileUtils.getTempDirectoryPath());
        System.out.println(FileUtils.getUserDirectoryPath());

        //打开流，如果不存在创建文件及其目录结构
        //第二个参数表示 文件流是否是追加方式
        FileOutputStream fos = FileUtils.openOutputStream(new File("E:/test/test.txt"), true);
        fos.write("欢迎访问：www.test.com\r\n".getBytes());
        fos.close();

        //文件 或 文件夹大小
        System.out.println(FileUtils.sizeOf(new File("E:/test")));
        System.out.println(FileUtils.sizeOfDirectory(new File("E:/test")));
    }
}
