package SocialMediaPlatform;
import Model.Database;
import View.LandingPage;

public class Main {

    public static void main(String[] args) {
        new LandingPage(new Database());
    }
}
