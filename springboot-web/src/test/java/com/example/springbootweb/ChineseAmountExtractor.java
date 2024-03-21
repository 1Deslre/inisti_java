package com.example.springbootweb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseAmountExtractor {

    public static List<String> extractChineseAmount(String text) {
        // 正则表达式模式匹配中文金额
        String pattern = "[壹贰叁肆伍陆柒捌玖拾佰仟万亿]+(?:元|块|圆)";
        // 使用Pattern和Matcher类找到所有匹配项
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);

        // 提取的中文金额列表
        List<String> extractedAmounts = new ArrayList<>();
        while (m.find()) {
            String chineseAmount = m.group();
            extractedAmounts.add(chineseAmount);
        }

        return extractedAmounts;
    }

    public static void main(String[] args) {
        // 示例字符串
        String text = "这笔交易金额为叁仟元，另外还有伍佰块钱。";
        // 提取中文金额
        List<String> amounts = extractChineseAmount(text);
        System.out.print("提取的中文金额：");
        for (String amount : amounts) {
            System.out.print(amount + " ");
        }
    }
}
