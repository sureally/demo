package com.demo.controller;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName com.demo.controller.ExportExcelController
 * @Desciption 将数据创建为excel返回给前端
 * @Author Shu WJ
 * @DateTime 2019-08-10 19:06
 * @Version 1.0
 **/
@Controller
@RequestMapping("/excel")
public class ExportExcelController {

  @RequestMapping(value = "/export", method = RequestMethod.GET)
  @ResponseBody
  public void exportExcel(HttpServletResponse response) {
    creatExcel(response);
  }


  private void creatExcel(HttpServletResponse response) {
    String[] title={"x","xx","xx"};

    XSSFWorkbook wb =new XSSFWorkbook();
    XSSFSheet sheet=wb.createSheet();
    XSSFRow row=sheet.createRow(0);
    XSSFCell cell=null;

    for(int i=0;i<title.length;i++){
      cell=row.createCell(i);
      cell.setCellValue(title[i]);
    }

    for (int i=1;i<=10;i++){
      XSSFRow nrow=sheet.createRow(i);
      XSSFCell ncell=nrow.createCell(0);
      ncell.setCellValue(""+i);
      ncell=nrow.createCell(1);
      ncell.setCellValue("user"+i);
      ncell=nrow.createCell(2);
      ncell.setCellValue("24");
    }

    try (OutputStream outputStream = response.getOutputStream()) {
      // 对应的是.xls类型。
      response.setContentType("application/vnd.ms-excel;charset=utf-8");
      // 浏览器识别Content-Disposition，get方法可以直接输入url然后直接下载
      response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("aa" + ".xls", "UTF-8"));
      response.setCharacterEncoding("UTF-8");
      wb.write(outputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
