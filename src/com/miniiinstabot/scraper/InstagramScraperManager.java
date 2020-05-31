package com.miniiinstabot.scraper;

import com.miniiinstabot.network.HttpClient;
import java.io.IOException;
import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.model.Account;

public class InstagramScraperManager {

    public Account getAccountByUsername(String username) throws IOException {
        Instagram instagram = new Instagram(new HttpClient().httpClient);
        Account account = instagram.getAccountByUsername(username);
        return account;
    }
}
