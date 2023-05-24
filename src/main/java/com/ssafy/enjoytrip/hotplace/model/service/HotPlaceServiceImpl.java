package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotPlaceParamDto;
import com.ssafy.enjoytrip.hotplace.model.mapper.HotPlaceMapper;

@Service
public class HotPlaceServiceImpl implements HotPlaceService {
	private HotPlaceMapper hotPlaceMapper;

	@Autowired
	public HotPlaceServiceImpl(HotPlaceMapper hotPlaceMapper) {
		super();
		this.hotPlaceMapper = hotPlaceMapper;
	}

	@Override
	public List<HotPlaceDto> listHotPlace(HotPlaceParamDto hotPlaceParamDto) throws Exception {
		int start = hotPlaceParamDto.getPg() == 0 ? 0 : (hotPlaceParamDto.getPg() - 1) * hotPlaceParamDto.getSpp();
		hotPlaceParamDto.setStart(start);
		return hotPlaceMapper.listHotPlace(hotPlaceParamDto);
	}

	@Override
	public HotPlaceDto getHotPlace(int contentId) throws Exception {
		return hotPlaceMapper.getHotPlace(contentId);
	}

}
