package com.cloudtimes.common.enums

enum class AgentType(val code: String, val info: String) {
    None("0", "未指定"),
    GeneralAgent("1", "普通代理"),
    CityPartner("2", "城市合伙人"),
    SpecialPartner("3", "特约合伙人"),
    SubAgent("9", "下级代理"),
}