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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.SidoGugunCodeDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
public class AttractionController {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private AttractionService attractionService;

	@Autowired
	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(attractionService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(attractionService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/searchContentType")
	@ResponseBody
//	public ResponseEntity<?> searchType(@RequestBody Map<String, Integer> param){
	public ResponseEntity<?> searchType(@RequestParam String sidoCode, @RequestParam String gugunCode,
			@RequestParam String contentTypeId) {
		try {
//			List<AttractionDto> list = attractionService.searchAttractionBySidoAndGugunAndContentTypeId(param.get("sidoCode"), param.get("gugunCode"), param.get("contentTypeId"));
			List<AttractionDto> list = attractionService.searchAttractionBySidoAndGugunAndContentTypeId(
					Integer.parseInt(sidoCode), Integer.parseInt(gugunCode), Integer.parseInt(contentTypeId));
			return new ResponseEntity<List>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("검색 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/searchKeyword")
	@ResponseBody
	public ResponseEntity<?> searchKeyword(@RequestParam String sidoCode, @RequestParam String gugunCode,
			@RequestParam String keyword) {
		try {
			List<AttractionDto> list = attractionService.searchAttractionByKeyword(Integer.parseInt(sidoCode),
					Integer.parseInt(gugunCode), keyword);
			return new ResponseEntity<List>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("검색 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/{contentId}")
	public ResponseEntity<?> getAttractionById(@PathVariable("contentId") String contentId) {
		logger.debug("getAttractionById : {}", contentId);
		AttractionDto attractionDto;
		
		try {
			attractionDto = attractionService.getAttraction(contentId);
			return new ResponseEntity<AttractionDto>(attractionDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("조회 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
