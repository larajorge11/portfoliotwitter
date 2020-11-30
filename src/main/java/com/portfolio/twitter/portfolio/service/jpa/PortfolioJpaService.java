package com.portfolio.twitter.portfolio.service.jpa;

import com.portfolio.twitter.portfolio.model.Portfolio;
import com.portfolio.twitter.portfolio.repositories.PortfolioRepository;
import com.portfolio.twitter.portfolio.service.PortfolioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioJpaService implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioJpaService (PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public List<Portfolio> findAll() {
        List<Portfolio> portfolios = new ArrayList<>();
        portfolioRepository.findAll().forEach(portfolios::add);
        return portfolios;
    }

    @Override
    public Portfolio findById(Integer id) {
        return portfolioRepository.findById(id).orElse(null);
    }

    @Override
    public Portfolio save(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }
}
