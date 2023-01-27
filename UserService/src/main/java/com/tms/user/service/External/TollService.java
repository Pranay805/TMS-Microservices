package com.tms.user.service.External;

import com.tms.user.service.Entities.Toll;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TOLL-SERVICE")
public interface TollService {

    @GetMapping("/tolls/{tollid}")
    Toll getToll(@PathVariable("tollid") String tollid);
}
