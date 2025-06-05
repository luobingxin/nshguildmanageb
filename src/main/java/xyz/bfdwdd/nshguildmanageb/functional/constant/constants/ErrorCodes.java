package xyz.bfdwdd.nshguildmanageb.functional.constant.constants;

/**
 * 全局错误码常量 / Global Error Codes
 */
public class ErrorCodes {
    public static final String USER_NOT_FOUND = "USER.NOT.FOUND";
    public static final String ROLE_ALREADY_EXISTS = "ROLE.ALREADY.EXISTS";
    public static final String INVALID_TOKEN = "AUTH.INVALID.TOKEN";
    public static final String PERMISSION_DENIED = "PERMISSION.DENIED";
    public static final String GUILD_NOT_FOUND = "GUILD.NOT.FOUND";
    public static final String INVITATION_CODE_EXPIRED = "INVITATION.CODE.EXPIRED";

    // 获取中文描述
    public static String getDescription(String errorCode) {
        switch (errorCode) {
            case USER_NOT_FOUND:
                return "用户未找到";
            case ROLE_ALREADY_EXISTS:
                return "角色已存在";
            case INVALID_TOKEN:
                return "无效的 Token";
            case PERMISSION_DENIED:
                return "权限不足";
            case GUILD_NOT_FOUND:
                return "帮会未找到";
            case INVITATION_CODE_EXPIRED:
                return "邀请码已过期";
            default:
                return "未知错误";
        }
    }
}