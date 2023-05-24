package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;

import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotPlaceParamDto;

public interface HotPlaceService {
	List<HotPlaceDto> listHotPlace(HotPlaceParamDto hotPlaceParamDto) throws Exception;

	HotPlaceDto getHotPlace(int contentId) throws Exception;
}
