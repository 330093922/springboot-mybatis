# 车辆同步SQL

INSERT INTO waybill_vehicle (
owner_id,
vehicle_code,
vehicle_model,
vehicle_type,
vehicle_status,
color,
manage_area,
transport_number,
first_date,
expire_date,
grant_organ,
load_capacity,
ton,
volume,
is_toxic,
is_etch,
is_explode,
insure_money,
insure_end_date,
default_pilot,
default_supercargo,
default_car,
default_car_code,
vehicle_rank,
next_tec_date,
next_maintain_date,
next_check_date,
cert_photo,
car_photo,
test_no,
transport_scope,
map_color
) SELECT
OWNER_ID,
VEHICLE_CODE,
VEHICLE_MODEL,
VEHICLE_TYPE,
VEHICLE_STATUS,
color,
MGRAREA,
CCERTWORD,
FIRSTDATE,
EXPIREDATE,
GRANTORGAN,
LOAD_CAPACITY,
ton,
POTCUBAGE,
ISJD,
ISQFS,
ISBZP,
WHCIMONEY,
WHCIENDDATE,
JSEMP_ID,
YYEMPID,
GVEHICLE_ID,
GVEHICLE_CODE,
VCLTECRANK,
NEXTTECDATE,
NEXTSECMDATE,
NEXTCHECKDATE,
CCERT_PHOTO,
CAR_PHOTO,
TEST_NO,
YSFW,
map_color
FROM
	t_base_vehicle


#   人员同步SQL

INSERT INTO waybill_user
(
owner_id,
NAME,
sex,
age,
birthday,
card_type,
id_card,
phone,
addr_place,
address,
native_place,
photo_url,
feature,
review_status,
flag,
join_time,
end_time,
PASSWORD
)
	SELECT
	OWNER_ID,
	NAME,
	SEX,
	AGE,
	BIRTHDAY,
	CARD_TYPE,
	IDCARD,
	PHONE,
	ADD_ZIPCODE,
	ADRESS,
	ZZIPCODE,
	photo_url,
	feature,
	review_status,
	flag,
	join_time,
	end_time,
	md5( "123456" )
FROM
	t_base_employee

#企业同步SQL

INSERT INTO waybill_owner (
owner_code,
owner_type,
owner_name,
owner_jc,
register_capital,
register_time,
area,
person_num,
icbc_code,
credit_code,
owner_addr,
register_addr,
mgr_code,
register_code,
mgr_area,
state,
contact,
contact_phone,
govern_code,
important_level,
is_important_chemical,
is_important_crafts,
owner_zt,
owner_zt_msg,
owner_nature,
corp_name,
corp_card,
corp_card_number,
corp_phone,
corp_photo,
industry,
lon,
lat,
business_level_count,
business_technology,
business_goods,
nonnative
) SELECT
OWNER_ID,
OWNERTYPE,
OWNERNAME,
OWNERJC,
ZHUCEZIBEN,
ZHUCETIME,
area,
PERSNUM,
ICBC_CODE,
TONGYICODE,
OWNERADDR,
ZHUCEDIZHI,
AZIPCODE,
ZZIPCODE,
MGRAREA,
STATE,
LXR,
PTTEL,
SSGLBM,
IMPORTANT_LEVEL,
IS_IMPORTANT_CHEMICAL,
IS_IMPORTANT_CRAFTS,
OWNERZT,
OWNERZT_MSG,
OWNERXZ,
FARENNAME,
CARD,
IDCARD,
PHONE,
PHOTO,
S_HY,
lon,
lat,
ZD_LEVEL_COUNT,
ZD_TECHNOLOGY,
ZD_GOODS,
NONNATIVE
FROM
	t_base_owner



# 2019-12-19 14:32 新增

# auth_role_user '资源-用户角色关联表';

# auth_role_resource '资源-角色资源关联表';

# auth_role_data '数据-角色数据关联表';

# auth_role '角色表';

# auth_resource '权限-资源表';



# 2019-12-17 14:08 新增

