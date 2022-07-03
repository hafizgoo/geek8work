package io.kimmking.rpcfx.exception;




public class RpcFxException extends RuntimeException{

    public RpcFxException() {
        super();
    }

    public RpcFxException(String message) {
        super(message);
    }

    public RpcFxException(String message, Throwable cause) {
        super(message, cause);
    }
}
