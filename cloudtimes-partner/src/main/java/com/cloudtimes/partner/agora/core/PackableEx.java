package com.cloudtimes.partner.agora.core;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
