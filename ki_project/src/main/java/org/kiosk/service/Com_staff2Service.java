package org.kiosk.service;

import java.util.List;

import org.kiosk.domain.Com_staff2VO;
import org.kiosk.domain.Criteria;
import org.kiosk.domain.SearchCriteria;

public interface Com_staff2Service {

	public void regist(Com_staff2VO vo) throws Exception;

	public Com_staff2VO read(Integer st_no) throws Exception;

	public void modify(Com_staff2VO vo) throws Exception;

	public void remove(Integer st_no) throws Exception;

	public List<Com_staff2VO> listAll() throws Exception;

	public int lastInsertID() throws Exception;

	public List<Com_staff2VO> listCriteria(Criteria cri) throws Exception;

	public int listCountCriteria(Criteria cri) throws Exception;
	
	public List<Com_staff2VO> listSearchCriteria(SearchCriteria cri) throws Exception;

	public List<Com_staff2VO> superListSearchCriteria(SearchCriteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	public int superListSearchCount(SearchCriteria cri) throws Exception;

}
