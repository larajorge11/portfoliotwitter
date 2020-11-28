package com.portfolio.twitter.portfolio.service;

import com.portfolio.twitter.portfolio.model.Portfolio;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PortfolioMapService extends AbstractMapService<Portfolio, Long> implements PortfolioService {

    @Override
    public Set<Portfolio> findAll() {
        return super.findAll();
    }

    @Override
    public Portfolio findById(Long id) {
        return super.findById(id);
    }
}
