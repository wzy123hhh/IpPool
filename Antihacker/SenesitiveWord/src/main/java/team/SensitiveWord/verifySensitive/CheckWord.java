package team.SensitiveWord.verifySensitive;

import team.SensitiveWord.util.SensitivewordFilter;

import java.util.HashMap;

/**
 * DFA算法检测敏感词
 */
public class CheckWord extends initSensitiveWordsMap{

    public int CheckSensitiveWord(String txt,int beginIndex,int matchType){
        boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0;     //匹配标识数默认为0
        char word = 0;
        HashMap nowMap = FBsensitiveWordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (HashMap) nowMap.get(word);     //获取指定key
            if(nowMap != null){     //存在，则判断是否为最后一个
                matchFlag++;     //找到相应key，匹配标识+1
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true
                    if(SensitivewordFilter.minMatchTYpe == matchType){    //最小规则，直接返回,最大规则还需继续查找
                        break;
                    }
                }
            }
            else{     //不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 && !flag){
            matchFlag = 0;
        }
        return matchFlag;
    }


}
