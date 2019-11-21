package com.catt.common.util.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 * Description:
 * Author: Zhang zhongtao
 * Version:
 * Since: Ver 1.1
 * Date: 2014-11-12 10:53
 * </pre>
 */
public class FileUtil extends FileUtils {

    // 文档文件类型数组
    public static final String DOC[] = {"txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};

    // 图片文件类型数组
    public static final String IMG[] = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
            "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};

    // 视频文件类型数组
    public static final String VIDEO[] = {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};

    // 音频文件类型数组
    public static final String AUDIO[] = {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg", "m4a", "vqf"};

    // 压缩文件类型数组
    public static final String ZIP[] = {"rar", "zip", "gz", "z"};

    /**
     * 创建临时文件
     *
     * @param inputStream
     * @param name        文件名
     * @param ext         扩展名
     * @return
     * @throws IOException
     */
    public static File createTmpFile(InputStream inputStream, String name,
                                     String ext) throws IOException {
        FileOutputStream fos = null;
        try {
            File tmpFile = File.createTempFile(name, '.' + ext);
            tmpFile.deleteOnExit();
            fos = new FileOutputStream(tmpFile);
            int read = 0;
            byte[] bytes = new byte[1024 * 100];
            while ((read = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
            fos.flush();
            return tmpFile;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 获取类路径
     *
     * @return
     */
    public static String getClassPath() {
        return com.catt.common.util.io.FileUtil.class.getClassLoader().getResource("").getPath();
    }

    /**
     * 解析文件路径表达式为文件对象，支持classpath:表达式来获取类路径下的文件
     *
     * @param filePath 文件路径表达式，例如："abc.txt"、"classpath:abc.txt"
     * @return
     */
    public static File parseFile(String filePath) {
        File file = null;

        if (filePath != null) {
            if (filePath.toLowerCase().startsWith("classpath:")) {// 类路径
                filePath = getClassPath() + filePath.substring(10);
            }

            file = new File(filePath);
        }

        return file;
    }

    /**
     * 根据文件名获取文件类型
     *
     * @param fileName
     * @return
     */
    public static Integer fileType(String fileName) {
        if (com.catt.common.util.lang.StringUtil.checkStr(fileName)) {
            String extension = FilenameUtils.getExtension(fileName).toLowerCase();

            if (com.catt.common.util.lang.StringUtil.checkStr(extension)) {
                // 文档文件
                for (String str : DOC) {
                    if (str.equals(extension)) {
                        return Type.Doc.ordinal();
                    }
                }

                // 图像文件
                for (String str : IMG) {
                    if (str.equals(extension)) {
                        return Type.Img.ordinal();
                    }
                }

                // 视频文件
                for (String str : VIDEO) {
                    if (str.equals(extension)) {
                        return Type.Video.ordinal();
                    }
                }

                // 音频文件
                for (String str : AUDIO) {
                    if (str.equals(extension)) {
                        return Type.Audio.ordinal();
                    }
                }

                // 压缩文件
                for (String str : ZIP) {
                    if (str.equals(extension)) {
                        return Type.Zip.ordinal();
                    }
                }
            }

        }

        return null;
    }


    /**
     * 文件类型<p><br>
     * 1-文档文件（包括：txt、doc、wps、rtf、html、pdf、hlp、xls等）
     * 2-图像文件（包括：bmp、gif、jpg、pic、png、tif等）
     * 3-视频文件（包括：avi、mpg、mov、swf等）
     * 4-音频文件（包括：wav、aif、au、mp3、ram等）
     * 5-压缩文件（包括：rar、zip、gz、z）
     * 6-系统文件
     * 7-语言文件
     * 8-映像文件
     * 9-备份文件
     * 10-临时文件
     * 11-模板文件
     * 12-批处理文件
     */
    public enum Type {
        UN("未知文件"),
        Doc("文档文件"),
        Img("图像文件"),
        Video("视频文件"),
        Audio("音频文件"),
        Zip("压缩文件"),
        System("系统文件"),
        Language("语言文件"),
        Mapping("映像文件"),
        Backup("备份文件"),
        Temp("临时文件"),
        Model("模板文件"),
        Deal("批处理文件");

        private String text;

        Type(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
