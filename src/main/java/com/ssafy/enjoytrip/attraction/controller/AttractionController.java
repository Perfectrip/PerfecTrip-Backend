package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.user.controller.UserController;
import com.ssafy.enjoytrip.user.model.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
public class AttractionController {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private AttractionService attractionService;
	
	@Autowired
	public  AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	@GetMapping("/searchContentType")
	@ResponseBody
	public ResponseEntity<?> searchType(@RequestBody Map<String, Integer> param){
		try {
			List<AttractionDto> list = attractionService.searchAttractionBySidoAndGugunAndContentTypeId(param.get("sidoCode"), param.get("gugunCode"), param.get("contentTypeId"));
			return new ResponseEntity<List>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("검색 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
