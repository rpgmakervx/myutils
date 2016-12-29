package org.easyarch.myutils.export.excel;

import org.apache.poi.xssf.usermodel.*;
import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.collection.CollectionUtils;
import org.easyarch.myutils.export.excel.annotation.ExcelEntity;
import org.easyarch.myutils.export.excel.annotation.ExcelField;
import org.easyarch.myutils.export.excel.entity.User;
import org.easyarch.myutils.format.TimeUtil;
import org.easyarch.myutils.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

public class ExcelUtils {

    private static XSSFWorkbook xsswb = new XSSFWorkbook();
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ByteArrayOutputStream content = new ByteArrayOutputStream();
    public static final String SUFFIX = ".xls";

    /**
     * 构建excel表格，生成文件流
     * @param datas
     * @param <T>
     */
    public static <T> void build(List<T> datas) {
        if (CollectionUtils.isEmpty(datas)) {
            return;
        }
        T t = datas.get(0);
        ExcelEntity entity = t.getClass().getAnnotation(ExcelEntity.class);
        if (entity == null) {
            return;
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
            return;
        }
        try {
            for (index = 0; index < datas.size(); index++) {
                row = sheet.createRow(index + 1);
                iterateField(fields, datas.get(index), row);
            }
            xsswb.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * 获得文件流
     * @return
     */
    public static byte[] getExcelAsByte() {
        return content.toByteArray();
    }

    /**
     * 持久化到本地磁盘
     * @param path
     */
    public static void disk(String path) {
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

    /**
     * 输出到指定流中
     * @param output
     */
    public static void stream(OutputStream output){
        byte[] data = getExcelAsByte();
        if (ArrayUtils.isEmpty(data)){
            return;
        }
        try {
            output.write(data,0,data.length);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历对象属性
     * @param fields
     * @param dto
     * @param row
     * @param <T>
     * @throws Exception
     */
    private static <T> void iterateField(Field[] fields, T dto, XSSFRow row) throws Exception {
        for (int index = 0; index < fields.length; index++) {
            fields[index].setAccessible(true);
            ExcelField ef = fields[index].getAnnotation(ExcelField.class);
            if (ef == null){
                continue;
            }
            Object value = fields[index].get(dto);
            if (value instanceof Date) {
                row.createCell(index).setCellValue(format.format((Date) value));
            } else {
                row.createCell(index).setCellValue(String.valueOf(value));
            }
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        long begin = System.currentTimeMillis();
        for (int index=0;index<50000;index++){
            users.add(new User("邢天宇", 22+index, "dfghjhdasfdsgdfbdv"+index,"1SFdsgfdhgfG1"+index,"1afsafsgds1"+index,TimeUtil.getDateOffsetByNow(-5000+index)));
        }
//        users.add(new User("梁乙", 23, TimeUtil.getDateOffsetByNow(-1)));
//        users.add(new User("季旭", 21, TimeUtil.getDateOffsetByNow(1)));
//        users.add(new User("周雪原", 24, TimeUtil.getDateOffsetByNow(2)));
        ExcelUtils.build(users);
        ExcelUtils.disk("/home/code4j/58daojia/名单" + SUFFIX);
        System.out.println("time:"+ (System.currentTimeMillis() - begin));
    }
}
