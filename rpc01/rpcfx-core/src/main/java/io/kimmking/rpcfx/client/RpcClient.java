package io.kimmking.rpcfx.client;




public interface RpcClient {


        <T> T create(final Class<T> serviceClass, final String url);

}

