create table gesture_violation_db.violation_record
(
    id             int auto_increment
        primary key,
    createTime     datetime              not null,
    gestureClass   varchar(50)           not null,
    judgeType      enum ('自动', '人工') not null,
    screenshotPath varchar(100)          null
)
    collate = utf8mb4_unicode_ci;


