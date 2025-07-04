# nshguildmanageb

项目重构

项目名称：逆水寒帮会管理系统-后端服务

项目描述：关于个人技术+AI开发案例-逆水寒帮会管理系统，是一个基于Spring Boot的后端应用程序，用于提供接口配合前端小程序、web、app、桌面程序等前端管理帮会相关的业务。

项目详情：

    名称：NSHGuildManageB（逆水寒帮会管理系统-后端服务）
    版本：1.0.0
    作者：异域工作室
    邮箱：yiyidzs@163.com
    官网：https://www.yiyidzs.com
    文档：https://www.yiyidzs.com/apidocs
    仓库：https://github.com/yiyidzs/NSHGuildManageB
    语言：Java
    框架：Spring Boot
    数据库：H2 Database （开发阶段） + MySQL Driver 9.0.0 （生产阶段）
    开发环境：Windows 11 + IntelliJ IDEA 2025.1 + Visual Studio Code 1.99.3

项目设置：

    名称：NSHGuildManageB
    位置：~\Desktop\SourceCode\NSHGuildManage
    语言：Java
    类型：Maven
    组件：xyz.bfdwdd
    工件：nshguildmanageb
    软件包名称：xyz.bfdwdd.nshguildmanageb
    JDK：Oracle OpenJDK 21
    JAVA：21
    打包：Jar

技术栈：
Windows 11 + IntelliJ IDEA 2025.1 + Visual Studio Code 1.99.3 + Java 21 + Spring Boot 3.4.5 + Maven 3.9.9 + H2 Database （开发阶段） + MySQL Driver 9.0.0 （生产阶段） + Lombok 1.18.38 + Spring Data JPA + Spring Security + Spring Web + ModelMapper 3.2.0 + SpringDoc OpenAPI 2.8.8 + MapStruct 1.6.1 + Spring Boot Test 3.4.5 + Spring Retry + Resilience4j 2.1.0 + JWT

# 一、项目基础搭建

    1. 创建Spring Boot项目
    2. 添加依赖管理
    3. 配置文件编写
    4. 项目结构搭建

# 二、项目环境配置

    1. 数据库配置（H2 Database + MySQL Driver）
    2. Spring Boot配置
    3. Lombok配置
    4. ModelMapper配置
    5. SpringDoc OpenAPI配置
    6. MapStruct配置
    7. Spring Retry配置
    8. Resilience4j配置
    9. JWT配置

# 三、基础模块开发



    1. 用户模块 UserModule
    2. 角色模块 RoleModule
    3. 邀请码模块 InvitationCodeModule
    4. 权限模块 PermissionModule
    5. 认证模块 AuthenticationModule

    注： 用户模块 UserModule + 角色模块 RoleModule + 邀请码模块 InvitationCodeModule + 权限模块 PermissionModule + 认证模块 AuthenticationModule 需要实现基本的CRUD操作，并且需要实现权限控制。如下需求：

    {
        开发者权限：拥有整个程序的所有权限，例如程序控制管理、制定管理系统管理员及往下兼容下面拥有的权限；
        系统管理员权限：拥有仅次于开发者权限的能力，整个程序的对外服务功能权限、制定管理超级管理员及往下兼容下面拥有的权限；
        超级管理员权限：普通用户可以获得的最高权限，能创建管理解散自己的帮会，创建帮会后默认自己为帮主，可以转让别人，但只能继承管理和解散功能及往下兼容下面拥有的权限；
        帮会管理员权限：由超级管理员或者帮主及以上权限设置的各帮会管理员，可以管理帮会的成员申请退出和邀请普通成员加入及往下兼容下面拥有的权限；
        普通成员权限：通过超级管理员及以上预创建或者注册成为，拥有申请指定超级管理员或帮主名下的帮会和退出自己所在的帮会及往下兼容下面拥有的权限；
        非授权成员：使用该服务会提示非授权成员，并且无法访问该服务提供的功能、界面、内容等，但会保留注册接口权限；

        激活码模块功能是配合上面使用，码内包含创建者身份、创建创建时间、可使用次数、有效时间、随机码进行加密的一个码，并且最后拼接一个查询码可查询使用过这个邀请码的成员及身份信息等，邀请码和查询码是一对一绑定关系拼接起来。

        用户模块默认系统会预设一名开发者权限用户，一名系统管理员，超级管理员需要系统管理员及以上设置或者通过他们生成的邀请码注册成为，帮主不是特定权限用户是由帮会创建者转移现成不能直接注册或者设置成为，但可由超级管理员创建帮会后将自己的帮主转移至他人现成或者由系统管理员和以上权限者设置各帮会的帮主，但帮会拥有者永远为创建者，其它最多只能是拥有管理权限，帮会管理员由超级管理员或帮主及以上设立，普通成员由邀请码注册成为但如果是帮会管理员、帮主、超级管理的邀请码注册其身份会转变成其帮会成员，其注册需要邀请码和输入指定帮会（邀请码创建的帮会）或者通过超级管理员预设自己创建的帮会成为，非授权用户默认所有非成员均为，同样拥有普通用户注册成为帮会成员的方法

        超级管理员创建次数上限3个帮会和生成的指定帮会邀请码单帮会但次最多可为30人使用，帮主不具备该权限只能生成的指定帮会邀请码单帮会但次最多可为15人使用和管理帮会单次邀请用户和踢出成员解散帮会，帮会管理生成的指定帮会邀请码单帮会但次最多可为5人使用和管理帮会单次邀请用户和踢出成员，系统管理员和以上的权限用户无此限制

        超级管理员同时可在最多3个帮会内，帮主、帮会管理员、普通用户（帮会成员）只能同时在一个帮会内，系统管理员及以上无限制，但每个帮会只能有一个帮主，但可以有多个帮会管理员和普通用户（帮会成员）
    }



    6. 日志模块 LogModule
    7. 通知模块 NotificationModule
    8. 全局异常处理模块 GlobalExceptionModule

