-- =============================================================
-- 参考数据种子脚本（手动执行一次，可通过 IDEA Database 控制台运行）
-- 本文件不会被 Spring 自动执行，避免与已有数据冲突
-- 覆盖 3 张参考数据表，保证核心功能可直接使用：
--   ai_model        出图模型（confirmTask 按 name 查算力消耗）
--   recharge_package 充值套餐（充值下单可选）
--   category        画廊作品分类（发布作品可选分类）
-- =============================================================
USE demo_db;

-- 1. AI 出图模型：确认任务时按 name='mock-image' 读取单次算力消耗
INSERT INTO ai_model (name, display_name, points_cost, status) VALUES
('mock-image', 'AI 绘图', 10, 1);

-- 2. 充值套餐
INSERT INTO recharge_package (name, points, price, type, status, created_at, updated_at) VALUES
('100 算力包',  100,  1.00, 'once', 1, NOW(), NOW()),
('500 算力包',  500,  5.00, 'once', 1, NOW(), NOW()),
('1000 算力包', 1000, 9.90, 'once', 1, NOW(), NOW());

-- 3. 画廊分类
INSERT INTO category (name, description, sort_order) VALUES
('人物', '人物插画 / 写真', 1),
('风景', '自然风景 / 城市', 2),
('动漫', '二次元 / 漫画风', 3),
('抽象', '抽象艺术 / 创意', 4);