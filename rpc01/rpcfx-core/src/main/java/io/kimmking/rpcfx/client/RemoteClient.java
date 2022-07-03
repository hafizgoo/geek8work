package io.kimmking.rpcfx.client;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;

import java.io.IOException;



public interface RemoteClient {
    RpcfxResponse post(final RpcfxRequest req, final String url) throws IOException;
}
