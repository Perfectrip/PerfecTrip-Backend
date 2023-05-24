package com.ssafy.enjoytrip.hotplace.model;

import io.swagger.annotations.ApiModelProperty;

public class HotPlaceParamDto {
	@ApiModelProperty(value = "현재 페이지 번호")
	private int pg;
	@ApiModelProperty(value = "페이지당 글갯수")
	private int spp;
	@ApiModelProperty(value = "페이지의 시작 글번호")
	private int start;

	public HotPlaceParamDto() {
		pg = 1;
		spp = 20;
	}

	public int getPg() {
		return pg;
	}

	public void setPg(int pg) {
		this.pg = pg;
	}

	public int getSpp() {
		return spp;
	}

	public void setSpp(int spp) {
		this.spp = spp;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HotPlaceParamDto [pg=").append(pg).append(", spp=").append(spp).append(", start=").append(start)
				.append("]");
		return builder.toString();
	}

}
