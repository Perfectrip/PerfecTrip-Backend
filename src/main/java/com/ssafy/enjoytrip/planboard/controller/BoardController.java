package com.ssafy.enjoytrip.planboard.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.planboard.model.service.BoardService;
import com.ssafy.enjoytrip.user.model.UserDto;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@PostMapping("/write")
//	public String write(@RequestBody PlanBoardDto boardDto, @RequestParam("upfile") MultipartFile[] files,
//			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
	public String write(@RequestBody PlanBoardDto boardDto, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		logger.debug("write boardDto : {}", boardDto);
		UserDto userDto = (UserDto) session.getAttribute("userinfo");
		boardDto.setUserId(userDto.getId());

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

		boardService.writeArticle(boardDto);
		redirectAttributes.addAttribute("pgno", "1");
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		return "redirect:/article/list";
	}

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) {
		logger.debug("list parameter pgno : {}", map.get("pgno"));

		try {
			List<PlanBoardDto> list = boardService.listArticle(map);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<PlanBoardDto>>(list, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
//		PageNavigation pageNavigation = boardService.makePageNavigation(map);
//		mav.addObject("articles", list);
//		mav.addObject("navigation", pageNavigation);
//		mav.addObject("pgno", map.get("pgno"));
//		mav.addObject("key", map.get("key"));
//		mav.addObject("word", map.get("word"));
//		mav.setViewName("board/list");
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map) {
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
//			model.addAttribute("article", boardDto);
//			model.addAttribute("pgno", map.get("pgno"));
//			model.addAttribute("key", map.get("key"));
//			model.addAttribute("word", map.get("word"));

			return new ResponseEntity<Map>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("조회 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping("/modify")
	public ResponseEntity<?> modify(PlanBoardDto boardDto, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) {
		logger.debug("modify boardDto : {}", boardDto);
		try {
			boardService.modifyArticle(boardDto);
			redirectAttributes.addAttribute("pgno", map.get("pgno"));
			redirectAttributes.addAttribute("key", map.get("key"));
			redirectAttributes.addAttribute("word", map.get("word"));

			return new ResponseEntity<String>("업데이트 성공!!!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("업데이트 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) {
		logger.debug("delete articleNo : {}", articleNo);

//		boardService.deleteArticle(articleNo, uploadPath);
		try {
			boardService.deleteArticle(articleNo);
			redirectAttributes.addAttribute("pgno", map.get("pgno"));
			redirectAttributes.addAttribute("key", map.get("key"));
			redirectAttributes.addAttribute("word", map.get("word"));

			return new ResponseEntity<String>("삭제 성공!!!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("삭제 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
