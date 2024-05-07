package slackbot.calender;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.slack.api.bolt.jetty.SlackAppServer;

public class CalenderApplication {
    public static void main(String[] args) throws Exception {
        String slackBotToken = System.getenv("SLACK_BOT_TOKEN");
        String slackSigningSecret = System.getenv("SLACK_SIGNING_SECRET");

        AppConfig appConfig = new AppConfig();
        appConfig.setSingleTeamBotToken(slackBotToken);
        appConfig.setSigningSecret(slackSigningSecret);

        App app = new App(appConfig);
        app.command("/hello", (req, ctx) -> ctx.ack(":wave: Hello!"));

        SlackAppServer server = new SlackAppServer(app);
        server.start();
    }

}
