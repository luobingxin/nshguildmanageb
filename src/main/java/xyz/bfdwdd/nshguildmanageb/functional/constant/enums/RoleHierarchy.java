package xyz.bfdwdd.nshguildmanageb.functional.constant.enums;

/**
 * 角色层级枚举 / Role Hierarchy Enum
 */
public enum RoleHierarchy {
    /**
     * 开发者：最高权限，可管理所有模块 / Developer: Highest level, full access
     */
    DEVELOPER(5),

    /**
     * 系统管理员：服务级别管理权限 / System Admin: Service-level management
     */
    SYSTEM_ADMIN(4),

    /**
     * 超级管理员：帮会创建者默认身份 / Guild Super Admin: Default role for guild creators
     */
    GUILD_SUPER_ADMIN(3),

    /**
     * 帮会管理员：成员管理权限 / Guild Admin: Manage members and invites
     */
    GUILD_ADMIN(2),

    /**
     * 普通成员：基础权限 / Member: Basic membership
     */
    MEMBER(1);

    private final int priorityLevel;

    RoleHierarchy(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}