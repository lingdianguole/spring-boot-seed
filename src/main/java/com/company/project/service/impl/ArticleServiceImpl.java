package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.ArticleMapper;
import com.company.project.model.Article;
import com.company.project.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/06.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
}
