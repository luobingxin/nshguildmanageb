-- 插入预设角色
INSERT INTO roles (name, description, hierarchy) VALUES
                                                     ('开发者', '拥有系统所有权限', 1),
                                                     ('系统管理员', '管理对外服务功能', 2),
                                                     ('超级管理员', '创建管理解散帮会', 3),
                                                     ('帮会管理员', '管理帮会成员', 4),
                                                     ('普通成员', '基本帮会成员', 5);

-- 插入预设权限
INSERT INTO permissions (name, description, code) VALUES
                                                      ('全局访问权限', 'Full system access', 'admin.access'),
                                                      ('创建帮会权限', 'Create a new guild', 'guild.create'),
                                                      ('管理帮会权限', 'Manage existing guilds', 'guild.manage'),
                                                      ('成员加入/退出帮会权限', 'Join or leave a guild', 'guild.join.leave'),
                                                      ('生成邀请码权限', 'Generate invitation codes', 'invitation.code.generate'),
                                                      ('用户注册权限', 'Register as a user', 'user.register');

-- 插入初始用户
INSERT INTO users (id, username, email, password) VALUES
                                                      ('dev_user', 'developer', 'developer@example.com', '$2a$10$eACCYoU2uK7LgVWfTgi8qeaScdmCnnZt.0B6xpucRgZJVBjiFVOcG'), -- 密码为 "password"
                                                      ('sys_admin', 'sysadmin', 'sysadmin@example.com', '$2a$10$eACCYoU2uK7LgVWfTgi8qeaScdmCnnZt.0B6xpucRgZJVBjiFVOcG'); -- 密码为 "password"

-- 分配角色给初始用户
INSERT INTO user_role (user_id, role_id) VALUES
                                             ('dev_user', 1), -- 开发者角色
                                             ('sys_admin', 2); -- 系统管理员角色

-- 给角色分配权限
-- 开发者角色拥有所有权限
INSERT INTO role_permission (role_id, permission_id)
SELECT 1, id FROM permissions;

-- 系统管理员权限
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (2, (SELECT id FROM permissions WHERE code = 'admin.access')),
                                                         (2, (SELECT id FROM permissions WHERE code = 'guild.create')),
                                                         (2, (SELECT id FROM permissions WHERE code = 'guild.manage'));

-- 超级管理员权限
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (3, (SELECT id FROM permissions WHERE code = 'guild.create')),
                                                         (3, (SELECT id FROM permissions WHERE code = 'guild.manage')),
                                                         (3, (SELECT id FROM permissions WHERE code = 'invitation.code.generate'));

-- 帮会管理员权限
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (4, (SELECT id FROM permissions WHERE code = 'guild.join.leave')),
                                                         (4, (SELECT id FROM permissions WHERE code = 'invitation.code.generate'));

-- 普通成员权限
INSERT INTO role_permission (role_id, permission_id) VALUES
    (5, (SELECT id FROM permissions WHERE code = 'guild.join.leave'));