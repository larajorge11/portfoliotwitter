package com.portfolio.twitter.portfolio.service.jpa;

import com.portfolio.twitter.portfolio.model.Portfolio;
import com.portfolio.twitter.portfolio.repositories.PortfolioRepository;
import com.portfolio.twitter.portfolio.service.PortfolioService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PortfolioJpaService implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioJpaService (PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Set<Portfolio> findAll() {
        portfolioRepository.findAll();
        return null;
    }

    @Override
    public Portfolio findById(Integer id) {
        return portfolioRepository.findById(id).orElse(null);
    }
}
