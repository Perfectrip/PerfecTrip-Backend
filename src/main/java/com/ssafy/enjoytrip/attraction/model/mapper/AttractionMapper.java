package com.ssafy.enjoytrip.attraction.model.mapper;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;

@Mapper
public interface AttractionMapper {
	AttractionDto getAttraction(String contentId) throws SQLException;

	List<AttractionDto> searchAttractionBySidoAndGugunAndContentTypeId(int sidoCode, int gugunCode, int contentTypeId)
			throws SQLException;

	List<AttractionDto> searchAttractionByKeyword(int sidoCode, int gugunCode, String keyword) throws SQLException;
}
