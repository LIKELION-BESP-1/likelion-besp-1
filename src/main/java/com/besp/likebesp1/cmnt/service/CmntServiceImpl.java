package com.besp.likebesp1.cmnt.service;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.dto.CmntInsertDto;
import com.besp.likebesp1.cmnt.repository.CmntDao;
import jakarta.annotation.Resource;
import org.apache.ibatis.javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("cmntService")
@Transactional
public class CmntServiceImpl implements CmntService{

    @Resource(name="cmntDao")
    CmntDao dao;

    //TODO: make Test

    @Override
    public List<CmntDto> getCmntsByPostId(long postId) {
        List<CmntDto> li = dao.getCmntsByPostId(postId);
        return li;
    }

    @Override
    public void insertCmnt(CmntInsertDto dto) {
        dao.insertCmnt(dto);
    }

    @Override
    public CmntDto findById(long cmntId) throws ObjectNotFoundException {

        Optional<CmntDto> optDto = dao.findById(cmntId);
        return optDto.orElseThrow(() ->
                new ObjectNotFoundException(String.format("%d에 해당하는 cmnt가 없습니다.", cmntId))
        );
    }

    @Override
    public void updateCmnt(String content, long memberId, long cmntId) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("cmntId", cmntId);

        dao.updateCmnt(map);
    }
}
