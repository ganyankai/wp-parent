package com.catt.common.util.io;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Zip压缩工具 Created by Sebarswee on 2014/12/3.
 */
public class ZipUtils {

	/**
	 * 不可实例化
	 */
	private ZipUtils() {
	}

	/**
	 * 压缩打包
	 * 
	 * @param files
	 *            需要打包的文件或目录
	 * @param zipFile
	 *            目标文件
	 */
	public static void packEntries(File[] files, File zipFile) {
		OutputStream out = null;
		ArchiveOutputStream os = null;
		try {
			out = new FileOutputStream(zipFile);
			os = new ArchiveStreamFactory().createArchiveOutputStream("zip",
					out);

			for (File file : files) {
				putArchiveEntry(os, file, "");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (os != null) {
				IOUtils.closeQuietly(os);
			}
			if (out != null) {
				IOUtils.closeQuietly(out);
			}
		}
	}

	/**
	 * 增加压缩包项
	 * 
	 * @param archiveOutputStream
	 *            压缩包输出流
	 * @param file
	 *            待压缩文件或目录
	 * @param zipParentPath
	 *            目标路径
	 * @throws IOException
	 */
	private static void putArchiveEntry(
			ArchiveOutputStream archiveOutputStream, File file,
			String zipParentPath) throws IOException {
		if (file.exists()) {
			if (file.isFile()) {
				archiveOutputStream.putArchiveEntry(new ZipArchiveEntry(
						zipParentPath + file.getName()));
				FileInputStream inputStream = new FileInputStream(file);
				IOUtils.copy(inputStream, archiveOutputStream);
				archiveOutputStream.closeArchiveEntry();
			} else if (file.isDirectory()) {
				File[] subFiles = file.listFiles();
				if (subFiles.length == 0) {
					archiveOutputStream.putArchiveEntry(new ZipArchiveEntry(
							zipParentPath + file.getName() + "/"));
					archiveOutputStream.closeArchiveEntry();
				} else {
					for (File subFile : subFiles) {
						putArchiveEntry(archiveOutputStream, subFile,
								zipParentPath + file.getName() + "/");
					}
				}
			}
		}
	}
	
	/**
	 * 解压zip文件
	 * @param zipfilePath 	zip原文件路径
	 * @param destDir 		zip解压后存放路径 
	 * @throws Exception
	 */
	public static void unZip(String zipfilePath, String destDir) throws Exception {
		File zipfile = new File(zipfilePath);
        File folder = new File(destDir);
        if(!folder.exists()){
        	folder.mkdirs();
        }
        
        destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
        ZipArchiveInputStream is = null;
        List<String> fileNames = new ArrayList<String>();
 
        try {
            is = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipfile), 2048));
            ZipArchiveEntry entry = null;
            while ((entry = is.getNextZipEntry()) != null) {
                fileNames.add(entry.getName());
                if (entry.isDirectory()) {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                } else {
                    OutputStream os = null;
                    try {
                        os = new BufferedOutputStream(new FileOutputStream(new File(destDir, entry.getName())), 2048);
                        IOUtils.copy(is, os);
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
        }
         
    }

}
