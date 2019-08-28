package com.myplace.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SensitiveWord {
    private StringBuilder replaceAll;// 初始化
    private String encoding = "utf-8";
    private String replceStr = "*";
    private int replceSize = 500;
    private static final String fileName = "key.txt";
    private List<String> arrayList;
    public Set<String> sensitiveWordSet;
    public List<String> sensitiveWordList;

    public SensitiveWord(String replceStr, int replceSize) {
        this.replceStr = fileName;
        this.replceSize = replceSize;
    }
    public SensitiveWord() { }
    public StringBuilder getReplaceAll() {
        return replaceAll;
    }
    public void setReplaceAll(StringBuilder replaceAll) {
        this.replaceAll = replaceAll;
    }
    public String getReplceStr() {
        return replceStr;
    }
    public void setReplceStr(String replceStr) {
        this.replceStr = replceStr;
    }
    public int getReplceSize() {
        return replceSize;
    }
    public void setReplceSize(int replceSize) {
        this.replceSize = replceSize;
    }
    public List<String> getArrayList() {
        return arrayList;
    }
    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }
    public String getEncoding() {
        return encoding;
    }
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
    /**
     * 将敏感字转换为*符号
     * @param str
     * @return
     */
    public String filterInfo(String str) {
        sensitiveWordSet = new HashSet<String>();
        sensitiveWordList = new ArrayList<>();
        StringBuilder buffer = new StringBuilder(str);
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());

        String temp;
        for (int x = 0; x < arrayList.size(); x++) {
            temp = arrayList.get(x);
            int findIndexSize = 0;
            for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1;) {
                findIndexSize = start + temp.length();
                Integer mapStart = hash.get(start);
                if (mapStart == null || (mapStart != null && findIndexSize > mapStart)) {
                    hash.put(start, findIndexSize);
                }
            }
        }
        Collection<Integer> values = hash.keySet();
        for (Integer startIndex : values) {
            Integer endIndex = hash.get(startIndex);
            String sensitive = buffer.substring(startIndex, endIndex);
            if (!sensitive.contains("*")) {
                sensitiveWordSet.add(sensitive);
            } sensitiveWordList.add(sensitive);
            buffer.replace(startIndex, endIndex, replaceAll.substring(0, endIndex - startIndex));
        }
        hash.clear();
        return buffer.toString();
    }
    /**
     * 初始化读取铭感文件库
     */
    public void InitializationWork() {
        replaceAll = new StringBuilder(replceSize);
        for (int x = 0; x < replceSize; x++) {
            replaceAll.append(replceStr);
        }
        arrayList = new ArrayList<String>();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
//            FileInputStream out = new FileInputStream(fileName);
//            read = new InputStreamReader(out, encoding);
            read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream(fileName), encoding);
            bufferedReader = new BufferedReader(read);
            for (String txt = null; (txt = bufferedReader.readLine()) != null; ) {
                if (!arrayList.contains(txt)) {
                    arrayList.add(txt);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != read) read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 判断是否有敏感词汇
     * @param str
     * @return
     */
    public static boolean checkSenstiveWord(String str) {
        // 初始敏感词库
        SensitiveWord sw = new SensitiveWord();
        sw.InitializationWork();
        str = sw.filterInfo(str);
        if (str.contains("*")) {
            return true;
        }
        return false;
    }
    public static String filterInfoAfter(String str) {
        // 初始敏感词库
        SensitiveWord sw = new SensitiveWord();
        sw.InitializationWork();
        str = sw.filterInfo(str);
        return str;
    }

    public static void main(String[] args) {
        long startNumer = System.currentTimeMillis();
        String str = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 私服我们的扮演的角色就是跟随着主人公的我.操喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
//        String str = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
//                + "然后 私服我们的扮演的角色就是跟随着主人公的我.操喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
//                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜片 深人静的晚上，关上电话静静的发呆着。";
        boolean flag = SensitiveWord.checkSenstiveWord(str);
        System.out.println("字符串的长度为:" + str.length());
        if(flag){
            str = SensitiveWord.filterInfoAfter(str);
        }

        long endNumber = System.currentTimeMillis();

        System.out.println("消耗时间为" + (endNumber - startNumer) + "ms");

        System.out.println("转换后的字符串为:\n" + str);
    }
}