# 四、功能模块开发

    1. 常量模块 ConstantModule
    2. 帮会模块 GuildModule
    3. 商城模块 ShopModule
    4. 扩展功能优化模块
        - 普通商城模块 CommonShopModule
        - 帮会商城模块 GuildShopModule
        - 军功点模块 MilitaryPointsModule
        - 帮会战模块 GuildWarModule
        - 军功转换模块 MilitaryPointsTransferModule
        - 投票模块 VoteModule


    注：
    {
    提新的需求：
    第一个商城分成两个一个是通用商城需要超级管理员及以上才能上架下架等管理，且超级管理员员操作需要其它所有超级管理员审核确认，系统管理员和以上的无限制；
    第二个军功点是每个加入帮会的都有可以直接在自己的帮会商城购买东西，帮会商城的上架下架只有拥有帮主或者系统管理员以上的才可操作，无该帮会帮会帮主的超级管理员也无权限操作，如果是帮主进行自己帮会商城操作则需要所有帮会管理员投票通过超过60%（可动态配置）审核，系统管理员及以上无限制，帮主只能操作自己的帮会商城和使用自己的帮会商城购买等无法操作其它帮会，帮会成员只能在自己的帮会商城购买等无上架等权限，系统管理员可操作所有的帮会商城无限制，仅有超级管理员无帮主身份或者有超级管理员和不属于该帮会的帮主身份不得操作该帮会，其次在对应帮会获得对应军功点默认只在对应帮会能能使用，需要帮主身份或者系统管理员身份以上开启军功点转换率功能，设置帮会转入和转出率后，才能在加入的新帮会按转换率将旧帮会的军功点转成新的军功点，该操作在成功转成新帮会军功点会将旧帮会军功点清空，如果有多个旧帮会则按照选择的旧帮会的军功点转，在转换操作时候会检查该帮会是否开启了该功能，开启了则会查询该成员拥有的同样开启了的该功能的旧帮会和对应军功点，然后自己选择那个进行转换，不得本帮会转本帮会军功点。该功能的开启同样需要对应帮会帮主发起所有帮会帮主投票通过60%（可动态配置）才能开启，系统管理员及以上可直接确认或者开启各帮会该功能。此功能的转换和军功点转通用商城的积分等货币独立护不影响，军功点转普通商城货币的转换率由系统及以上管理员设置；
    }



