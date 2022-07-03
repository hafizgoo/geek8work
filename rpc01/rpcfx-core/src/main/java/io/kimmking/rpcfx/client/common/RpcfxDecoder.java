package io.kimmking.rpcfx.client.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Auther: hafizgoo
 * @Date: DATE−2022/7/3 - MONTH−07 - DAY−03 - TIME−20:22
 * @Description: io.kimmking.rpcfx.client.common
 * @version: 1.0
 */


public class RpcfxDecoder extends ByteToMessageDecoder {
    private int length = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= 4) {
            if (length == 0) {
                length = in.readInt();
            }
            if (in.readableBytes() < length) {
                return;
            }
            byte[] content = new byte[length];
            if (in.readableBytes() >= length) {
                in.readBytes(content);
                RpcfxProtocol rpcfxProtocol = new RpcfxProtocol();
                rpcfxProtocol.setLen(length);
                rpcfxProtocol.setContent(content);
                out.add(rpcfxProtocol);
            }
            length = 0;
        }
    }
}
