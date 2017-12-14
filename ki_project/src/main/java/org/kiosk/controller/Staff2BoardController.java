package org.kiosk.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.kiosk.domain.Com_staff2VO;
import org.kiosk.domain.Com_teamVO;
import org.kiosk.domain.PageMaker;
import org.kiosk.domain.SearchCriteria;
import org.kiosk.domain.UserVO;
import org.kiosk.service.Com_sectionService;
import org.kiosk.service.Com_staff2Service;
import org.kiosk.service.Com_teamService;
import org.kiosk.util.UploadFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/staff2board/*")
public class Staff2BoardController {

	private static final Logger logger = LoggerFactory.getLogger(Staff2BoardController.class);

	@Inject
	private Com_staff2Service service;

	@Inject
	private Com_sectionService sectionService;

	@Inject
	private Com_teamService teamService;

	@Resource(name = "PageMaker")
	private PageMaker pageMaker;

	@Resource(name = "UploadFileUtils")
	private UploadFileUtils uploadFileUtils;

	private String img_fileName = "staff2_";
	private String[] dirPath = { "resources", "upload", "staff2" };

	private String uploadPath() {
		String uploadPath = File.separator;
		for (String path : dirPath) {
			uploadPath = uploadPath + path + File.separator;
		}
		return uploadPath;
	}

	// 필요에 따라 arraylist로 원하는 항목을 add 하여 array 변환하면 유동적인 path를 생성할수있다.

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {

		logger.info("staff2board/list - GET");
		logger.info("test-" + cri.toString());

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("login");
		logger.info("Login : " + userVO.toString());

		pageMaker.setCri(cri);

		if (userVO.getAuth() == 1 || cri.getSection_cd() == null) {
			cri.setSection_cd(userVO.getSection_fullcode());
		}

		pageMaker.setTotalCount(service.listSearchCount(cri));

		model.addAttribute("login", userVO);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("uploadPath", uploadPath());
		model.addAttribute("sectionService", sectionService.listAll());
		model.addAttribute("list", service.listSearchCriteria(cri));
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("st_no") int st_no, @ModelAttribute("cri") SearchCriteria cri, Model model,
			HttpServletRequest request) throws Exception {
		logger.info("staff2board/readPage - GET");
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("login");
		model.addAttribute("login", userVO);
		model.addAttribute("uploadPath", uploadPath());
		logger.info("Login : " + userVO.toString());

		model.addAttribute(service.read(st_no));
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registGET(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {
		logger.info("staff2board/register - GET");
		logger.info("regist get ...........");
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("login");
		model.addAttribute("login", userVO);
		model.addAttribute("sectionService", sectionService.listAll());
		logger.info("Login : " + userVO.toString());

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(Com_staff2VO board, RedirectAttributes rttr,
			@RequestParam("imgFile") MultipartFile imgFile, HttpServletRequest request) throws Exception {
		logger.info("staff2board/register - POST");
		logger.info("regist post ...........");
		logger.info(board.toString());
		Com_teamVO teamVO = teamService.readTeamCd(board.getSection_cd(), board.getClass_nm());
		board.setReal_use_dep_nm(sectionService.readSectionNm(board.getSection_cd()));
		board.setTeam_cd(teamVO.getTeam_cd());
		board.setSt_sort(service.createSortNo(board));
		String root_path = request.getSession().getServletContext().getRealPath("/");

		String img_filenm = uploadFileUtils.uploadImageFile(root_path, imgFile.getOriginalFilename(),
				imgFile.getBytes(), img_fileName + (service.lastInsertID()), dirPath);
		board.setImg_filenm(img_filenm);
		service.regist(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/staff2board/list?page=1";
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(int st_no, @ModelAttribute("cri") SearchCriteria cri, Model model,
			HttpServletRequest request) throws Exception {
		logger.info("staff2board/modifyPage - GET");
		model.addAttribute(service.read(st_no));
		logger.info(service.read(st_no).toString());
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("login");
		model.addAttribute("login", userVO);
		model.addAttribute("sectionService", sectionService.listAll());
		logger.info("Login : " + userVO.toString());
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(Com_staff2VO board, SearchCriteria cri, RedirectAttributes rttr,
			@RequestParam("imgFile") MultipartFile imgFile, HttpServletRequest request,
			@RequestParam("imgName") String imgName) throws Exception {
		logger.info("staff2board/modifyPage - POST");
		logger.info(cri.toString());
		Com_teamVO teamVO = teamService.readTeamCd(board.getSection_cd(), board.getClass_nm());
		board.setReal_use_dep_nm(sectionService.readSectionNm(board.getSection_cd()));
		board.setTeam_cd(teamVO.getTeam_cd());
		//board.setSt_sort(teamVO.getTeam_sort());

		String img_filenm=null;
		String root_path = request.getSession().getServletContext().getRealPath("/");

		if (imgName.equals(board.getImg_filenm())) {
			img_filenm = imgName;
		} else {
			uploadFileUtils.deleteFile(root_path + uploadPath(), service.read(board.getSt_no()).getImg_filenm());

			img_filenm = uploadFileUtils.uploadImageFile(root_path, imgFile.getOriginalFilename(), imgFile.getBytes(),
					img_fileName + board.getSt_no(), dirPath);
		}
		
		board.setImg_filenm(img_filenm);
		service.modify(board);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());

		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info(rttr.toString());

		return "redirect:/staff2board/list";
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("st_no") int st_no, SearchCriteria cri, RedirectAttributes rttr,
			HttpServletRequest request) throws Exception {
		logger.info("staff2board/removePage - POST");

		String root_path = request.getSession().getServletContext().getRealPath("/");

		uploadFileUtils.deleteFile(root_path + uploadPath(), service.read(st_no).getImg_filenm());
		service.remove(st_no);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/staff2board/list?page=1";
	}

}