package com.company.project.service;

import com.company.project.model.Article;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/12/06.
 */
public interface ArticleService extends Service<Article> {
    @Override
    List<Article> findAll();
}
