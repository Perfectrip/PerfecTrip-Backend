package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {
	private AttractionMapper attractionMapper;
	
	@Autowired
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	@Override
	public AttractionDto getAttraction(String contentId) throws Exception {
		return attractionMapper.getAttraction(contentId);
	}

	@Override
	public List<AttractionDto> searchAttractionBySidoAndGugunAndContentTypeId(int sidoCode, int gugunCode,
			int contentTypeId) throws Exception {
		return attractionMapper.searchAttractionBySidoAndGugunAndContentTypeId(sidoCode, gugunCode, contentTypeId);
	}

	@Override
	public List<AttractionDto> searchAttractionByKeyword(int sidoCode, int gugunCode, String keyword) throws Exception {
		return attractionMapper.searchAttractionByKeyword(sidoCode, gugunCode, keyword);
	}

}
