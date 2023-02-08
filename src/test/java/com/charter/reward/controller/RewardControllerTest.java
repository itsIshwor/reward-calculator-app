package com.charter.reward.controller;

import com.charter.reward.model.Reward;
import com.charter.reward.service.RewardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardControllerTest {

    @Mock
    private RewardService rewardService;

    @InjectMocks
    private RewardReSource rewardReSource;

    @Test
    public void testGetRewardsForCustomerId() {
        Reward reward = new Reward();
        when(rewardService.rewardByCustomerId(anyLong())).thenReturn(reward);

        ResponseEntity<List<Reward>> response = rewardReSource.getRewards(java.util.Optional.of((1L)));
        List<Reward> rewards = response.getBody();
        assertEquals(Collections.singletonList(reward), rewards);
    }

    @Test
    public void testGetRewardsForAll() {
        List<Reward> rewards = List.of(new Reward(), new Reward());
        when(rewardService.calculateAllReward()).thenReturn(rewards);

        ResponseEntity<List<Reward>> response = rewardReSource.getRewards(null);
        List<Reward> responseRewards = response.getBody();
        assertEquals(rewards, responseRewards);
    }
}
