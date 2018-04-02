package common.Excel;

import common.annotation.XlsHeader;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * @author: wangzx
 * @Date: 2018-03-28-14:53
 *TODO 用法
 String [] properties = {"bookId","name","number"};
*ExcelUtil.exportExcel("导出列表.xlsx",properties,list,request,response,"yyyy-MM-dd",Book.class);
 */
public class ExcelUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    public static <T> boolean exportExcel(String fileName, String[] properties, Collection<T> dataset, HttpServletRequest request, HttpServletResponse response, String pattern, Class clazz) throws Exception {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(fileName);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置样式
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        XSSFCell cell = null;
        // 设置表格标题行
        XSSFRow row = sheet.createRow(0);
        for (short i = 0; i < properties.length; i++) {
            // 获取字段头
            String property = properties[i];
            Field field = clazz.getDeclaredField(property);
            field.setAccessible(true);
            XlsHeader title = field.getAnnotation(XlsHeader.class);
            // 创建单元格
            cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(title.value());
        }

        // 遍历集合数据，设置数据内容
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = it.next();
            int i = 0;
            for (String property : properties) {
                //创建单元格
                cell = row.createCell(i++);
                cell.setCellStyle(style);
                //拼接get方法名
                String getMethodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
                Class<? extends Object> tCls = t.getClass();
                //获取方法
                Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                //调用方法返回值
                Object value = getMethod.invoke(t, new Object[]{});
                // 判断值的类型后进行强制类型转换
                if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat format = new SimpleDateFormat(pattern);
                    if (value != null) {
                        cell.setCellValue(format.format(date));
                    }
                } else {
                    // 其它数据类型都当作字符串简单处理
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }
        }

        //写入文件流
        boolean isSuccess = write(request, response, workbook, fileName, dataset.size());

        return isSuccess;
    }

    /**
     * 写入文件流
     * @param request
     * @param response
     * @param wb
     * @param fileName
     * @param total
     * @return
     */
    private static boolean write(HttpServletRequest request, HttpServletResponse response, XSSFWorkbook wb, String fileName, Integer total) {
        boolean isSuccess = false;
        OutputStream outputStream = null;
        //获取客户端浏览器和操作系统信息
        //Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko --->IE11
        //Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.149 Safari/537.36 --->谷歌
        //Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0--->火狐
        String userAgent = request.getHeader("USER-AGENT");
        try {
            String finalFileName = null;
            //fileName = fileName + DateUtils.dateToString(new Date(), "yyyy-MM-dd")+".xlsx";
            //解决文件名乱码
            //IE浏览器
            if (org.apache.commons.lang3.StringUtils.contains(userAgent, "MSIE") || org.apache.commons.lang3.StringUtils.contains(userAgent, "11.0")) {
                finalFileName = URLEncoder.encode(fileName, "UTF8");
                //google,火狐浏览器
            } else if (org.apache.commons.lang3.StringUtils.contains(userAgent, "Mozilla")) {
                finalFileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
            } else {
                //其他浏览器
                finalFileName = URLEncoder.encode(fileName, "UTF8");
            }
            //设置让浏览器弹出下载提示框，而不是直接在浏览器中打开
            response.setHeader("Content-Disposition", "attachment;filename=" + finalFileName);
            response.setContentType("application/ynd.ms-excel;charset=UTF-8");
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            LOGGER.info(fileName + total + "条");
        } catch (IOException e) {
            isSuccess = true;
            LOGGER.error("excel写入文件异常", e.getMessage());
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }
}
