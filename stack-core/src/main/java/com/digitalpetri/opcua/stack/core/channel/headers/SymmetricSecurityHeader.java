package com.digitalpetri.opcua.stack.core.channel.headers;

import com.google.common.base.MoreObjects;
import com.digitalpetri.opcua.stack.core.util.annotations.UInt32Primitive;
import io.netty.buffer.ByteBuf;

public class SymmetricSecurityHeader {

    @UInt32Primitive
    private final long tokenId;

    /**
     * @param tokenId A unique identifier for the SecureChannel SecurityToken used to secure the Message.
     */
    public SymmetricSecurityHeader(long tokenId) {
        this.tokenId = tokenId;
    }

    public long getTokenId() {
        return tokenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SymmetricSecurityHeader that = (SymmetricSecurityHeader) o;

        return tokenId == that.tokenId;
    }

    @Override
    public int hashCode() {
        return (int) (tokenId ^ (tokenId >>> 32));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("tokenId", tokenId)
                .toString();
    }

    public static ByteBuf encode(SymmetricSecurityHeader header, ByteBuf buffer) {
        buffer.writeInt((int) header.getTokenId());

        return buffer;
    }

    public static SymmetricSecurityHeader decode(ByteBuf buffer) {
        return new SymmetricSecurityHeader(buffer.readUnsignedInt());
    }

}
