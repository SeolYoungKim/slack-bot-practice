package slackbot.slackbolt;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.*;
import static com.slack.api.model.block.element.BlockElements.*;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.slack.api.bolt.jetty.SlackAppServer;

public class SlackBoltApplication {
    public static void main(String[] args) throws Exception {
        String slackBotToken = System.getenv("SLACK_BOT_TOKEN");
        String slackSigningSecret = System.getenv("SLACK_SIGNING_SECRET");

        AppConfig appConfig = new AppConfig();
        appConfig.setSingleTeamBotToken(slackBotToken);
        appConfig.setSigningSecret(slackSigningSecret);

        App app = new App(appConfig);
        app.command("/hello", (req, ctx) -> ctx.ack(":wave: Hello!"));
        app.command("/ping", (req, ctx) -> ctx.ack(asBlocks(
                section(section -> section.text(markdownText(":thumbsup: pong"))),
                actions(actions -> actions
                        .elements(asElements(
                                button(b -> b.actionId("ping-again").text(plainText(pt -> pt.text("Ping"))).value("ping"))
                        ))
                )
        )));

        SlackAppServer server = new SlackAppServer(app);
        server.start();
    }

}
