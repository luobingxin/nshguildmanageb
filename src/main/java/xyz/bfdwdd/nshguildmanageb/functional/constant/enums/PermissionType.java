package xyz.bfdwdd.nshguildmanageb.functional.constant.enums;

/**
 * 权限类型枚举 / Permission Type Enum
 */
public enum PermissionType {
    /**
     * 全局访问权限 / Full system access
     */
    ADMIN_ACCESS("admin.access"),

    /**
     * 创建帮会权限 / Create a new guild
     */
    GUILD_CREATE("guild.create"),

    /**
     * 管理帮会权限 / Manage existing guilds
     */
    GUILD_MANAGE("guild.manage"),

    /**
     * 成员加入/退出帮会权限 / Join or leave a guild
     */
    GUILD_JOIN_LEAVE("guild.join.leave"),

    /**
     * 生成邀请码权限 / Generate invitation codes
     */
    INVITATION_CODE_GENERATE("invitation.code.generate"),

    /**
     * 用户注册权限 / Register as a user
     */
    USER_REGISTER("user.register");

    private final String code;

    PermissionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}