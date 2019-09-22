package team.SensitiveWord.verifySensitive;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import team.SensitiveWord.util.SqlUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class initSensitiveWordsMap {

    protected static HashMap FBsensitiveWordMap = null;
    //反动敏感词
    protected static HashMap  FDsensitiveWordMap = null;
    //暴力敏感词
    protected static HashMap  BLsensitiveWordMap = null;
    //色情敏感词
    protected static HashMap SQsensitiveWordMap = null;
    //
    protected static HashMap QTsensitiveWordMap = null;

    //用户自定义词库
    protected static HashMap customizeSensitiveWordMap = null;

    protected static QueryRunner queryRunner = SqlUtil.getQueryRunner();

    public initSensitiveWordsMap() {
        initSensitiveWordsMap();
    }

    /**
     * 初始化敏感词库
     */
    public static void initSensitiveWordsMap(){

        initFDSensitiveWordsMap();
        initSQSensitiveWordsMap();
        initFBSensitiveWordsMap();
        initQTSensitiveWordsMap();
        initBLSensitiveWordsMap();
    }

    /**
     * 初始化用户自定义敏感词库
     * @param customize
     */
    public static void initcustomizeSensitiveWord(List<String> customize){
        customizeSensitiveWordMap = new HashMap(customize.size());
        String key = null;
        HashMap nowMap = null;
        HashMap<String, String> newWorMap = null;
        Iterator<String> iterable =customize.iterator();
        while (iterable.hasNext()){
            key = iterable.next();
            nowMap = customizeSensitiveWordMap;

            for(int i = 0 ; i < key.length() ; i++){

                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if(wordMap != null){        //如果存在该key，直接赋值
                    nowMap = (HashMap) wordMap;
                }
                else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }

    private static void initQTSensitiveWordsMap(){//String tablename,HashMap sensitiveWordMap

        try {
            List<String> result = queryRunner.query("select words from qtwords", new ColumnListHandler<String>("words"));
            QTsensitiveWordMap = new HashMap(result.size());
            String key = null;
            HashMap nowMap = null;
            HashMap<String, String> newWorMap = null;

            Iterator<String> iterable =result.iterator();

            while (iterable.hasNext()){
                key = iterable.next();
                nowMap = QTsensitiveWordMap;

                for(int i = 0 ; i < key.length() ; i++){

                    char keyChar = key.charAt(i);       //转换成char型
                    Object wordMap = nowMap.get(keyChar);       //获取

                    if(wordMap != null){        //如果存在该key，直接赋值
                        nowMap = (HashMap) wordMap;
                    }
                    else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                        newWorMap = new HashMap<String,String>();
                        newWorMap.put("isEnd", "0");     //不是最后一个
                        nowMap.put(keyChar, newWorMap);
                        nowMap = newWorMap;
                    }

                    if(i == key.length() - 1){
                        nowMap.put("isEnd", "1");    //最后一个
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initBLSensitiveWordsMap(){//String tablename,HashMap sensitiveWordMap

        try {
            List<String> result = queryRunner.query("select words from mswords", new ColumnListHandler<String>("words"));
            BLsensitiveWordMap = new HashMap(result.size());
            String key = null;
            HashMap nowMap = null;
            HashMap<String, String> newWorMap = null;

            Iterator<String> iterable =result.iterator();

            while (iterable.hasNext()){
                key = iterable.next();
                nowMap = BLsensitiveWordMap;

                for(int i = 0 ; i < key.length() ; i++){

                    char keyChar = key.charAt(i);       //转换成char型
                    Object wordMap = nowMap.get(keyChar);       //获取

                    if(wordMap != null){        //如果存在该key，直接赋值
                        nowMap = (HashMap) wordMap;
                    }
                    else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                        newWorMap = new HashMap<String,String>();
                        newWorMap.put("isEnd", "0");     //不是最后一个
                        nowMap.put(keyChar, newWorMap);
                        nowMap = newWorMap;
                    }

                    if(i == key.length() - 1){
                        nowMap.put("isEnd", "1");    //最后一个
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initFDSensitiveWordsMap(){//String tablename,HashMap sensitiveWordMap

        try {
            List<String> result = queryRunner.query("select words from fdongwords", new ColumnListHandler<String>("words"));
            FDsensitiveWordMap = new HashMap(result.size());
            String key = null;
            HashMap nowMap = null;
            HashMap<String, String> newWorMap = null;

            Iterator<String> iterable =result.iterator();

            while (iterable.hasNext()){
                key = iterable.next();
                nowMap = FDsensitiveWordMap;

                for(int i = 0 ; i < key.length() ; i++){

                    char keyChar = key.charAt(i);       //转换成char型
                    Object wordMap = nowMap.get(keyChar);       //获取

                    if(wordMap != null){        //如果存在该key，直接赋值
                        nowMap = (HashMap) wordMap;
                    }
                    else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                        newWorMap = new HashMap<String,String>();
                        newWorMap.put("isEnd", "0");     //不是最后一个
                        nowMap.put(keyChar, newWorMap);
                        nowMap = newWorMap;
                    }

                    if(i == key.length() - 1){
                        nowMap.put("isEnd", "1");    //最后一个
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initFBSensitiveWordsMap(){//String tablename,HashMap sensitiveWordMap

        try {
            List<String> result = queryRunner.query("select words from bkwords", new ColumnListHandler<String>("words"));
            FBsensitiveWordMap = new HashMap(result.size());
            String key = null;
            HashMap nowMap = null;
            HashMap<String, String> newWorMap = null;

            Iterator<String> iterable =result.iterator();

            while (iterable.hasNext()){
                key = iterable.next();
                nowMap = FBsensitiveWordMap;

                for(int i = 0 ; i < key.length() ; i++){

                    char keyChar = key.charAt(i);       //转换成char型
                    Object wordMap = nowMap.get(keyChar);       //获取

                    if(wordMap != null){        //如果存在该key，直接赋值
                        nowMap = (HashMap) wordMap;
                    }
                    else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                        newWorMap = new HashMap<String,String>();
                        newWorMap.put("isEnd", "0");     //不是最后一个
                        nowMap.put(keyChar, newWorMap);
                        nowMap = newWorMap;
                    }

                    if(i == key.length() - 1){
                        nowMap.put("isEnd", "1");    //最后一个
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initSQSensitiveWordsMap(){//String tablename,HashMap sensitiveWordMap

        try {
            List<String> result = queryRunner.query("select words from sqwords", new ColumnListHandler<String>("words"));
            SQsensitiveWordMap = new HashMap(result.size());
            String key = null;
            HashMap nowMap = null;
            HashMap<String, String> newWorMap = null;

            Iterator<String> iterable =result.iterator();

            while (iterable.hasNext()){
                key = iterable.next();
                nowMap = SQsensitiveWordMap;

                for(int i = 0 ; i < key.length() ; i++){

                    char keyChar = key.charAt(i);       //转换成char型
                    Object wordMap = nowMap.get(keyChar);       //获取

                    if(wordMap != null){        //如果存在该key，直接赋值
                        nowMap = (HashMap) wordMap;
                    }
                    else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                        newWorMap = new HashMap<String,String>();
                        newWorMap.put("isEnd", "0");     //不是最后一个
                        nowMap.put(keyChar, newWorMap);
                        nowMap = newWorMap;
                    }

                    if(i == key.length() - 1){
                        nowMap.put("isEnd", "1");    //最后一个
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
