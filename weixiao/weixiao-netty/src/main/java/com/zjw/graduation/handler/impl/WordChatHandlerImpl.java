package com.zjw.graduation.handler.impl;

import com.zjw.graduation.enums.MsgActionEnum;
import com.zjw.graduation.handler.ChatHandler;
import com.zjw.graduation.pojo.DataContent;
import com.zjw.graduation.server.NettyServerHandler;
import com.zjw.graduation.server.UserChannelRel;
import com.zjw.graduation.util.JsonUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

@Component
public class WordChatHandlerImpl implements ChatHandler {
    @Override
    public void handler(DataContent dataContent, Channel currentChannel) {
        Long receiverId = dataContent.getChatInfo().getReceiverId();
        Channel channel = UserChannelRel.get(receiverId);
        if(channel != null){
            Channel receiverChannel = NettyServerHandler.clients.find(channel.id());
            if (receiverChannel != null){
                receiverChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(dataContent)));
            }
        }
    }

    @Override
    public Integer getType() {
        return MsgActionEnum.WORD.getType();
    }
}
