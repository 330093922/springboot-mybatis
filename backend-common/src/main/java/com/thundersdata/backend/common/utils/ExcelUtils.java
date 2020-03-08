package com.thundersdata.backend.common.utils;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.thundersdata.backend.common.enums.BoolEnum;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * excel
 */
public class ExcelUtils {

    private ExcelUtils() {
    }

    public static HorizontalCellStyleStrategy getExcelStyleStrategy() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)20);
        headWriteFont.setFontName("思源黑体");
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setLocked(false);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontName("宋体");
        contentWriteFont.setFontHeightInPoints((short)15);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        contentWriteCellStyle.setLocked(false);

        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    private static String patternFileSuffix(String fileName) {
        Assert.hasText(fileName, "文件名不合法");

        int suffixIndex = fileName.lastIndexOf('.');
        int length = fileName.length();

        String suffix = null;
        if (suffixIndex != -1) {
            suffix = ExcelSuffixEnum.patternSuffix(fileName.substring(suffixIndex, length));
        }

        Assert.hasText(suffix, "文件名不合法");
        return suffix;
    }

    public static ExcelTypeEnum typeOf(String fileName) {
        String suffix = patternFileSuffix(fileName);

        ExcelTypeEnum excelTypeEnum = null;
        if (ExcelTypeEnum.XLS.getValue().equalsIgnoreCase(suffix)) {
            excelTypeEnum = ExcelTypeEnum.XLS;
        }

        if (ExcelTypeEnum.XLSX.getValue().equalsIgnoreCase(suffix)) {
            excelTypeEnum = ExcelTypeEnum.XLSX;
        }

        Objects.requireNonNull(excelTypeEnum, "获取文件类型失败");
        return excelTypeEnum;
    }

    public static String valueOfXLSX() {
        return ExcelSuffixEnum.XLSX.getName();
    }

    public static Boolean toBoolean(String boolString) {
        if (BoolEnum.YES.getDesc().equals(boolString)) {
            return true;
        }

        return false;
    }

    /**
     * excel后缀 枚举
     * @author nonfou
     *
     */
    enum ExcelSuffixEnum {
        /**
         * Excel 97-2013
         */
        XLS(".xls"),
        /**
         * Excel 2007-2019
         */
        XLSX(".xlsx");

        ExcelSuffixEnum(String name) {
            this.name = name;
        }

        private String name;
        public String getName() {
            return this.name;
        }

        public static String patternSuffix(String suffix) {
            for (ExcelSuffixEnum excelSuffixEnum : ExcelSuffixEnum.values()) {
                if (excelSuffixEnum.getName().equalsIgnoreCase(suffix)) {
                    return excelSuffixEnum.getName();
                }
            }

            return null;
        }
    }
}
