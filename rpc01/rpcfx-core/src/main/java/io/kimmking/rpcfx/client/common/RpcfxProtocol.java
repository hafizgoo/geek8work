package io.kimmking.rpcfx.client.common;


import lombok.Data;

@Data
public class RpcfxProtocol {
    /**
     * 数据大小
     */
    private int len;

    /**
     * 数据内容
     */
    private byte[] content;
}
