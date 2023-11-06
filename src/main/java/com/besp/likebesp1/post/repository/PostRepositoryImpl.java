package com.besp.likebesp1.post.repository;

import com.besp.likebesp1.post.entity.PostDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("postRepository")
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    SqlSessionTemplate sm;

    @Override
    public void insert(PostDto dto) {
        sm.insert("Post_insert", dto);
    }

    @Override
    public List<PostDto> getList(PostDto dto) {
        return sm.selectList("Post_getList", dto);
    }

    @Override
    public PostDto getPost(long postId, long boardId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("boardId", boardId);
        return sm.selectOne("Post_getPost", params);
    }

}
