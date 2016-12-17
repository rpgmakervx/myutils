package org.easyarch.myutils.export.excel;

import org.apache.poi.hssf.usermodel.*;
import org.easyarch.myutils.collection.CollectionUtil;
import org.easyarch.myutils.export.excel.annotation.ExcelEntity;
import org.easyarch.myutils.export.excel.annotation.ExcelField;
import org.easyarch.myutils.export.excel.entity.User;
import org.easyarch.myutils.format.TimeUtil;
import org.easyarch.myutils.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 16-12-17
 * 下午4:34
 */

public class ExcelHelper {

    private HSSFWorkbook wb;
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ByteArrayOutputStream content;
    public static final String SUFFIX = ".xls";

    public ExcelHelper() {
        wb = new HSSFWorkbook();
        content = new ByteArrayOutputStream();
    }

    public <T> ExcelHelper build(List<T> datas) {
        if (CollectionUtil.isEmpty(datas)) {
            return this;
        }
        T t = datas.get(0);
        ExcelEntity entity = t.getClass().getAnnotation(ExcelEntity.class);
        if (entity == null) {
            return this;
        }
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(entity.table());
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        Field[] fields = t.getClass().getDeclaredFields();
        int index = 0;
        HSSFCell cell = null;
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
            wb.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public byte[] getExcelAsByte() {
        return content.toByteArray();
    }

    public void disk(String path) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(getExcelAsByte());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeIO(fos);
        }
    }

    private <T> void iterateField(Field[] fields, T dto, HSSFRow row) throws Exception {
        for (int index = 0; index < fields.length; index++) {
            fields[index].setAccessible(true);
            Object value = fields[index].get(dto);
            if (value instanceof Date) {
                row.createCell(index).setCellValue(format.format((Date) value));
            } else {
                row.createCell(index).setCellValue(String.valueOf(value));
            }
        }
    }

    public static void main(String[] args) {
        ExcelHelper helper = new ExcelHelper();
        List<User> users = new ArrayList<User>();
        users.add(new User("邢天宇", 22, TimeUtil.getDateByNow(0)));
        users.add(new User("梁乙", 23, TimeUtil.getDateByNow(-1)));
        users.add(new User("季旭", 21, TimeUtil.getDateByNow(1)));
        users.add(new User("周雪原", 24, TimeUtil.getDateByNow(2)));
        helper.build(users).disk("/home/code4j/58daojia/名单" + SUFFIX);
    }
}