再次开始重构，首先
第一步：将整体项目结构树调整一下，在xyz.bfdwdd.nshguildmanageb下面创建子包基础模块（Base Modules）、功能模块（Functional Modules）、扩展优化模块（Extended Optimization Modules）三个子包。将模块按开发顺序移入（先后也代码开发优先及顺序）
1.用户模块 UserModule、
2. 角色模块 RoleModule、
3.邀请码模块 InvitationCodeModule、
4. 权限模块 PermissionModule、
5. 认证模块 AuthenticationModule、
6. 日志模块 LogModule
7. 通知模块 NotificationModule
8. 全局异常处理模块 GlobalExceptionModule
八个模块移动到基础模块（Base Modules）子包模块目录下；将模块
1. 常量模块 ConstantModule
2. 帮会模块 GuildModule
3. 商城模块 ShopModule
三个模块移动到功能模块（Functional Modules）子包模块目录下；将模块
1. 投票模块 VoteModule
2. 帮会战模块 GuildWarModule
3. 军功点模块 MilitaryMeritModule
4. 军功转换模块 MilitaryMeritPointsModule
5. 普通商城模块 OrdinaryShopModule
6. 帮会商城模块 GuildShopModule
六个模块移动到扩展优化模块（Extended Optimization Modules）子包模块目录下。

第二步：开发顺序
基础模块（Base Modules）不需要调整，需要调整的是功能模块（Functional Modules）和扩展优化模块（Extended Optimization Modules）也就是本次重构开发的主要任务，顺序为
1. 常量模块 ConstantModule 用来功能和扩展及后续常量的存放；
2. 投票模块 VoteModule 用来在后续其它功能开放投票使用，如开启军功转换模块 MilitaryMeritPointsModule、普通商城模块 OrdinaryShopModule的操作；
3. 帮会模块 GuildModule 用来记录各帮会的创建者、成员、管理员等数据，也是后续开发其它模块的基础，故排在前面；
4 帮会战模块 GuildWarModule 涉及的属性较多，记录帮战数据等，也是自动获得军功点的主要模块，故延后跳过开发后续在补；
5 军功点模块 MilitaryMeritModule 是帮会和帮会商城等最重要的经济模块，根据默认公式、动态自定义公式、帮会战数据公式等传入对应数据获得对应军功；
6. 商城模块 ShopModule 主要是用来存放普通商城模块 OrdinaryShopModule 和帮会商城模块 GuildShopModule 的基础，后续开发这两个模块都需要用到这个模块的接口和实体类等；
7. 普通商城模块 OrdinaryShopModule 主要的系统商城由系统管理员管理的，可使用洛币、军功点、积分及后续开发的货币等级系统购买东西；
8. 帮会商城模块 GuildShopModule 由各帮会帮主管理的商城，主要使用军功点也可使用洛币及后续开发的货币等级系统购买东西；
9. 军功点转换模块 MilitaryMeritPointsModule 主要目前有两个功能，一个是将军功点按系统管理员动态设置的汇率转换成普通商城可用的洛币；一个是将旧帮会军功点按帮主或者系统管理员以上设置的汇率转换到新帮会，其实就是设置不同帮会拥有不同汇率价值，设置帮会军功点的转入转出（默认一致）汇率。

第三步：注意完成我项目重构.md中选择的 105-110行片段要求；注意中英文注释；注意项目结构（是用新调整的结构树将这些模块移入 基础模块BaseModules、功能模块FunctionalModules、扩展优化模块Extended Optimization Modules三个子包下面后面开发的模块也将在他们下面），每一个模块开发都需要在末尾给出该步骤模块开发需要创建的结构树和到该步骤为止完整的项目结构树。



# 五、项目集测试

    1. 单元测试
    2. 集成测试
    3. 系统测试
    4. 压力测试
    5. 安全测试

# 六、打包上线测试

    1. 打包上线
    2. 上线测试
    3. 问题修复
    4. 功能完善

# 七、项目优化

    1. 性能优化
    2. 功能优化
    3. 代码优化
    4. 架构优化

# 八、项目发布

    1. 项目发布
    2. 用户反馈收集
    3. 问题修复
    4. 功能完善
    5. 性能优化

# 九、项目文档

    1. 项目文档编写
    2. 项目文档发布
    3. 项目文档测试
    4. 项目文档优化
    5. 项目文档更新

# 十、项目总结

    1. 项目总结
    2. 项目优化
    3. 项目改进
    4. 项目展望








