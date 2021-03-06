package org.kiosk.persistence;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.kiosk.domain.Com_teamVO;
import org.kiosk.dto.TeamsDTO;
import org.springframework.stereotype.Repository;

@Repository
public class Com_teamDAOImpl implements Com_teamDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "org.kiosk.mapper.Com_teamMapper";

	@Override
	public void create(Com_teamVO vo) throws Exception {
		session.insert(namespace + ".create", vo);

	}

	@Override
	public Com_teamVO read(Com_teamVO vo) throws Exception {
		return session.selectOne(namespace + ".read", vo);
	}

	@Override
	public void update(Com_teamVO vo) throws Exception {
		session.update(namespace + ".update", vo);

	}

	@Override
	public void delete(Com_teamVO vo) throws Exception {
		session.delete(namespace + ".delete", vo);

	}

	@Override
	public List<Com_teamVO> list(String section_cd) throws Exception {
		return session.selectList(namespace + ".list", section_cd);
	}

	@Override
	public List<Com_teamVO> jsonList(String bureau_cd) throws Exception {
		return session.selectList(namespace + ".jsonList", bureau_cd);
	}

	@Override
	public int lastInsertSort(String section_cd) throws Exception {
		return session.selectOne(namespace + ".lastInsertSort", section_cd);
	}

	@Override
	public Com_teamVO readTeamCd(TeamsDTO dto) throws Exception {
		return session.selectOne(namespace + ".readTeamCdSort", dto);
	}

}
