package io.rscale.training.attendeeviewer;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "roster")
public interface RosterClient {
    @RequestMapping(method = RequestMethod.GET,value = "/people")
    PagedResources<Attendee> getAttendees(@RequestParam("page") int page);

    @RequestMapping(method = RequestMethod.GET, value = "/people/{attendeeId}")
     Attendee getAttendee(@PathVariable("attendeeId") String attendeeId);
    
}