```
    xyz.bfdwdd.nshguildmanageb
├── NSHGuildManageBApplication.java    # 主启动类
├── constant/
│   ├── enums/
│   │   ├── RoleHierarchy.java          # 角色层级枚举
│   │   ├── PermissionType.java         # 权限类型枚举
│   │   └── GuildActionType.java        # 帮会操作枚举
│   ├── constants/
│   │   ├── ErrorCodes.java             # 全局错误码
│   │   └── AppConstants.java           # 应用常量（JWT、邀请码长度等）
│   └── ConstantApplication.java        # 可选：模块启动类
├── permission/
│   ├── config/
│   │   └── PermissionConfig.java        # 权限模块配置
│   ├── dto/
│   │   ├── request/
│   │   │   ├── CreatePermissionRequest.java
│   │   │   └── UpdatePermissionRequest.java
│   │   └── response/
│   │       └── PermissionResponse.java
│   ├── entity/
│   │   └── Permission.java
│   ├── repository/
│   │   └── PermissionRepository.java
│   ├── service/
│   │   ├── PermissionService.java
│   │   └── impl/
│   │       └── PermissionServiceImpl.java
│   ├── controller/
│   │   └── PermissionController.java
│   ├── exception/
│   │   ├── PermissionNotFoundException.java
│   │   ├── PermissionAlreadyExistsException.java
│   │   └── PermissionOperationException.java
│   └── PermissionApplication.java
├── role/
│   ├── config/
│   │   └── RoleConfig.java              # 模块配置类
│   ├── dto/
│   │   ├── request/
│   │   │   ├── CreateRoleRequest.java
│   │   │   └── UpdateRoleRequest.java
│   │   └── response/
│   │       └── RoleResponse.java
│   ├── entity/
│   │   └── Role.java
│   ├── repository/
│   │   └── RoleRepository.java
│   ├── service/
│   │   ├── RoleService.java
│   │   └── impl/
│   │       └── RoleServiceImpl.java
│   ├── controller/
│   │   └── RoleController.java
│   ├── exception/
│   │   ├── RoleAlreadyExistsException.java
│   │   ├── RoleNotFoundException.java
│   │   └── RoleOperationException.java
│   └── RoleApplication.java
├── user/
│   ├── config/
│   │   └── InitialAdminConfig.java      # 初始化管理员配置
│   ├── dto/
│   │   ├── request/
│   │   │   ├── RegisterRequest.java
│   │   │   └── UpdateUserRequest.java
│   │   └── response/
│   │       └── UserResponse.java
│   ├── entity/
│   │   └── User.java
│   ├── repository/
│   │   └── UserRepository.java
│   ├── service/
│   │   ├── UserService.java
│   │   └── impl/
│   │       └── UserServiceImpl.java
│   ├── controller/
│   │   └── UserController.java
│   ├── exception/
│   │   ├── UserAlreadyExistsException.java
│   │   ├── UserNotFoundException.java
│   │   └── UserOperationException.java
│   └── UserApplication.java
├── authentication/
│   ├── config/
│   │   └── SecurityConfig.java          # Spring Security 配置
│   ├── dto/
│   │   ├── request/
│   │   │   └── LoginRequest.java
│   │   └── response/
│   │       └── LoginResponse.java
│   ├── filter/
│   │   └── JwtAuthenticationFilter.java
│   ├── service/
│   │   └── CustomUserDetailsService.java
│   ├── utils/
│   │   └── JwtUtils.java               # JWT 工具类
│   ├── controller/
│   │   └── AuthController.java
│   ├── exception/
│   │   ├── AuthenticationException.java
│   │   └── InvalidTokenException.java
│   └── AuthApplication.java
├── invitationcode/
│   ├── config/
│   │   └── CodeGeneratorConfig.java     # 邀请码生成配置
│   ├── dto/
│   │   ├── request/
│   │   │   └── GenerateCodeRequest.java
│   │   └── response/
│   │       └── InvitationCodeResponse.java
│   ├── entity/
│   │   └── InvitationCode.java
│   ├── repository/
│   │   └── InvitationCodeRepository.java
│   ├── service/
│   │   ├── InvitationCodeService.java
│   │   └── impl/
│   │       └── InvitationCodeServiceImpl.java
│   ├── controller/
│   │   └── InvitationCodeController.java
│   ├── exception/
│   │   ├── InvalidInvitationCodeException.java
│   │   ├── InvitationCodeAlreadyUsedException
│   │   ├── InvitationCodeExpiredException.java
│   │   └── InvitationCodeOperationException.java
│   └── CodeApplication.java
├── log/
│   ├── config/
│   │   └── LogConfig.java
│   ├── dto/
│   │   ├── request/
│   │   │   └── LogSearchRequest.java
│   │   └── response/
│   │       └── LogResponse.java
│   ├── entity/
│   │   └── Log.java
│   ├── repository/
│   │   └── LogRepository.java
│   ├── service/
│   │   ├── LogService.java
│   │   └── impl/
│   │       └── LogServiceImpl.java
│   ├── aspect/
│   │   └── LoggingAspect.java
│   ├── controller/
│   │   └── LogController.java
│   ├── exception/
│   │   ├── LogNotFoundException.java
│   │   └── LogOperationException.java
│   └── LogApplication.java
├── notification/
│   ├── config/
│   │   └── NotificationConfig.java
│   ├── dto/
│   │   ├── request/
│   │   │   ├── CreateNotificationRequest.java
│   │   │   └── UpdateNotificationRequest.java
│   │   └── response/
│   │       └── NotificationResponse.java
│   ├── entity/
│   │   └── Notification.java
│   ├── repository/
│   │   └── NotificationRepository.java
│   ├── service/
│   │   ├── NotificationService.java
│   │   └── impl/
│   │       └── NotificationServiceImpl.java
│   ├── controller/
│   │   └── NotificationController.java
│   ├── exception/
│   │   ├── NotificationNotFoundException.java
│   │   └── NotificationOperationException.java
│   └── NotificationApplication.java
└── exception/
    ├── GlobalExceptionHandler.java        # 全局异常处理
    ├── ApiError.java                    # 标准化错误响应
    └── ApiException.java                # 自定义异常基类

```


