package io.kimmking.rpcfx.client.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: hafizgoo
 * @Date: DATE−2022/7/3 - MONTH−07 - DAY−03 - TIME−20:19
 * @Description: io.kimmking.rpcfx.client.common
 * @version: 1.0
 */


public class RpcfxEncoder extends MessageToByteEncoder<RpcfxProtocol> {

    protected void encode(ChannelHandlerContext ctx, RpcfxProtocol msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
