package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.commands.Avatar;
import org.example.commands.Builder;
import org.example.commands.Ping;

public class Bot {
    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.load();

        JDA jda = JDABuilder.createDefault(dotenv.get("DEVTOKEN"))
                .setActivity(Activity.playing("with minions"))
                .addEventListeners(new Ping(), new Avatar(), new Builder())
                .build().awaitReady();

        Guild guild = jda.getGuildById(dotenv.get("GUILD"));

        assert guild != null;
        guild.updateCommands().addCommands(
                Commands.slash("ping", "Ping command"),
                Commands.slash("avatar", "Avatar command")
                        .addOptions(
                                new OptionData(OptionType.USER, "user", "the user")
                        ),
                Commands.slash("builder", "Builder command")
                        .addOptions(
                                new OptionData(OptionType.STRING, "title", "embed title").setRequired(true),
                                new OptionData(OptionType.STRING, "description", "embed description").setRequired(true),
                                new OptionData(OptionType.ATTACHMENT, "thumbnail", "embed thumbnail"),
                                new OptionData(OptionType.ATTACHMENT, "image", "embed image")
                        )
        ).queue();
    }
}