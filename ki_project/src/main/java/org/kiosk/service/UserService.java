package org.kiosk.service;

import java.util.Date;
import java.util.List;

import org.kiosk.domain.UserVO;
import org.kiosk.domain.SearchCriteria;
import org.kiosk.dto.LoginDTO;

public interface UserService {

	public UserVO login(LoginDTO dto) throws Exception;

	public void keepLogin(String id, String sessionId, Date next) throws Exception;

	public UserVO checkLoginBefore(String value);

	public void newUser(UserVO vo) throws Exception;

	public void changePassword(LoginDTO dto) throws Exception;
	
	public UserVO dupCheck(LoginDTO dto) throws Exception;
	
	public UserVO read(String id) throws Exception;

	public void modify(UserVO vo) throws Exception;

	public void remove(String id) throws Exception;

	public List<UserVO> listAll() throws Exception;
	
	public List<UserVO> listSearchCriteria(SearchCriteria cri) throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;
}
