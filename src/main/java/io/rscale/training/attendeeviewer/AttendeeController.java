package io.rscale.training.attendeeviewer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AttendeeController {

	private RosterClient attendeeClient;
	private CompanyClient companyClient;
	
	@Autowired
	public AttendeeController(RosterClient attendeeClient, CompanyClient companyClient) {
		this.attendeeClient = attendeeClient;
		this.companyClient = companyClient;
	}
	
    @RequestMapping("/")
    public String attendees(@RequestParam(value="page", required=false, defaultValue= "0") int page, Model model) {
        model.addAttribute("attendees", attendeeClient.getAttendees(page).getContent());
        return "list";
    }

    @RequestMapping("/{attendeeId}")
    public String attendee(@PathVariable(value="attendeeId") String attendeeId, Model model) {
        Attendee attendee = attendeeClient.getAttendee(attendeeId);
    	model.addAttribute("attendee", attendee);
    	if ( ! StringUtils.isEmpty(attendee.getCompanyGuid()) ) {
    		model.addAttribute("company", companyClient.getCompany(attendee.getCompanyGuid()));
    	}
        return "detail";
    }
    
}