以下是你当前项目的完整结构树，按照 **基础模块**、**功能模块**、**扩展优化模块** 进行分类整理。结构中包含中英文注释，并基于你目前的开发进度进行归纳。

---

## 🧱 一、基础模块（Base Modules）

这些是系统的基础支撑模块，包括用户权限、认证、日志、异常处理等核心功能。

```
xyz.bfdwdd.nshguildmanageb
├── NSHGuildManageBApplication.java           # 主启动类 / Main Application Class
│
├── constant/                                 # 常量模块 / Constant Module
│   ├── enums/
│   │   ├── RoleHierarchy.java                # 角色层级枚举 / Role Hierarchy Enum
│   │   ├── PermissionType.java               # 权限类型枚举 / Permission Type Enum
│   │   └── GuildActionType.java              # 帮会操作枚举 / Guild Action Type Enum
│   ├── constants/
│   │   ├── ErrorCodes.java                   # 错误码常量 / Global Error Codes
│   │   └── AppConstants.java                 # 应用级常量 / App Constants
│   └── ConstantApplication.java              # 可选：模块启动类
│
├── user/                                     # 用户模块 / User Module
│   ├── config/
│   │   └── InitialAdminConfig.java           # 初始化管理员配置 / Admin Initialization Config
│   ├── dto/
│   │   ├── request/
│   │   │   ├── RegisterRequest.java          # 注册请求 / Register Request DTO
│   │   │   └── UpdateUserRequest.java        # 更新请求 / Update Request DTO
│   │   └── response/
│   │       └── UserResponse.java             # 返回响应 / User Response DTO
│   ├── entity/
│   │   └── User.java                         # 用户实体 / User Entity
│   ├── repository/
│   │   └── UserRepository.java               # 数据访问层 / User Repository
│   ├── service/
│   │   ├── UserService.java                  # 接口 / User Service Interface
│   │   └── impl/
│   │       └── UserServiceImpl.java          # 实现类 / User Service Implementation
│   ├── controller/
│   │   └── UserController.java               # 控制器 / REST API Controller
│   ├── exception/
│   │   ├── UserAlreadyExistsException.java
│   │   ├── UserNotFoundException.java
│   │   └── UserOperationException.java
│   └── UserApplication.java                  # 可选启动类
│
├── role/                                     # 角色模块 / Role Module
│   ├── config/
│   │   └── RoleConfig.java                   # 模块配置类 / Role Module Config
│   ├── dto/
│   │   ├── request/
│   │   │   ├── CreateRoleRequest.java
│   │   │   └── UpdateRoleRequest.java
│   │   └── response/
│   │       └── RoleResponse.java
│   ├── entity/
│   │   └── Role.java
│   ├── repository/
│   │   └── RoleRepository.java
│   ├── service/
│   │   ├── RoleService.java
│   │   └── impl/
│   │       └── RoleServiceImpl.java
│   ├── controller/
│   │   └── RoleController.java
│   ├── exception/
│   │   ├── RoleAlreadyExistsException.java
│   │   ├── RoleNotFoundException.java
│   │   └── RoleOperationException.java
│   └── RoleApplication.java
│
├── permission/                               # 权限模块 / Permission Module
│   ├── config/
│   │   └── PermissionConfig.java
│   ├── dto/
│   │   ├── request/
│   │   │   ├── CreatePermissionRequest.java
│   │   │   └── UpdatePermissionRequest.java
│   │   └── response/
│   │       └── PermissionResponse.java
│   ├── entity/
│   │   └── Permission.java
│   ├── repository/
│   │   └── PermissionRepository.java
│   ├── service/
│   │   ├── PermissionService.java
│   │   └── impl/
│   │       └── PermissionServiceImpl.java
│   ├── controller/
│   │   └── PermissionController.java
│   ├── exception/
│   │   ├── PermissionNotFoundException.java
│   │   ├── PermissionAlreadyExistsException.java
│   │   └── PermissionOperationException.java
│   └── PermissionApplication.java
│
├── authentication/                           # 认证模块 / Authentication Module
│   ├── config/
│   │   └── SecurityConfig.java               # Spring Security 配置 / Security Configuration
│   ├── dto/
│   │   ├── request/
│   │   │   └── LoginRequest.java
│   │   └── response/
│   │       └── LoginResponse.java
│   ├── filter/
│   │   └── JwtAuthenticationFilter.java      # JWT 过滤器 / JWT Filter
│   ├── service/
│   │   └── CustomUserDetailsService.java     # 自定义用户详情服务
│   ├── utils/
│   │   └── JwtUtils.java                     # JWT 工具类 / JWT Utility
│   ├── controller/
│   │   └── AuthController.java               # 登录接口 / Login API
│   ├── exception/
│   │   ├── AuthenticationException.java
│   │   └── InvalidTokenException.java
│   └── AuthApplication.java
│
├── invitationcode/                           # 邀请码模块 / Invitation Code Module
│   ├── config/
│   │   └── CodeGeneratorConfig.java
│   ├── dto/
│   │   ├── request/
│   │   │   └── GenerateCodeRequest.java
│   │   └── response/
│   │       └── InvitationCodeResponse.java
│   ├── entity/
│   │   └── InvitationCode.java
│   ├── repository/
│   │   └── InvitationCodeRepository.java
│   ├── service/
│   │   ├── InvitationCodeService.java
│   │   └── impl/
│   │       └── InvitationCodeServiceImpl.java
│   ├── controller/
│   │   └── InvitationCodeController.java
│   ├── exception/
│   │   ├── InvalidInvitationCodeException.java
│   │   ├── InvitationCodeAlreadyUsedException.java
│   │   ├── InvitationCodeExpiredException.java
│   │   └── InvitationCodeOperationException.java
│   └── CodeApplication.java
│
├── log/                                      # 日志模块 / Log Module
│   ├── config/
│   │   └── LogConfig.java
│   ├── dto/
│   │   ├── request/
│   │   │   └── LogSearchRequest.java
│   │   └── response/
│   │       └── LogResponse.java
│   ├── entity/
│   │   └── Log.java
│   ├── repository/
│   │   └── LogRepository.java
│   ├── service/
│   │   ├── LogService.java
│   │   └── impl/
│   │       └── LogServiceImpl.java
│   ├── aspect/
│   │   └── LoggingAspect.java                # AOP 切面记录日志
│   ├── controller/
│   │   └── LogController.java
│   ├── exception/
│   │   ├── LogNotFoundException.java
│   │   └── LogOperationException.java
│   └── LogApplication.java
│
├── notification/                             # 通知模块 / Notification Module
│   ├── config/
│   │   └── NotificationConfig.java
│   ├── dto/
│   │   ├── request/
│   │   │   ├── CreateNotificationRequest.java
│   │   │   └── UpdateNotificationRequest.java
│   │   └── response/
│   │       └── NotificationResponse.java
│   ├── entity/
│   │   └── Notification.java
│   ├── repository/
│   │   └── NotificationRepository.java
│   ├── service/
│   │   ├── NotificationService.java
│   │   └── impl/
│   │       └── NotificationServiceImpl.java
│   ├── controller/
│   │   └── NotificationController.java
│   ├── exception/
│   │   ├── NotificationNotFoundException.java
│   │   └── NotificationOperationException.java
│   └── NotificationApplication.java
│
├── exception/                                # 全局异常处理模块 / Global Exception Module
│   ├── GlobalExceptionHandler.java             # 全局异常处理器 / Global Handler
│   ├── ApiError.java                         # 标准错误结构 / Unified Error Structure
│   └── ApiException.java                     # 异常基类 / Base Exception Class
```

