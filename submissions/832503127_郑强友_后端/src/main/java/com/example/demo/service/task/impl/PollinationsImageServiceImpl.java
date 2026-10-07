package com.example.demo.service.task.impl;

import com.example.demo.service.task.ImageGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Pollinations 图片生成实现
 * 通过 image.service=pollinations 激活
 * <p>
 * Pollinations 是一个完全免费的 AI 图片生成服务（无需 API Key）
 * 调用方式：GET 请求，返回图片 URL
 * 生成的图片在浏览器访问时实时渲染，不消耗服务器资源
 * <p>
 * 文档：https://pollinations.ai/
 */
@Service
@ConditionalOnProperty(name = "image.service", havingValue = "pollinations")
@Slf4j
public class PollinationsImageServiceImpl implements ImageGenerationService {

    /** Pollinations 基础 URL */
    private static final String BASE_URL = "https://image.pollinations.ai/prompt/";

    /** 默认图片宽度 */
    private static final int WIDTH = 1024;

    /** 默认图片高度 */
    private static final int HEIGHT = 1024;

    /** 默认模型（flux 质量较好） */
    private static final String MODEL = "flux";

    @Override
    public String generate(String description) {
        // 1. URL 编码描述文本（保证中文/特殊字符正确）
        String encodedPrompt = URLEncoder.encode(description, StandardCharsets.UTF_8);

        // 2. 拼接 URL
        // 格式：https://image.pollinations.ai/prompt/{描述}?width=1024&height=1024&model=flux&nologo=true
        String url = BASE_URL + encodedPrompt
                + "?width=" + WIDTH
                + "&height=" + HEIGHT
                + "&model=" + MODEL
                + "&nologo=true";

        log.info("Pollinations 图片 URL 已生成，描述长度={}", description.length());
        return url;
    }
}