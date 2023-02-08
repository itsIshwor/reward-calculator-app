package com.charter.reward.service;

import com.charter.reward.model.Customer;
import com.charter.reward.model.Purchase;
import com.charter.reward.model.Reward;
import com.charter.reward.repository.CustomerRepository;
import com.charter.reward.util.Preconditions;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class RewardService {

    protected final Function<Instant, Month> INSSTANTMONTHFUNCTION =
        instant -> LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).getMonth();

    @Autowired
    private  CustomerRepository customerRepository;



    public List<Reward> calculateAllReward() {
        return this.customerRepository.findAll()
            .stream()
            .map(Customer::getId)
            .map(this::rewardByCustomerId)
            .collect(Collectors.toList());
    }

    public Reward rewardByCustomerId(@NotNull Long customerId) {

        return this.customerRepository.findById(customerId)
            .map(Customer::getPurchases)
            .map(this::calculateRewardFromPurchase)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Customer id %s not found", customerId)));
    }

    protected Reward calculateRewardFromPurchase(List<Purchase> purchases) {
        Preconditions.checkArgument(!purchases.isEmpty(),
            new ResponseStatusException(
                HttpStatus.OK, "NO ANY CUSTOMER PURCHASE"));
        final Map<Month, Long> pointsByMonth = new HashMap<>();
        purchases.stream()
            .forEach(purchase -> {
                final Month month = INSSTANTMONTHFUNCTION.apply(purchase.getTimestamp());
                pointsByMonth.put(month,
                    pointsByMonth.getOrDefault(month, 0l) + calculate(purchase.getAmount()));
            });
        return new Reward(purchases.get(0).getCustomer().getName(), pointsByMonth);
    }

    protected Long calculate(BigDecimal amount) {
        long amt = amount.longValue();
        if (amt < 50) {
            return 0l;
        } else if (amt <= 100) {
            return (amt-50);
        }
        //120 -> 2*(120-100)+
        return (2l * (amt - 100))+ 50;
    }
}