---

## ⚙️ 二、功能模块（Functional Modules）

这些模块实现系统的主要业务逻辑，如帮会管理、商城、军功点等。

### 1. `guild` - 帮会模块

```
xyz.bfdwdd.nshguildmanageb.guild
├── config/
│   └── GuildConfig.java                      # 模块配置类
├── dto/
│   ├── request/
│   │   ├── CreateGuildRequest.java
│   │   ├── JoinGuildRequest.java
│   │   ├── TransferOwnerRequest.java
│   │   └── UpdateGuildRequest.java
│   └── response/
│       ├── GuildResponse.java
│       └── GuildMemberResponse.java
├── entity/
│   ├── Guild.java
│   └── GuildMember.java
├── repository/
│   ├── GuildRepository.java
│   └── GuildMemberRepository.java
├── service/
│   ├── GuildService.java
│   └── impl/
│       └── GuildServiceImpl.java
├── controller/
│   └── GuildController.java
├── exception/
│   ├── GuildNotFoundException.java
│   ├── GuildOperationException.java
│   ├── MemberNotInGuildException.java
│   └── NotGuildOwnerException.java
└── GuildApplication.java
```

---

### 2. `shop` - 商城模块（通用商城）

```
xyz.bfdwdd.nshguildmanageb.shop
├── config/
│   └── ShopConfig.java
├── dto/
│   ├── request/
│   │   ├── CreateProductRequest.java
│   │   └── PurchaseProductRequest.java
│   └── response/
│       ├── ProductResponse.java
│       └── PurchaseRecordResponse.java
├── entity/
│   ├── Product.java
│   └── PurchaseRecord.java
├── repository/
│   ├── ProductRepository.java
│   └── PurchaseRecordRepository.java
├── service/
│   ├── ShopService.java
│   └── impl/
│       └── ShopServiceImpl.java
├── controller/
│   └── ShopController.java
├── exception/
│   ├── ProductNotFoundException.java
│   ├── InsufficientBalanceException.java
│   └── PurchaseFailedException.java
└── ShopApplication.java
```

