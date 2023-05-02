package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;

public interface AttractionService {
	AttractionDto getAttraction(String contentId) throws Exception;

	List<AttractionDto> searchAttractionBySidoAndGugunAndContentTypeId(int sidoCode, int gugunCode, int contentTypeId)
			throws Exception;

	List<AttractionDto> searchAttractionByKeyword(int sidoCode, int gugunCode, String keyword) throws Exception;
}