# waybill_order_route '电子运单-推荐线路表'


# 2019-12-13 09:50 新增
# waybill_route '常用线路表';
#
# waybill_camera '摄像头表';
#
# waybill_goods '危险化学品货物表';
#
# waybill_goods_security '危险化学品货物-安全卡';
#
# waybill_goods_discipline '危险化学品货物-操作规程';
#
# waybill_detection '检查项表';
#
# waybill_goods_detection '危险化学品货物-检查项';
#
# waybill_area '装卸区域';



-- auto-generated definition
create table auth_role_user
(
  id         int auto_increment
    primary key,
  role_id    int        default 0                 null comment '角色id',
  user_id    int        default 0                 null comment '用户id',
  created_at datetime   default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
  deleted    tinyint(1) default 0                 null comment '删除标记'
)
  comment '资源-用户角色关联表';

-- auto-generated definition
create table auth_role_resource
(
  id          int auto_increment
    primary key,
  role_id     int                                  null comment '角色id',
  resource_id int                                  null comment '资源id',
  created_at  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
  deleted     tinyint(1) default 0                 null comment '删除标记'
)
  comment '资源-角色资源关联表';

-- auto-generated definition
create table auth_role_data
(
  id         int auto_increment
    primary key,
  scope      int        default 0                 null comment '数据范围 0：自定义 1：全部',
  role_id    int                                  null comment '角色id',
  data_type  int                                  null comment '数据类型 0:企业数据',
  data_id    int                                  null comment '数据id',
  created_at datetime   default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
  deleted    tinyint(1) default 0                 null comment '删除标记'
)
  comment '数据-角色数据关联表';


-- auto-generated definition
create table auth_role
(
  id          int auto_increment
    primary key,
  role_name   varchar(128)                           not null comment '角色名称',
  description varchar(128)                           not null comment '角色描述',
  is_admin    varchar(128) default '1'               not null comment '是否能删除 0否  1是',
  created_at  datetime     default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at  datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
  deleted     tinyint(1)   default 0                 null comment '删除标记'
)
  comment '角色表';

-- auto-generated definition
create table auth_resource
(
  id           int auto_increment
    primary key,
  resource_key varchar(128)                           not null comment '资源唯一标识',
  name         varchar(255)                           null comment '资源名称',
  order_value  int(4)       default 0                 null comment '排序优先级',
  api_url      varchar(128) default ''                null comment '资源对应的url 资源或者后台接口',
  icon_url     varchar(128) default ''                null comment '对应的前端图标属性',
  type         int(4)       default 0                 null comment '0菜单 1按钮等资源',
  description  varchar(128)                           null comment '资源描述',
  parent_id    int          default 0                 null comment '父级别id',
  business     varchar(32)                            null comment '业务字段',
  created_at   datetime     default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at   datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
  deleted      tinyint(1)   default 0                 null comment '删除标记'
)
  comment '权限-资源表';





create table waybill_order_route
(
  id         int auto_increment comment 'id'
    primary key,
  order_id   int                                not null comment '运单id',
  route_id   int                                not null comment '线路id',
  created_at datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted tinyint  default 0                 null comment '是否删除'
)
  comment '电子运单-推荐线路表';


create table waybill_area
(
  id         int auto_increment comment 'id'
    primary key,
  owner_id   int(32)                            null comment '所属企业id',
  area_no    varchar(32)                        not null comment '装卸区域编号 ',
  area_name  varchar(64)                        not null comment '装卸区域名称',
  type       tinyint                            null comment '装卸区类型：0:装卸货 1:装货 2:卸货',
  goods_name varchar(255)                       null comment '可装卸货货物名称',
  classify   varchar(255)                       null comment '危险特性分类',
  input_date datetime default CURRENT_TIMESTAMP null comment '投入日期',
  volume     int                                null comment '容积（立方米）',
  length     int                                null comment '长（米）',
  wide       int                                null comment '宽（米）',
  image_url  varchar(128)                       null comment '装卸区域实景',
  status     tinyint  default 0                 null comment '状态：0:激活 1:禁止',
  remark     text                               null comment '备注',
  created_at datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted tinyint  default 0                 null comment '是否删除'
)
  comment '装卸区域';

