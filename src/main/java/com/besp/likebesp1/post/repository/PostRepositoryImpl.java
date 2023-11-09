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
    public long insert(PostDto dto) {
        sm.insert("Post_insert", dto);
        return dto.getPostId();  // 삽입된 게시글의 id를 반환
    }

    @Override
    public List<PostDto> getList(PostDto dto, int startIndex, int endIndex) {
        Map<String, Object> params = new HashMap<>();
        params.put("dto", dto);
        params.put("startIndex", startIndex);
        params.put("endIndex", endIndex);
        return sm.selectList("Post_getList", params);
    }

    @Override
    public PostDto getPost(long postId, long boardId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("boardId", boardId);
        return sm.selectOne("Post_getPost", params);
    }

    @Override
    public void update(PostDto dto) {
        sm.update("Post_update", dto);
    }

    @Override
    public void delete(long postId, long boardId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("boardId", boardId);
        sm.delete("Post_delete", params);
    }

    @Override
    public int getTotalPosts(long boardId) {
        return sm.selectOne("Post_getTotalPosts", boardId);
    }
}