---

### 3. `guildshop` - 帮会商城模块

```
xyz.bfdwdd.nshguildmanageb.guildshop
├── config/
│   └── GuildShopConfig.java
├── dto/
│   ├── request/
│   │   ├── AddGuildProductRequest.java
│   │   ├── BuyGuildProductRequest.java
│   │   ├── InitiateVoteRequest.java
│   │   └── ConfirmVoteRequest.java
│   └── response/
│       ├── GuildProductResponse.java
│       └── VoteStatusResponse.java
├── entity/
│   ├── GuildProduct.java
│   └── GuildConversionRate.java
├── repository/
│   ├── GuildProductRepository.java
│   └── GuildConversionRateRepository.java
├── service/
│   ├── GuildShopService.java
│   └── impl/
│       └── GuildShopServiceImpl.java
├── controller/
│   └── GuildShopController.java
├── exception/
│   ├── ConversionNotAllowedException.java
│   ├── ProductAlreadyExistsException.java
│   └── ConversionDisabledException.java
└── GuildShopApplication.java
```

---

### 4. `militarymerit` - 军功点模块

```
xyz.bfdwdd.nshguildmanageb.militarymerit
├── config/
│   └── MeritConfig.java
├── dto/
│   ├── request/
│   │   ├── AddMeritRequest.java
│   │   ├── DeductMeritRequest.java
│   │   └── ClearMeritRequest.java
│   └── response/
│       ├── MeritDetailResponse.java
│       └── MeritSummaryResponse.java
├── entity/
│   └── MilitaryMerit.java
├── repository/
│   └── MilitaryMeritRepository.java
├── service/
│   ├── MilitaryMeritService.java
│   └── impl/
│       └── MilitaryMeritServiceImpl.java
├── controller/
│   └── MilitaryMeritController.java
├── exception/
│   ├── MeritNotFoundException.java
│   ├── InsufficientMilitaryMeritException.java
│   └── MeritOperationException.java
└── MeritApplication.java
```

