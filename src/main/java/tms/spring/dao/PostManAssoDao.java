package tms.spring.dao;

import org.springframework.stereotype.Repository;
import tms.spring.entity.PostManAsso;

/**
 * Created by user on 2017/11/21.
 */
@Repository
public interface PostManAssoDao {

    Long insert(PostManAsso postManAsso);
    PostManAsso selectById(Long id);
}
