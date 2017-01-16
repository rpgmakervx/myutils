package org.easyarch.myutils.export.excel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.xssf.usermodel.*;
import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.export.excel.annotation.ExcelEntity;
import org.easyarch.myutils.export.excel.annotation.ExcelField;
import org.easyarch.myutils.format.TimeUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 16-12-17
 * 下午4:34
 */

public class ExcelHelper {
    private XSSFWorkbook xsswb;
    private ByteArrayOutputStream content;
    public static final String SUFFIX = ".xlsx";
    //控制是否能够生成表格
    public boolean option = false;

    public ExcelHelper(){
        xsswb = new XSSFWorkbook();
        content = new ByteArrayOutputStream();
    }
    /**
     * 构建excel表格，生成文件流
     * 注意 option为true的时候不再构建表格
     * @param datas
     * @param <T>
     */
    public <T> ExcelHelper build(List<T> datas) {
        if (option){
            return this;
        }
        if (CollectionUtils.isEmpty(datas)) {
            return this;
        }
        T t = datas.get(0);
        ExcelEntity entity = t.getClass().getAnnotation(ExcelEntity.class);
        if (entity == null) {
            return this;
        }
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = xsswb.createSheet(entity.table());
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        XSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = xsswb.createCellStyle();
        Field[] fields = t.getClass().getDeclaredFields();
        int index = 0;
        XSSFCell cell = null;
        for (Field field : fields) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (excelField == null) {
                continue;
            }
            cell = row.createCell(index);
            cell.setCellValue(excelField.field());
            cell.setCellStyle(style);
            index++;
        }
        //实体中没有列要放到excel表中
        if (index == 0) {
            return this;
        }
        try {
            for (index = 0; index < datas.size(); index++) {
                row = sheet.createRow(index + 1);
                iterateField(fields, datas.get(index), row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 注意 option为true的时候不再构建表格
     * @param datas
     * @param sheetname
     * @param <T>
     */
    public <T> ExcelHelper build(List<T> datas,String sheetname) {
        if (option){
            return this;
        }
        if (CollectionUtils.isEmpty(datas)) {
            return this;
        }
        T t = datas.get(0);
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = xsswb.createSheet(sheetname);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        XSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = xsswb.createCellStyle();
        Field[] fields = t.getClass().getDeclaredFields();
        int index = 0;
        XSSFCell cell = null;
        for (Field field : fields) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (excelField == null) {
                continue;
            }
            cell = row.createCell(index);
            cell.setCellValue(excelField.field());
            cell.setCellStyle(style);
            index++;
        }
        //实体中没有列要放到excel表中
        if (index == 0) {
            return this;
        }
        try {
            for (index = 0; index < datas.size(); index++) {
                row = sheet.createRow(index + 1);
                iterateField(fields, datas.get(index), row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 获得文件流
     * @return
     */
    public byte[] getExcelAsByte() {
        return content.toByteArray();
    }

    /**
     * 持久化到本地磁盘
     * @param path
     */
    public ExcelHelper disk(String path) {
        byte[] data = getExcelAsByte();
        if (ArrayUtils.isEmpty(data)){
            return this;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 输出到指定流中
     * @param output
     */
    public ExcelHelper stream(OutputStream output){
        byte[] data = getExcelAsByte();
        if (ArrayUtils.isEmpty(data)){
            return this;
        }
        try {
            output.write(data,0,data.length);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ExcelHelper complete(){
        option = true;
        if (isEmpty()){
            return this;
        }
        try {
            xsswb.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private boolean isEmpty(){
        return xsswb.getNumberOfSheets() == 0;
    }
    public void clear(){
        content = null;
        option = false;
        try {
            xsswb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        xsswb = new XSSFWorkbook();
    }
    /**
     * 遍历对象属性
     * @param fields
     * @param dto
     * @param row
     * @param <T>
     * @throws Exception
     */
    private <T> void iterateField(Field[] fields, T dto, XSSFRow row) throws Exception {
        int col = 0;
        for (int index = 0; index < fields.length; index++) {
            fields[index].setAccessible(true);
            ExcelField ef = fields[index].getAnnotation(ExcelField.class);
            if (ef == null){
                continue;
            }
            Object value = fields[index].get(dto);
            if (value instanceof Date) {
                row.createCell(col).setCellValue(TimeUtils.getTimeFormatString((Date) value));
            } else {
                row.createCell(col).setCellValue(String.valueOf(value));
            }
            col++;
        }
    }

    public void main(String[] args) {
        BigDecimal d = new BigDecimal("100.0");
        System.out.println(d.scale());
        BigDecimal e = new BigDecimal("100.00");
        System.out.println(d.compareTo(e));
    }
}
