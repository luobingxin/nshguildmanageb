package xyz.bfdwdd.nshguildmanageb.extended.vote.entity;

/**
 * 投票类型枚举 / Vote Type Enum
 */
public enum VoteType {
    /**
     * 开启军功点转换功能 / Enable Military Merit Conversion
     */
    ENABLE_MERIT_CONVERSION("enable.merit.conversion"),

    /**
     * 开启普通商城功能 / Enable Ordinary Shop
     */
    ENABLE_ORDINARY_SHOP("enable.ordinary.shop");

    private final String code;

    VoteType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}