create table waybill_camera
(
  id               int auto_increment comment 'id'
    primary key,
  owner_id         int                                not null comment '所属企业',
  equipment_no     varchar(100)                       null comment '设备编号 ',
  equipment_status int(1)                             null comment '设备状态 1：正常；2：异常; 3：停用 ',
  type             varchar(100)                       null comment '视频读取方式分类 0：流地址 1：用户名密码',
  install_area     varchar(255)                       null comment '安装区域 1.厂区门口 2.磅区 3.装卸区车位 4.其他',
  user_id          varchar(100)                       null comment '用户id ',
  password         varchar(100)                       null comment '密码 ',
  name             varchar(100)                       null comment '显示名称 ',
  lon              varchar(100)                       null comment '经度 ',
  lat              varchar(100)                       null comment '纬度 ',
  rtmp_url         varchar(100)                       null comment 'rtmp流地址 ',
  hls_url          varchar(100)                       null comment 'hls地址 ',
  status           tinyint  default 0                 null comment '0:激活 1:禁止',
  created_at       datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at       datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted       tinyint  default 0                 null comment '是否删除'
)
  comment '摄像头表';

create table waybill_detection
(
  id         int auto_increment comment 'id'
    primary key,
  type       varchar(62)                        not null comment '检查类型 ',
  time       varchar(128)                       not null comment '检查时间',
  project    varchar(128)                       null comment '检查项',
  content    text                               null comment '检查内容',
  remark     text                               null comment '备注',
  created_at datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted tinyint  default 0                 null comment '是否删除'
)
  comment '检查项表';

create table waybill_files
(
  id         int auto_increment comment '文件id'
    primary key,
  url        varchar(100)                       null comment '文件链接 ',
  suffix     varchar(32)                        null comment '文件后缀',
  type       varchar(32)                        null comment '文件类型 0:图片  1:其他',
  created_at datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted tinyint  default 0                 null comment '是否删除'
)
  comment '文件表';

create table waybill_goods
(
  id             int auto_increment comment 'id'
    primary key,
  un_no          varchar(100)                       not null comment '联合国货物编号 ',
  name           varchar(128)                       not null comment '货物名称',
  type           varchar(100)                       null comment '类/项别',
  packaging_type varchar(100)                       null comment '包装类别',
  alias          varchar(100)                       null comment '别名',
  number         decimal(12, 2)                     null comment '数量（吨/立方）',
  remark         text                               null comment '备注',
  status         tinyint  default 0                 null comment '0:激活 1:禁止',
  created_at     datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at     datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted     tinyint  default 0                 null comment '是否删除'
)
  comment '危险化学品货物表';

create table waybill_goods_detection
(
  id           int auto_increment comment 'id'
    primary key,
  goods_id     int                                not null comment '货物id ',
  detection_id int                                not null comment '检查项id ',
  created_at   datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at   datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted   tinyint  default 0                 null comment '是否删除'
)
  comment '危险化学品货物-检查项';

create table waybill_goods_discipline
(
  id         int auto_increment comment 'id'
    primary key,
  goods_id   int                                not null comment '货物id ',
  name       varchar(128)                       not null comment '操作名称',
  content    text                               null comment '内容',
  created_at datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted tinyint  default 0                 null comment '是否删除'
)
  comment '危险化学品货物-操作规程';

create table waybill_goods_security
(
  id           int auto_increment comment 'id'
    primary key,
  goods_id     int                                not null comment '货物id ',
  image_one    varchar(128)                       null comment '安全图片1',
  image_tow    varchar(128)                       null comment '安全图片2',
  risk         text                               null comment '危险性',
  requirements text                               null comment '储运要求',
  reveal       text                               null comment '泄露处置',
  emergency    text                               null comment '急救方法',
  extinguisher text                               null comment '灭火方法',
  protective   text                               null comment '防护措施',
  created_at   datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at   datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted   tinyint  default 0                 null comment '是否删除'
)
  comment '危险化学品货物-安全卡';

