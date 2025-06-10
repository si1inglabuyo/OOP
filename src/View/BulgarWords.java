package Model;

import java.util.Arrays;
import java.util.List;

public class BulgarWords {

    // List of inappropriate or flagged words (Filipino + English)
    private static final List<String> BULGAR_WORDS = Arrays.asList(
            "gago", "tanga", "bobo", "ulol", "putangina", "punyeta", "leche", "lintik", "tarantado", "bwisit",
            "kantot", "pekpek", "burat", "puke", "suso", "pwet", "puki", "bayag", "puchu", "tite",
            "fuck", "shit", "bitch", "asshole", "dick", "pussy", "motherfucker", "fucker", "fucking", "cunt",
            "slut", "whore", "bastard", "cock", "jerk", "douche", "suck", "retard", "stupid", "idiot",
            "damn", "hell", "nigga", "nigger", "dumb", "moron", "screw you", "suck my", "go to hell", "f u",
            "fu", "wtf", "bullshit", "crap", "bloody", "arse", "bugger", "wanker", "prick", "twat",
            "puta", "hayop ka", "walang hiya", "tangina", "gaga", "putang ina mo", "tae", "demonyo", "loko ka",
            "hindot", "hindutan", "salsal", "jakol", "sira ulo", "nakakabuwisit", "yawa", "ulol ka", "tanginamo",
            "titi", "manyak", "kupal", "tarantado ka", "gagong", "tanginang", "ulol na", "buwisit ka", "pakyu",
            "pakyu ka", "pakyu mo", "tang-ina", "tang ina", "lintik ka", "walang kwenta", "bobo mo", "tanga ka",
            "bastusan", "pervert", "creep", "loser", "hoe", "dog", "freak", "puta ka", "putang ina nyo"
    );

    // Method to check if a post contains any flagged words
    public static boolean containsBulgarWords(String post) {
        for (String word : BULGAR_WORDS) {
            if (post.toLowerCase().contains(word.toLowerCase())) {
                return true; // Bulgar word found
            }
        }
        return false; // No flagged words found
    }
}
