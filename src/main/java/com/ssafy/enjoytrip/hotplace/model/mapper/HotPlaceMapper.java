package com.ssafy.enjoytrip.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotPlaceParamDto;

@Mapper
public interface HotPlaceMapper {
	List<HotPlaceDto> listHotPlace(HotPlaceParamDto hotPlaceParamDto) throws SQLException;

	HotPlaceDto getHotPlace(int contentId) throws SQLException;

	void increaseReadCount(int contentId) throws SQLException;
}