create table waybill_nodes
(
  id         int auto_increment comment '节点id'
    primary key,
  name       varchar(100)                       null comment '节点名称 ',
  field      varchar(32)                        null comment '备用字段',
  created_at datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted tinyint  default 0                 null comment '是否删除'
)
  comment '节点表';

create table waybill_order
(
  id              int auto_increment comment 'id'
    primary key,
  order_code      varchar(100)                       not null comment '运单编号 ',
  qr_code         varchar(128)                       null comment '二维码信息字符串',
  shipper_id      int(32)                            null comment '发货企业（托运企业）id',
  receiving_id    int(32)                            null comment '收货企业id',
  shipment_start  varchar(255)                       null comment '装货地点',
  shipment_stop   varchar(255)                       null comment '卸货地点',
  shipment_time   datetime                           null comment '预计装货时间',
  shipment_arrive datetime                           null comment '预计到达日期',
  shipment_id     int(32)                            null comment '运输企业（承运）id (经营许可证：信用代码)',
  pilot_id        int(32)                            null comment '驾驶员用户id',
  supercargo_id   int(32)                            null comment '押运员用户id',
  vehicle_id      int(32)                            null comment '车辆id',
  trailer_id      int(32)                            null comment '挂车id',
  attention       text                               null comment '注意事项',
  measures        text                               null comment '应急处置措施',
  status          int(1)   default 0                 null comment '运单状态  0:新建 1:已调度 2:运输中 3:待审核 4:已完成 5:已拒绝',
  node_id         int(32)                            null comment '当前节点id',
  remark          text                               null comment '备注',
  created_at      datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at      datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted      tinyint  default 0                 null comment '是否删除',
  constraint order_code
    unique (order_code)
)
  comment '运单表';

create table waybill_order_goods
(
  id           int auto_increment comment 'id'
    primary key,
  order_code   varchar(100)                       not null comment '运单编号 ',
  goods_code   varchar(64)                        null comment '联合国统一编号',
  goods_name   varchar(128)                       null comment '货物名称',
  goods_type   varchar(128)                       null comment '类别',
  goods_size   varchar(128)                       null comment '包装规格',
  goods_weight varchar(128)                       null comment '重量',
  goods_volume int(32)                            null comment '体积(m3)',
  remark       text                               null comment '备注',
  created_at   datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at   datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted   tinyint  default 0                 null comment '是否删除'
)
  comment '运单-货物表';

create table waybill_order_node
(
  id         int auto_increment comment 'id'
    primary key,
  order_code varchar(100)                       null comment '运单编号 ',
  node_id    int(32)                            null comment '节点id',
  content    text                               null comment '节点内容',
  created_at datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted tinyint  default 0                 null comment '是否删除'
)
  comment '运单-节点事件';

