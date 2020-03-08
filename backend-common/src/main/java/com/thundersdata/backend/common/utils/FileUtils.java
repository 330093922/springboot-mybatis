package com.thundersdata.backend.common.utils;

import com.alibaba.excel.support.ExcelTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件操作
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    /**
     * 文件删除
     *
     * @param filePath 文件路径
     */
    public static void deleteFile(String filePath) {
        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException e) {
            logger.error("删除文件失败", e);
        }
    }

    public static String tempExcelFilePath() {
        return System.getProperty("user.dir") + File.separator + tempExcelFileName();
    }

    /**
     * xlsx类型excel文件临时名称
     *
     * @return 文件名称
     */
    private static String tempExcelFileName() {
        return System.currentTimeMillis() + ExcelTypeEnum.XLSX.getValue();
    }
}
