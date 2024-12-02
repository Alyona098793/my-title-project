package com.exsample.myproject.repos;

import com.exsample.myproject.domain.TitleBlog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TitleRepo  extends CrudRepository<TitleBlog, Integer>{
    List<TitleBlog> findByTitleTag(String titleTag);
}