create table waybill_owner
(
  id                    int auto_increment comment '企业id identity (1,1) not null '
    primary key,
  owner_code            varchar(128)                       null comment '企业编号',
  owner_type            varchar(100)                       null comment '企业类型(运输、危化、经营) 1.生产、2.经营、3.存储、4.使用、5.运输、6.回收',
  owner_name            varchar(100)                       null comment '企业名称 ',
  owner_jc              varchar(100)                       null comment '企业简称 ',
  register_capital      varchar(100)                       null comment '注册资本 ',
  register_time         datetime                           null comment '注册时间 ',
  area                  double                             null comment '厂区面积 ',
  person_num            varchar(20)                        null comment '在职人数 ',
  icbc_code             varchar(100)                       null comment '工商注册号',
  credit_code           varchar(100)                       null comment '统一信用代码 ',
  owner_addr            varchar(100)                       null comment '经营地址 ',
  register_addr         varchar(200)                       null comment '注册地址 ',
  mgr_code              varchar(6)                         null comment '经营地址所在区域',
  register_code         varchar(6)                         null comment '注册地址所在区域 ',
  mgr_area              varchar(500)                       null comment '经营范围 字符串',
  state                 int(10)  default 1                 null comment '经营状态 0：停业；1：营业；9：注销',
  contact               varchar(20)                        null comment '联系人 ',
  contact_phone         varchar(30)                        null comment '联系电话 ',
  govern_code           varchar(20)                        null comment '所属管辖范围 12位行政区划code',
  important_level       int(2)                             null comment '重大危险源级别 ',
  is_important_chemical int(2)                             null comment '是否涉及重点危险化学品 1是，0否',
  is_important_crafts   int(2)                             null comment '是否涉及重点化工工艺  1是，0否',
  owner_zt              int(10)                            null comment '0：新注册 1：乡镇审核中  2：区县审核中  3：市级审核中  5：审核通过  9：驳回',
  owner_zt_msg          varchar(255)                       null comment '审核反馈信息',
  owner_nature          int(10)                            null comment '企业性质 ',
  corp_name             varchar(100)                       null comment '法人姓名 ',
  corp_card             int(10)                            null comment '法人证件类型 ',
  corp_card_number      varchar(22)                        null comment '法人证件号码 ',
  corp_phone            varchar(100)                       null comment '法人联系电话 ',
  corp_photo            varchar(100)                       null comment '法人照片 ',
  industry              int(10)                            null comment '所属行业 ',
  lon                   varchar(50)                        null comment '经度',
  lat                   varchar(50)                        null comment '维度',
  business_level_count  varchar(255)                       null comment '等级',
  business_technology   varchar(255)                       null comment '工艺',
  business_goods        varchar(255)                       null comment '营业范围',
  nonnative             varchar(1)                         null comment '外地企业（0：否；1：是；）',
  created_at            datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at            datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted            tinyint  default 0                 null comment '是否删除'
);

create table waybill_route
(
  id         int auto_increment comment 'id'
    primary key,
  owner_id   int                                not null comment '所属企业',
  name       varchar(64)                        not null comment '线路名称',
  start_addr varchar(128)                       not null comment '始发地',
  way        varchar(255)                       not null comment '途径',
  km         int                                not null comment '里程',
  stop_addr  varchar(128)                       not null comment '终点站',
  notice     text                               null comment '注意事项',
  map        text                               null comment '经纬度信息',
  status     tinyint  default 0                 null comment '0:激活 1:禁止',
  created_at datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted tinyint  default 0                 null comment '是否删除'
)
  comment '常用线路表';

create table waybill_user
(
  id            int auto_increment comment '人员编号 identity (1,1) not null '
    primary key,
  owner_id      varchar(100)                       null comment '所属单位id ',
  name          varchar(100)                       null comment '姓名 ',
  type          int(10)  default 0                 null comment '从业类型 0:装卸管理员 1:特种设备作业人员 2:驾驶员 3:押运员 4:其他人员',
  sex           int(1)                             null comment '性别 0:女  1:男',
  age           int(3)                             null comment '年龄 ',
  birthday      date                               null comment '出生年月 ',
  card_type     int(1)                             null comment '证件类型 0：其他  1：身份证   2：护照',
  id_card       varchar(100)                       null comment '证件号码 ',
  phone         varchar(100)                       null comment '联系电话 ',
  addr_place    varchar(6)                         null comment '家庭住址地区编号 ',
  address       varchar(100)                       null comment '地址 ',
  native_place  varchar(6)                         null comment '籍贯 ',
  photo_url     varchar(255)                       null comment '人员照片 ',
  feature       text                               null comment '人脸特征值 二进制字符串',
  review_status int                                null comment '审核状态 0不需要审核 1乡镇审核中 2区县审核中 3市级审核中 4审核通过 9驳回',
  flag          int                                null comment '是否离职  0:离职   1:入职',
  join_time     datetime                           null comment '入职时间',
  end_time      datetime                           null comment '合同到期时间',
  username      varchar(128)                       null comment '登陆用户名',
  password      varchar(128)                       null comment '登陆密码',
  token         varchar(128)                       null comment 'token',
  login_type    int(1)   default 0                 null comment '0:司机 1:调度员',
  state         int(1)   default 0                 null comment '0:可以拉货 1：不能拉货',
  status        tinyint  default 0                 null comment '0:激活 1:禁止',
  created_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at    datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted    tinyint  default 0                 null comment '是否删除'
)
  comment '人员用户信息表';

