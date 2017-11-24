package tms.spring.dao;

import tms.spring.entity.PostManAsso;

/**
 * Created by user on 2017/11/21.
 */
public interface PostManAssoDao {

    Long insert(PostManAsso postManAsso);
    PostManAsso selectById(Long id);
}
