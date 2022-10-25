package com.ruoyi.common.utils;

/**
 * @author linmr
 * @description:
 * @date 2022/10/20
 */

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 解析文件工具类
 *
 * @author Yuanqiang.Zhang
 * @since 2022/7/12
 */
@SuppressWarnings("unused")
public class UnZipUtils {

    public static int BYTE_LEN = 10240;

    /**
     * 本地文件解压
     *
     * @param file 具体文件
     * @return 解压后的文件列表
     */
    public static List<UnzipFileVo> unzip(File file) {
        if (Objects.isNull(file) || !file.exists()) {
            return Collections.emptyList();
        }
        ZipFile zip = null;
        try {
            zip = new ZipFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(zip)) {
            return Collections.emptyList();
        }
        Enumeration<? extends ZipEntry> entries = zip.entries();
        List<UnzipFileVo> vos = new ArrayList<>();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String path = entry.getName();
            UnzipFileVo vo = new UnzipFileVo();
            vo.setPath(path);
            // 解析文件夹
            boolean directory = entry.isDirectory();
            if (directory) {
                vo.setType(0);
                vos.add(vo);
                continue;
            }
            // 解析文件
            vo.setType(1);
            StringBuilder sb = new StringBuilder();
            try (InputStream in = zip.getInputStream(entry);
                 InputStreamReader inputStreamReader = new InputStreamReader(in);
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            vo.setContent(sb.toString());
            vos.add(vo);
        }
        if (Objects.nonNull(zip)) {
            try {
                zip.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return vos;
    }


    /**
     * 上传文件解压
     *
     * @param multipartFile 上传文件
     * @return 解压后的文件列表
     */
    public static List<UnzipFileVo> unzip(MultipartFile multipartFile) {
        File file = getFile(multipartFile);
        if (Objects.isNull(file)) {
            return Collections.emptyList();
        }
        List<UnzipFileVo> vos = unzip(file);
        if (file.exists()) {
            boolean delete = file.delete();
            if (delete) {
                System.out.println(file.getName() + " 临时文件删除成功！");
            } else {
                System.out.println(file.getName() + " 临时文件删除失败！");
            }
        }
        return vos;
    }

    /**
     * MultipartFile 转 File
     *
     * @param multipartFile 上传文件
     * @return 本地文件
     */
    private static File getFile(MultipartFile multipartFile) {
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File("D://" + fileName);
        int len;
        try (OutputStream os = new FileOutputStream(file);
             InputStream in = multipartFile.getInputStream()) {
            byte[] buffer = new byte[BYTE_LEN];
            while ((len = in.read(buffer, 0, BYTE_LEN)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }
}