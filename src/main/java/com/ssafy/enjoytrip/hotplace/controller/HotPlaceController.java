package com.ssafy.enjoytrip.hotplace.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.hotplace.model.CommentDto;
import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotPlaceParamDto;
import com.ssafy.enjoytrip.hotplace.model.service.CommentService;
import com.ssafy.enjoytrip.hotplace.model.service.HotPlaceService;
import com.ssafy.enjoytrip.planboard.model.BoardParameterDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/hotplace")
@CrossOrigin("*")
public class HotPlaceController {
	private static final Logger logger = LoggerFactory.getLogger(HotPlaceController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private HotPlaceService hotPlaceService;
	private CommentService commentService;

	@Autowired
	public HotPlaceController(HotPlaceService hotPlaceService, CommentService commentService) {
		this.hotPlaceService = hotPlaceService;
		this.commentService = commentService;
	}

	@ApiOperation(value = "핫플레이스 목록", notes = "핫플레이스의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<?> listHotPlace(
			@ApiParam(value = "핫플레이스를 얻기위한 부가정보.", required = true) HotPlaceParamDto hotPlaceParamDto) {
		logger.info("listHotPlace - 호출");
		System.out.println(hotPlaceParamDto);
		try {
			List<HotPlaceDto> list = hotPlaceService.listHotPlace(hotPlaceParamDto);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<HotPlaceDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/{contentId}")
	public ResponseEntity<?> getHotPlaceById(@PathVariable("contentId") int contentId) {
		logger.debug("getHotPlaceById : {}", contentId);
		HotPlaceDto hotPlaceDto;

		try {
			hotPlaceDto = hotPlaceService.getHotPlace(contentId);
			return new ResponseEntity<HotPlaceDto>(hotPlaceDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("조회 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/comment")
	public ResponseEntity<String> write(@RequestBody CommentDto commentDto) {
		logger.debug("write commentDto : {}", commentDto);
		try {
			commentService.writeComment(commentDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/comment/{contentId}")
	public ResponseEntity<?> listComment(@PathVariable("contentId") int contentId) {
		logger.info("listComment - 호출");
		try {
			List<CommentDto> list = commentService.listComment(contentId);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/comment")
	public ResponseEntity<?> modify(@RequestBody CommentDto commentDto) {
		logger.info("modifyComment - 호출 {}", commentDto);
		try {
			commentService.modifyComment(commentDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> delete(@PathVariable("commentId") int commentId) {
		logger.debug("delete comment : {}", commentId);

		try {
			if (commentService.deleteComment(commentId)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
