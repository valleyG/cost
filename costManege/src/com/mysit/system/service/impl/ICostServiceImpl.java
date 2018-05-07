package com.mysit.system.service.impl;

import com.mysit.pojo.Cost;
import com.mysit.system.dao.ICostDao;
import com.mysit.system.dao.impl.ICostDaoImpl;
import com.mysit.system.service.ICostService;

import java.util.List;

public class ICostServiceImpl implements ICostService {
    ICostDao costDao = new ICostDaoImpl();

    @Override
    public List<Cost> queryCostList(Cost cost) {
        return costDao.queryCostList(cost);
    }

    @Override
    public boolean addCost(Cost cost) {
        int rows = costDao.addCost(cost);
        if (rows > 0)
            return true;
        return false;
    }

    @Override
    public boolean updateCost(Cost cost) {
        int rows = costDao.updateCost(cost);
        if (rows > 0)
            return true;
        return false;
    }

    @Override
    public boolean deleteCost(Cost cost) {
        int rows = costDao.deleteCost(cost);
        if(rows>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Cost queryOneCost(int costId) {
        return costDao.queryOneCost(costId);
    }
}
