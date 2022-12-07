package org.example.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Builder extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("builder")) {
            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle(Objects.requireNonNull(event.getOption("title")).getAsString());
            embed.setDescription(Objects.requireNonNull(event.getOption("description")).getAsString());

            Message.Attachment attachment;

            if (event.getOption("thumbnail") != null) {
                attachment = Objects.requireNonNull(event.getOption("thumbnail")).getAsAttachment();
                embed.setThumbnail(attachment.getUrl());
            }

            if (event.getOption("image") != null) {
                attachment = Objects.requireNonNull(event.getOption("image")).getAsAttachment();
                embed.setThumbnail(attachment.getUrl());
            }

            event.replyEmbeds(embed.build()).queue();
        }
    }
}
