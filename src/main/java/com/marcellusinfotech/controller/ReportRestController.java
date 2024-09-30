package com.marcellusinfotech.controller;

import jakarta.servlet.ServletOutputStream; // Use 'jakarta' if applicable
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcellusinfotech.service.ReportService;

@RestController
public class ReportRestController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception {
	    String headerKey = "Content-Disposition";
	    String headerValue = "attachment;filename=employees.xls";

	    response.setContentType("application/vnd.ms-excel");
	    response.setHeader(headerKey, headerValue);

	    reportService.generateExcel(response);
	}

}
