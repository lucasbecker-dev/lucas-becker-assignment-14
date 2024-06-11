package com.example.coderscampus.assignment14.web;

import com.example.coderscampus.assignment14.domain.Channel;
import com.example.coderscampus.assignment14.domain.Message;
import com.example.coderscampus.assignment14.service.ChannelService;
import com.example.coderscampus.assignment14.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/channels")
public class ChannelWebController {

    private final ChannelService channelService;
    private final MessageService messageService;

    @Autowired
    public ChannelWebController(ChannelService channelService, MessageService messageService) {
        this.channelService = channelService;
        this.messageService = messageService;
    }



    @GetMapping("/{channelId}")
    public ModelAndView channel(@PathVariable Long channelId) {
        ModelAndView modelAndView = new ModelAndView("channel");
        Channel channel = channelService.findById(channelId);
        modelAndView.addObject("channel", channel);
        modelAndView.addObject("messages", messageService.findByChannel(channel));
        return modelAndView;
    }

    @PostMapping("/{channelId}")
    public String createMessage(@PathVariable Long channelId, @RequestParam String content) {
        Message message = new Message();
        Channel channel = channelService.findById(channelId);
        message.setContent(content);
        message.setChannel(channel);
        messageService.save(message);
        return "redirect:/channels/" + channelId;
    }
}
