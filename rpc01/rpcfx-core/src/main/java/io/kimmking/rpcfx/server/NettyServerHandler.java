package io.kimmking.rpcfx.server;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import lombok.extern.slf4j.Slf4j;
import static java.nio.charset.StandardCharsets.UTF_8;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Auther: hafizgoo
 * @Date: DATE−2022/7/3 - MONTH−07 - DAY−03 - TIME−20:44
 * @Description: io.kimmking.rpcfx.server
 * @version: 1.0
 */

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private final RpcfxInvoker invoker;

    public NettyServerHandler(RpcfxInvoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpContent content = (HttpContent)msg;
        final String byteBuf = content.content().toString(UTF_8);
        //RpcfxRqeust反序列化
        RpcfxRequest rpcRequest = JSON.parseObject(byteBuf ,RpcfxRequest.class);
        log.info("RpcfxRequest serializer : " + rpcRequest.toString());
        final RpcfxResponse response = invoker.invoke(rpcRequest);
        final String responseJson = JSON.toJSONString(response);

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(responseJson.getBytes()));
        httpResponse.headers().set("Content-Type", "application/json");
        httpResponse.headers().setInt("Content-Length", responseJson.length());
        ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE).sync();

    }
}
