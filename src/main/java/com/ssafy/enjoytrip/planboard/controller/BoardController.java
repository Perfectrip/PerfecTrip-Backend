package com.ssafy.enjoytrip.planboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.planboard.model.BoardParameterDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.planboard.model.service.BoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@PostMapping
//	public String write(@RequestBody PlanBoardDto boardDto, @RequestParam("upfile") MultipartFile[] files,
//			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
	public ResponseEntity<String> write(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) PlanBoardDto boardDto) {
		logger.debug("write boardDto : {}", boardDto);

		try {
			boardService.writeArticle(boardDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}

//		FileUpload 관련 설정.
//		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
//		if (!files[0].isEmpty()) {
//			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			String saveFolder = uploadPath + File.separator + today;
//			logger.debug("저장 폴더 : {}", saveFolder);
//			File folder = new File(saveFolder);
//			if (!folder.exists())
//				folder.mkdirs();
//			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
//			for (MultipartFile mfile : files) {
//				FileInfoDto fileInfoDto = new FileInfoDto();
//				String originalFileName = mfile.getOriginalFilename();
//				if (!originalFileName.isEmpty()) {
//					String saveFileName = UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					fileInfoDto.setSaveFolder(today);
//					fileInfoDto.setOriginalFile(originalFileName);
//					fileInfoDto.setSaveFile(saveFileName);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
//					mfile.transferTo(new File(folder, saveFileName));
//				}
//				fileInfos.add(fileInfoDto);
//			}
//			boardDto.setFileInfos(fileInfos);
//		}
	}

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<?> list(
			@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) {
		logger.info("listArticle - 호출");
		System.out.println(boardParameterDto);
		try {
			List<PlanBoardDto> list = boardService.listArticle(boardParameterDto);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<PlanBoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = PlanBoardDto.class)
	@GetMapping("/{articleno}")
	public ResponseEntity<?> view(@PathVariable("articleno") int articleNo, @RequestParam Map<String, String> map) {
		logger.debug("view articleNo : {}", articleNo);
		PlanBoardDto boardDto;
		try {
			boardDto = boardService.getArticle(articleNo);
			boardService.updateHit(articleNo);

			Map<String, Object> result = new HashMap<>();
			result.put("article", boardDto);

			result.put("pgno", map.get("pgno"));
			result.put("key", map.get("key"));
			result.put("word", map.get("word"));

			return new ResponseEntity<Map>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("조회 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<?> modify(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) PlanBoardDto boardDto) {
		logger.info("modifyArticle - 호출 {}", boardDto);
		try {
			boardService.modifyArticle(boardDto);
//			redirectAttributes.addAttribute("pgno", map.get("pgno"));
//			redirectAttributes.addAttribute("key", map.get("key"));
//			redirectAttributes.addAttribute("word", map.get("word"));
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}")
	public ResponseEntity<?> delete(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleNo) {
		logger.debug("delete articleNo : {}", articleNo);
		
		try {
			if (boardService.deleteArticle(articleNo)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
