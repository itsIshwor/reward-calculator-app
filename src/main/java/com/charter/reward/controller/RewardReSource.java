package com.charter.reward.controller;

import com.charter.reward.model.Reward;
import com.charter.reward.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/rewards")
public class RewardReSource {

    @Autowired
    private  RewardService rewardService;

    @GetMapping
    public ResponseEntity<List<Reward>> getRewards(@RequestParam Optional<Long> customerId) {
        List<Reward> rewards = customerId.isPresent()?
                List.of(rewardService.rewardByCustomerId(customerId.get())) :
                rewardService.calculateAllReward();
        if(customerId.isPresent())log.info("CustomerIdIs Present:::::::{}",customerId.get());
        return new ResponseEntity(rewards, HttpStatus.OK);
    }
}
