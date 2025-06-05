-- 用户表
CREATE TABLE users (
                       id VARCHAR(36) PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 角色表
CREATE TABLE roles (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL UNIQUE,
                       description TEXT,
                       hierarchy INT NOT NULL CHECK (hierarchy BETWEEN 1 AND 5),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 权限表
CREATE TABLE permissions (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL UNIQUE,
                             description TEXT,
                             code VARCHAR(50) NOT NULL UNIQUE,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 用户-角色关联表
CREATE TABLE user_role (
                           user_id VARCHAR(36) NOT NULL,
                           role_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- 角色-权限关联表
CREATE TABLE role_permission (
                                 role_id BIGINT NOT NULL,
                                 permission_id BIGINT NOT NULL,
                                 PRIMARY KEY (role_id, permission_id),
                                 FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
                                 FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

-- 帮会表
CREATE TABLE guilds (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        creator_id VARCHAR(36) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 邀请码表
CREATE TABLE invitation_code (
                                 id VARCHAR(36) PRIMARY KEY,
                                 code VARCHAR(100) NOT NULL UNIQUE,
                                 query_code VARCHAR(20) NOT NULL UNIQUE,
                                 creator_id VARCHAR(36) NOT NULL,
                                 guild_id BIGINT,
                                 max_uses INT NOT NULL,
                                 current_uses INT DEFAULT 0,
                                 expires_at TIMESTAMP,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE,
                                 FOREIGN KEY (guild_id) REFERENCES guilds(id) ON DELETE SET NULL
);

-- 帮会成员表
CREATE TABLE guild_members (
                               guild_id BIGINT NOT NULL,
                               user_id VARCHAR(36) NOT NULL,
                               role VARCHAR(50) NOT NULL,
                               joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               PRIMARY KEY (guild_id, user_id),
                               FOREIGN KEY (guild_id) REFERENCES guilds(id) ON DELETE CASCADE,
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 日志表
CREATE TABLE logs (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      action VARCHAR(100) NOT NULL,
                      description TEXT,
                      user_id VARCHAR(36),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- 通知表
CREATE TABLE notifications (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               title VARCHAR(200) NOT NULL,
                               content TEXT NOT NULL,
                               type VARCHAR(50) NOT NULL,
                               target_id VARCHAR(36),
                               sender_id VARCHAR(36) NOT NULL,
                               receiver_id VARCHAR(36) NOT NULL,
                               read_status BOOLEAN DEFAULT FALSE,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
                               FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 军功点表
CREATE TABLE military_merit (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                user_id VARCHAR(36) NOT NULL,
                                guild_id BIGINT NOT NULL,
                                points INT DEFAULT 0,
                                last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                UNIQUE (user_id, guild_id),
                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                FOREIGN KEY (guild_id) REFERENCES guilds(id) ON DELETE CASCADE
);

-- 转换率设置表
CREATE TABLE conversion_rate (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                 from_guild_id BIGINT NOT NULL,
                                 to_guild_id BIGINT NOT NULL,
                                 rate DECIMAL(10,2) NOT NULL,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 UNIQUE (from_guild_id, to_guild_id),
                                 FOREIGN KEY (from_guild_id) REFERENCES guilds(id) ON DELETE CASCADE,
                                 FOREIGN KEY (to_guild_id) REFERENCES guilds(id) ON DELETE CASCADE
);

-- 商品表
CREATE TABLE products (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          price DECIMAL(10,2) NOT NULL,
                          stock INT NOT NULL,
                          currency_type VARCHAR(20) NOT NULL,
                          guild_id BIGINT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (guild_id) REFERENCES guilds(id) ON DELETE SET NULL
);

-- 投票记录表
CREATE TABLE guild_votes (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             guild_id BIGINT NOT NULL,
                             action_type VARCHAR(50) NOT NULL,
                             proposer_id VARCHAR(36) NOT NULL,
                             required_percentage INT DEFAULT 60,
                             status VARCHAR(20) DEFAULT 'PENDING',
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             ended_at TIMESTAMP,
                             FOREIGN KEY (guild_id) REFERENCES guilds(id) ON DELETE CASCADE,
                             FOREIGN KEY (proposer_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 投票详情表
CREATE TABLE guild_vote_records (
                                    vote_id BIGINT NOT NULL,
                                    voter_id VARCHAR(36) NOT NULL,
                                    approved BOOLEAN NOT NULL,
                                    voted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (vote_id, voter_id),
                                    FOREIGN KEY (vote_id) REFERENCES guild_votes(id) ON DELETE CASCADE,
                                    FOREIGN KEY (voter_id) REFERENCES users(id) ON DELETE CASCADE
);