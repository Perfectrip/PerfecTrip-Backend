package com.ssafy.enjoytrip.attraction.model;

import java.math.BigDecimal;

public class AttractionDto {
	private int contentId;
	private int contentTypeId;

	private String title;
	private String addr1;
	private String zipCode;
	private String firstImage;
	private String firstImage2;
	private int readCount;

	private BigDecimal latitude;
	private BigDecimal longitude;

	private String overview;

	public AttractionDto() {
	}

	public AttractionDto(int contentId, int contentTypeId, String title, String addr1, String zipCode,
			String firstImage, String firstImage2, int readCount, BigDecimal latitude, BigDecimal longitude,
			String overview) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.addr1 = addr1;
		this.zipCode = zipCode;
		this.firstImage = firstImage;
		this.firstImage2 = firstImage2;
		this.readCount = readCount;
		this.latitude = latitude;
		this.longitude = longitude;
		this.overview = overview;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getFirstImage2() {
		return firstImage2;
	}

	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttractionDto [contentId=").append(contentId).append(", contentTypeId=").append(contentTypeId)
				.append(", title=").append(title).append(", addr1=").append(addr1).append(", zipCode=").append(zipCode)
				.append(", firstImage=").append(firstImage).append(", firstImage2=").append(firstImage2)
				.append(", readCount=").append(readCount).append(", latitude=").append(latitude).append(", longitude=")
				.append(longitude).append(", overview=").append(overview).append("]");
		return builder.toString();
	}

}
