package com.mysit.system.dao;

import com.mysit.pojo.Cost;

import java.util.List;

public interface ICostDao {
    List<Cost> queryCostList(Cost cost);

    int addCost(Cost cost);

    int updateCost(Cost cost);

    int deleteCost(Cost cost);

    Cost queryOneCost(int costId);
}