create table waybill_vehicle
(
  id                 int auto_increment comment '车辆id identity (1,1) not null '
    primary key,
  owner_id           varchar(100)                       null comment '所属单位id ',
  vehicle_code       varchar(100)                       null comment '车辆号码 ',
  vehicle_model      varchar(100)                       null comment '品牌型号',
  vehicle_type       int                                null comment '车辆类型（牵引车，挂车） 1:中型普通货车  2:保温冷藏车  3:其他车辆  4:危险品运输车  5:小型普通货车  6:平板车  7:挂车  8:牵引车  9:罐车  10:重型普通货车  11:集装箱车',
  vehicle_status     int(2)                             null comment '营运状态 0:营运;1:停运;2:报废;3:停驶;4:其他',
  color              int                                null comment '车牌颜色 jt/t 415规定',
  manage_area        varchar(100)                       null comment '经营范围 ',
  transport_number   varchar(100)                       null comment '道路运输证号 ',
  first_date         date                               null comment '有效期起 ',
  expire_date        date                               null comment '有效期至 ',
  grant_organ        varchar(100)                       null comment '发证机关 ',
  load_capacity      decimal(9, 3)                      null comment '核定载重量 ',
  ton                decimal(9, 3)                      null comment '总质量 ',
  volume             varchar(100)                       null comment '罐体容积 ',
  is_toxic           int                                null comment '是否运输毒物 0：不是  1：是',
  is_etch            int                                null comment '是否运输强腐蚀 0：不是  1：是',
  is_explode         int                                null comment '是否运输爆炸品 0：不是  1：是',
  insure_money       double                             null comment '危险货物承运人责任保险投保金额 ',
  insure_end_date    date                               null comment '危险货物承运人责任保险有效期至 ',
  default_pilot      varchar(100)                       null comment '默认驾驶员id ',
  default_supercargo varchar(100)                       null comment '默认押运员id ',
  default_car        varchar(100)                       null comment '默认挂车id ',
  default_car_code   varchar(100)                       null comment '默认挂车车牌号 ',
  vehicle_rank       varchar(100)                       null comment '车辆技术等级 ',
  next_tec_date      date                               null comment '等级评定日期 ',
  next_maintain_date date                               null comment '二级维护日期 ',
  next_check_date    date                               null comment '年审有效截止期 ',
  cert_photo         varchar(255)                       null comment '资质照片 ',
  car_photo          varchar(255)                       null comment '车辆照片 ',
  test_no            varchar(100)                       null comment '罐体检测报告编号 ',
  transport_scope    varchar(100)                       null comment '运输范围',
  map_color          int      default 2                 not null comment '地图车辆颜色：2绿色：无运单，3黄色：有运单空车（运单状态已出发-装货完成完成，卸货完成-运单完成），4蓝色：有运单重车（装货完成-卸货完成），另外，1，5状态不存库  ，1红色：车辆异常（t_gps_vehiclegps表中alarmState>0的时候表示异常）5灰色：离线（t_gps_vehiclegps表中is_online=0的时候表示离线）',
  state              int(1)   default 0                 null comment '0:可以派车  1：不可以派出',
  created_at         datetime default CURRENT_TIMESTAMP null comment '创建时间',
  updated_at         datetime default CURRENT_TIMESTAMP null comment '更新时间',
  is_deleted         tinyint  default 0                 null comment '是否删除'
)
  comment '车辆信息表';


