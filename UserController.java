package com.dan.stockapp.controller;

import com.dan.stockapp.model.Stock;
import com.dan.stockapp.model.StockPersistence;
import com.dan.stockapp.model.User;
import com.dan.stockapp.model.UserPersistence;
import com.dan.stockapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created By Dan on 2017/10/20
 */

@Controller
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(UserController.class);
    @RequestMapping("/name")
    public List<User> FetchByUserName() {
        List userList = new ArrayList();
        User obj = new User();
        obj.setUserName("Annie");
        obj.setPassword("1234");
        obj.setUserId(1);

        userList.add(obj);

        User obj2 = new User();
        obj2.setUserName("blake");
        obj2.setPassword("89000");
        obj2.setUserId(2);
        userList.add(obj2);

        return userList;
    }

    @RequestMapping("/name/{id}")
    public String FetchByUserID(@PathVariable(value = "id") int userId) {
        return "getted" + userId;
    }

    @RequestMapping(value="/name", method= RequestMethod.POST)
    public User createUser(@RequestBody User userObj) {
        return userService.createUser(userObj);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserPersistence createUser(@RequestBody UserPersistence userPersistence) {
        log.info("name is {}", userPersistence.getUserName());
        log.info("id is {}", userPersistence.getUserId());
        Set<StockPersistence> stocks = new HashSet<StockPersistence>();
        for(StockPersistence stock : userPersistence.getStocks()) {
            stock.setUserPersistenceSet(userPersistence);
            stocks.add(stock);
        }

        userPersistence.setStocks(stocks);

        return userService.createUser(userPersistence);

    }

//    @RequestMapping(value = "/stock", method = RequestMethod.POST)
//    public StockPersistence createStock(@RequestBody StockPersistence stockPersistence) {
//        log.info("name is {}", stockPersistence.getStockName());
//        log.info("price is {}", stockPersistence.getStockPrice());
//        log.debug("id is {}", stockPersistence.getStockId());
//
//        return userService.createStock(stockPersistence);
//
//    }
}