---

### 5. `generalshop` - 通用商城模块

```
xyz.bfdwdd.nshguildmanageb.generalshop
├── config/
│   └── GeneralShopConfig.java
├── dto/
│   ├── request/
│   │   ├── CreateProductRequest.java
│   │   └── PurchaseProductRequest.java
│   └── response/
│       ├── ProductResponse.java
│       └── ReviewResponse.java
├── entity/
│   ├── GeneralProduct.java
│   └── ProductReview.java
├── repository/
│   ├── GeneralProductRepository.java
│   └── ProductReviewRepository.java
├── service/
│   ├── GeneralShopService.java
│   └── impl/
│       └── GeneralShopServiceImpl.java
├── controller/
│   └── GeneralShopController.java
├── exception/
│   ├── ProductNotFoundException.java
│   ├── ReviewNotApprovedException.java
│   └── InvalidRoleOperationException.java
└── GeneralShopApplication.java
```

---

## 🔁 三、扩展优化模块（Extended Optimization Modules）

### 1. `vote` - 投票机制模块

```
xyz.bfdwdd.nshguildmanageb.vote
├── config/
│   └── VoteConfig.java
├── dto/
│   ├── request/
│   │   ├── InitiateVoteRequest.java
│   │   └── ConfirmVoteRequest.java
│   └── response/
│       └── VoteStatusResponse.java
├── entity/
│   ├── GuildVote.java
│   ├── GuildVoteRecord.java
│   └── VoteType.java
├── repository/
│   ├── GuildVoteRepository.java
│   └── GuildVoteRecordRepository.java
├── service/
│   ├── VoteService.java
│   └── impl/
│       └── VoteServiceImpl.java
├── controller/
│   └── VoteController.java
├── exception/
│   ├── VoteNotFoundException.java
│   ├── VoteAlreadyEndedException.java
│   └── NotEnoughApprovalsException.java
└── VoteApplication.java
```

---

### 2. `meritconversion` - 军功点转换率模块

```
xyz.bfdwdd.nshguildmanageb.meritconversion
├── config/
│   └── ConversionSettings.java
├── dto/
│   ├── request/
│   │   ├── SetConversionRateRequest.java
│   │   └── EnableConversionRequest.java
│   └── response/
│       └── ConversionRateResponse.java
├── entity/
│   ├── ConversionRate.java
│   ├── ConversionVote.java
│   └── ConversionVoteRecord.java
├── repository/
│   ├── ConversionRateRepository.java
│   ├── ConversionVoteRepository.java
│   └── ConversionVoteRecordRepository.java
├── service/
│   ├── MeritConversionService.java
│   └── impl/
│       └── MeritConversionServiceImpl.java
├── controller/
│   └── MeritConversionController.java
├── exception/
│   ├── ConversionRateNotFoundException.java
│   ├── ConversionDisabledException.java
│   └── ConversionNotAllowedException.java
└── MeritConversionApplication.java
```

---

## ✅ 总结

| 模块 | 类型 | 功能 |
|------|------|------|
| `constant` | 基础 | 枚举、全局常量 |
| `user`, `role`, `permission` | 基础 | 用户权限体系 |
| `authentication` | 基础 | JWT 认证与登录 |
| `invitationcode` | 基础 | 邀请码生成和使用 |
| `log`, `notification` | 基础 | 系统日志与通知 |
| `guild` | 功能 | 帮会创建、成员管理 |
| `generalshop` | 功能 | 通用商城 |
| `guildshop` | 功能 | 帮会专属商城 |
| `militarymerit` | 功能 | 军功点管理 |
| `meritconversion` | 扩展 | 军功点跨帮会转换 |
| `vote` | 扩展 | 投票机制支持 |

---

如果你需要我为你生成项目文档目录、数据库设计、API 文档模板或部署说明，也可以告诉我，我会继续补充完善！