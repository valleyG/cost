package com.mysit.system.service;

import com.mysit.pojo.Cost;

import java.util.List;

public interface ICostService {
    List<Cost> queryCostList(Cost cost);

    boolean addCost(Cost cost);

    boolean updateCost(Cost cost);

    boolean deleteCost(Cost cost);

    Cost queryOneCost(int costId);
}
