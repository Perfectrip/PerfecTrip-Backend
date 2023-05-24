package com.ssafy.enjoytrip.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotPlaceParamDto;

@Mapper
public interface HotPlaceMapper {
	// 핫플레이스목록조회 GET /hotplace?pg=1
	List<HotPlaceDto> listHotPlace(HotPlaceParamDto hotPlaceParamDto) throws SQLException;

	// 핫플레이스상세조회 GET /hotplace/{placeno}
	HotPlaceDto getHotPlace(int contentId) throws SQLException;

}
