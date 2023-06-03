package SpeakingClockController;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/speaking-clock")
public class speakingClockController {
    private speakingClockService speakingClockService;

    @Autowired
    public speakingClockController(speakingClockService speakingClockService) {
        this.speakingClockService = speakingClockService;
    }

    @GetMapping("/current-time")
    @ResponseBody
    public String getCurrentTimeInWords() {
        String currentTime = speakingClockService.getCurrentTime();
        return speakingClockService.convertTimeToWords(currentTime);
    }

    @GetMapping("/convert/{time}")
    @ResponseBody
    public String convertTimeToWords(@PathVariable("time") String time) {
        return speakingClockService.convertTimeToWords(time);
    }
}