package org.example.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Avatar extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("avatar")) {
            EmbedBuilder embed = new EmbedBuilder();
            User user;

            if (event.getOption("user") != null) {
                user = Objects.requireNonNull(event.getOption("user")).getAsUser();
            } else {
                user = event.getUser();
            }
            embed.setTitle(user.getName());
            embed.setImage(user.getAvatarUrl());

            event.replyEmbeds(embed.build()).queue();
        }
    }
}
