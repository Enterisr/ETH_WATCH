package drunk.tolstoy.ethWatch.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import drunk.tolstoy.ethWatch.Services.CoinapiService;

@RestController
public class MainController {
    @Autowired
    CoinapiService coinapiService;

    @GetMapping("/")
    public boolean IsUp() {
        return true;
    }

    @GetMapping("/getethnow")
    public String GetEthNow() {

        return coinapiService.GetEthValue();
    }
}
