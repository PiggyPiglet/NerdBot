package me.piggypiglet.nerdbot;

import me.piggypiglet.framework.Framework;
import me.piggypiglet.framework.utils.annotations.files.Config;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class NerdBot {
    private NerdBot() {
        Framework.builder()
                .main(this)
                .commandPrefix("!")
                .pckg("me.piggypiglet.nerdbot")
                .file(true, "config", "/config.json", "./config.json", Config.class)
                .build()
                .init();
    }

    public static void main(String[] args) {
        new NerdBot();
    }
}
