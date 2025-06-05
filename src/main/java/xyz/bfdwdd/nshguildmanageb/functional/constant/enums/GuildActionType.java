package xyz.bfdwdd.nshguildmanageb.functional.constant.enums;

/**
 * 帮会操作类型枚举 / Guild Action Type Enum
 */
public enum GuildActionType {
    /**
     * 创建帮会 / Create a new guild
     */
    CREATE_GUILD,

    /**
     * 解散帮会 / Disband the current guild
     */
    DISBAND_GUILD,

    /**
     * 添加成员 / Add a member to the guild
     */
    ADD_MEMBER,

    /**
     * 移除成员 / Remove a member from the guild
     */
    REMOVE_MEMBER,

    /**
     * 设置帮主 / Assign a new guild leader
     */
    SET_LEADER,

    /**
     * 设置帮会管理员 / Assign a guild admin
     */
    SET_ADMIN;
}