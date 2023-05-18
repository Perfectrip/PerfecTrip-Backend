package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.SidoGugunCodeDto;

public interface AttractionService {
	List<SidoGugunCodeDto> getSido() throws Exception;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

	AttractionDto getAttraction(String contentId) throws Exception;

	List<AttractionDto> searchAttractionBySidoAndGugunAndContentTypeId(int sidoCode, int gugunCode, int contentTypeId)
			throws Exception;

	List<AttractionDto> searchAttractionByKeyword(int sidoCode, int gugunCode, String keyword) throws Exception;
}
