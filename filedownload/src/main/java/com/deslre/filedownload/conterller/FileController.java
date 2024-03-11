package com.deslre.filedownload.conterller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/***
 * File.separator:这个代表系统目录中的间隔符，说白了就是斜线，不过有时候需要双线，有时候是单线，你用这个静态变量就解决兼容问题了。
 * */
@Controller
@RequestMapping("/index")
public class FileController {
    //上传文件路径
    private static final String parentPath = "E:" + File.separator + "filepload";

    @RequestMapping("/upload")
    public String upload() {
        return "uploadto";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        //判断上传文件是否为空，若为空则返回错误信息
        if (file.isEmpty()) {
            return "上传失败";
        } else {
            File filePath = new File(parentPath);
            if (!filePath.exists()) {//如果文件路径不存在则创建一个
                filePath.mkdir();
            }
            //获取文件原名
            String originalFileName = file.getOriginalFilename();
            System.out.println(originalFileName);
            //获取源文件前缀
            String fileNamePrefix = originalFileName.substring(0, originalFileName.lastIndexOf("."));
            //获取源文件后缀
            String fileNameSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            //将源文件前缀之后加上时间戳避免重名
            String newFileNamePrefix = fileNamePrefix + new Date().getTime();
            //得到上传后新文件的文件名
            String newFileName = newFileNamePrefix + fileNameSuffix;
            //创建一个新的File对象用于存放上传的文件
            File fileNew = new File(parentPath + File.separator + newFileName);
            try {
                file.transferTo(fileNew);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传成功";
    }


    //文件下载
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) {
        //通过response输出流将文件传递到浏览器
        //1. 获取文件路径
        String fileName = "强大专业卸载文件工具1709977754260.exe";
        //2.构建一个文件通过Paths工具类获取一个Path对象
        Path path = Paths.get(parentPath, fileName);
        //判断文件是否存在
        if (Files.exists(path)) {
            //存在则下载
            //通过response设定他的响应类型
            //4.获取文件的后缀名
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //            5.设置contentType ,只有指定contentType才能下载
            response.setContentType("application/" + fileSuffix);
            //            6.添加http头信息
            //            因为fileName的编码格式是UTF-8 但是http头信息只识别 ISO8859-1 的编码格式
            //            因此要对fileName重新编码
            try {
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //            7.使用  Path 和response输出流将文件输出到浏览器
            try {
                Files.copy(path, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //多文件上传
    @RequestMapping(value = "/uploads",method=RequestMethod.POST)
    @ResponseBody
    public String uploadTwo(@RequestParam("files")MultipartFile[] files){
        //判断上传文件是否为空，若为空则返回错误信息
        if(files.length < 1){
            System.out.println("files========");
        }
        System.out.println("files.length====="+files.length);
        for(MultipartFile file:files){
            if(file.isEmpty()){
                return "上传失败";
            }
            File filePath = new File(parentPath);
            if(!filePath.exists()){//如果文件路径不存在则创建一个
                filePath.mkdir();
            }
            //获取文件原名
            String originalFileName = file.getOriginalFilename();
            System.out.println(originalFileName);
            //获取源文件前缀
            String fileNamePrefix = originalFileName.substring(0,originalFileName.lastIndexOf("."));
            //获取源文件后缀
            String fileNameSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            //将源文件前缀之后加上时间戳避免重名
            String newFileNamePrefix = fileNamePrefix+new Date().getTime();
            //得到上传后新文件的文件名
            String newFileName = newFileNamePrefix+fileNameSuffix;
            //创建一个新的File对象用于存放上传的文件
            File fileNew = new File(parentPath+File.separator+newFileName);
            try {
                file.transferTo(fileNew);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传成功";
    }
}