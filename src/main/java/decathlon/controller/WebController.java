package decathlon.controller;

import decathlon.domain.DecathlonEvent;
import decathlon.domain.FinalScore;
import decathlon.domain.SingleEvent;
import decathlon.service.ScoringService;
import decathlon.domain.Performances;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    Performances performances = new Performances();
    SingleEvent event = new SingleEvent();

    private final ScoringService scoringService;

    @GetMapping("/score")
    public String scores(Model model) {
        model.addAttribute("performances", performances);
        return "scores";
    }

    @GetMapping("/scoreByEvent")
    public String scoresByEvent(Model model) {
        model.addAttribute("singleEvent", event);
        return "scoresByEvent";
    }

    @PostMapping("/score")
    public String calculateScores(@ModelAttribute("performances") Performances performances, Model model) {
        FinalScore finalScore = scoringService.calculateScores(performances);
        addPointsToAttributes(model, finalScore);
        return "scores";
    }

    @PostMapping("/scoreByEvent")
    public String calculateScoreByEvent(@ModelAttribute("singleEvent") SingleEvent event, Model model) {
        model.addAttribute("result", scoringService.calculateSingleEventScore(event));
        return "scoresByEvent";
    }

    private static void addPointsToAttributes(Model model, FinalScore finalScore) {
        model.addAttribute("hundredResult", finalScore.getHundred());
        model.addAttribute("longJumpResult", finalScore.getLongJump());
        model.addAttribute("shotPutResult", finalScore.getShotPut());
        model.addAttribute("highJumpResult", finalScore.getHighJump());
        model.addAttribute("fourHundredResult", finalScore.getFourHundred());
        model.addAttribute("hurdlesResult", finalScore.getHurdles());
        model.addAttribute("discusResult", finalScore.getDiscus());
        model.addAttribute("poleVaultResult", finalScore.getPoleVault());
        model.addAttribute("javelinResult", finalScore.getJavelin());
        model.addAttribute("longRunResult", finalScore.getLongRun());
        model.addAttribute("result", finalScore.getTotal());
    }

}
