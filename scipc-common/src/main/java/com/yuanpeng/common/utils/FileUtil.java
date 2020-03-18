package com.yuanpeng.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.yuanpeng.common.exception.BadRequestException;
import com.yuanpeng.common.exception.GlobalExceptionHandler;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * @description: 文件工具类
 * @author: YuanPeng
 * @create: 2020-02-11 16:54
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    // GB MB KB
    private static final int GB = 1024 * 1024 * 1024;
    private static final int MB = 1024 * 1024;
    private static final int KB = 1024;

    // 格式化小数
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * MultipartFIle -> File 先创建一个相同类型的File文件 然后覆盖
     * @param multipartFile
     * @return
     */
    public static File multipartFileToFile(MultipartFile multipartFile) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String suffix = "." + getExtensionName(fileName);
        File file = null;
        try {
            file = File.createTempFile(IdUtil.simpleUUID(), suffix);    // 用uuid作为文件名 防治临时文件重复
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获得文件名的后缀 不带.
     * @param fileName
     * @return
     */
    public static String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return fileName;
    }

    /**
     * 获取文件名 不加后缀 不加.
     * @param fileName
     * @return
     */
    public static String getFileNameNoEx(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        }
        return fileName;
    }

    /**
     * 文件大小转换
     * @param size
     * @return
     */
    public static String getSize(long size) {
        String resultSize;
        if (size / GB >= 1) {
            // 如果 > 1GB
            resultSize = DF.format(size / (float) GB) + "GB    ";
        } else if (size / MB >= 1) {
            resultSize = DF.format(size / (float) MB) + "MB    ";
        } else if (size / KB >= 1) {
            resultSize = DF.format(size / (float) KB) + "KB    ";
        } else {
            resultSize = size + "B    ";
        }
        return resultSize;
    }

    /**
     * inputStream -> File
     * @param ins
     * @param name
     * @return
     */
    public static File inputStreamToFile(InputStream ins, String name) throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
        if (file.exists()) {
            return file;
        }
        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        int len = 1024;
        byte[] buffer = new byte[len];
        while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
            os.write(buffer, 0, bytesRead);
            os.flush();
        }
        os.close();
        ins.close();
        return file;
    }


    /**
     * 上传File到filePath
     * @param file
     * @param filePath
     * @return
     */
    public static File upload(MultipartFile file, String filePath, String resultName) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmssS");
        String name = getFileNameNoEx(file.getOriginalFilename());
        String suffix = getExtensionName(file.getOriginalFilename());
        String nowStr = "-" + simpleDateFormat.format(date);

        try {
            String fileName = name + nowStr + "." + suffix;
            if (resultName != null) {
                fileName = resultName;
            }
            String path = filePath + fileName;
            // getCanonicalFile 解析各种路径
            File dest = new File(path).getCanonicalFile();
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param file
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 加载文件以Resource对象
     * @param path
     * @return
     * @throws Exception
     */
    public static Resource loadFileAsResource(String path) throws Exception {
        try {
            Path filePath = Paths.get(path).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }
    /**
     * 判断两个文件是否相同
     */
    public static boolean check(File file1, File file2) {
        String img1Md5 = getMd5(file1);
        String img2Md5 = getMd5(file2);
        return img1Md5.equals(img2Md5);
    }

    /**
     * 判断两个文件是否相同
     */
    public static boolean check(String file1Md5, String file2Md5) {
        return file1Md5.equals(file2Md5);
    }


    public static String getMd5(File file) {
        return getMd5(getByte(file));
    }

    private static String getMd5(byte[] bytes) {
        // 16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(bytes);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            // 移位 输出字符串
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void checkSize(long maxSize, long size) {
        // 1M
        int len = 1024 * 1024;
        if(size > (maxSize * len)){
            throw new BadRequestException("文件超出规定大小");
        }
    }


    private static byte[] getByte(File file) {
        // 得到文件长度
        byte[] b = new byte[(int) file.length()];
        try {
            InputStream in = new FileInputStream(file);
            try {
                in.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return b;
    }

    public static String fileToBase64(File file) throws Exception {
        FileInputStream inputFile = new FileInputStream(file);
        String base64;
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        base64= Base64.encode(buffer);
        return base64.replaceAll("[\\s*\t\n\r]", "");
    }

    /**
     * 导出excel
     */
    public static void downloadExcel(List<Map<String, Object>> list, HttpServletResponse response) throws IOException {
        String tempPath =System.getProperty("java.io.tmpdir") + IdUtil.fastSimpleUUID() + ".xlsx";
        File file = new File(tempPath);
        BigExcelWriter writer= ExcelUtil.getBigWriter(file);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition","attachment;filename=file.xlsx");
        ServletOutputStream out=response.getOutputStream();
        // 终止后删除临时文件
        file.deleteOnExit();
        writer.flush(out, true);
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    public static String getFileType(String type) {
        String documents = "txt doc pdf ppt pps xlsx xls docx";
        String music = "mp3 wav wma mpa ram ra aac aif m4a";
        String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg";
        if(image.contains(type)){
            return "图片";
        } else if(documents.contains(type)){
            return "文档";
        } else if(music.contains(type)){
            return "音乐";
        } else if(video.contains(type)){
            return "视频";
        } else {
            return "其他";
        }
    }
}
