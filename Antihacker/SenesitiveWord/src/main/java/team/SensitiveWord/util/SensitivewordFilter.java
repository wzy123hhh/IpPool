package team.SensitiveWord.util;

import com.alibaba.fastjson.JSONObject;
import team.SensitiveWord.entity.UrlInfo;
import team.SensitiveWord.verifySensitive.initSensitiveWordsMap;

import java.util.*;

public class SensitivewordFilter extends initSensitiveWordsMap {
	@SuppressWarnings("rawtypes")
	public static int minMatchTYpe = 1;
	public static int maxMatchType = 2;

	private List<HashMap> sensitiveWordMapList = new ArrayList<>();

	public SensitivewordFilter(){
		super();
		//暴恐敏感词
		sensitiveWordMapList.add(FBsensitiveWordMap);
		//反动敏感词
		sensitiveWordMapList.add(FDsensitiveWordMap);
		//色情敏感词
		sensitiveWordMapList.add(SQsensitiveWordMap);
		//其他敏感词
		sensitiveWordMapList.add(QTsensitiveWordMap);
		//民生敏感词
		sensitiveWordMapList.add(BLsensitiveWordMap);
		//自定义敏感词
		sensitiveWordMapList.add(customizeSensitiveWordMap);
	}

	/**
	 * 判断文字是否包含敏感词
	 * @param txt 文字
	 * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @param type 要匹配的敏感词类型(0,1,2,3,4,5)
	 * @return 包含true
	 */
//	public boolean isContaintSensitiveWord(String txt,int matchType,int... type){
//		boolean flag = false;
//		for(int i = 0 ; i < txt.length() ; i++){
//
//			HashMap<Integer, Integer> integerIntegerHashMap = this.CheckSensitiveWord(txt, i, matchType, type);
//			for (Integer key : integerIntegerHashMap.keySet()) {
//				if (integerIntegerHashMap.get(key)>0){
//					return true;
//				}
//			}
//
//		}
//		return flag;
//	}

	/**
	 * 获取文本中存在的敏感词
	 * @param txt
	 * @param matchType
	 * @return
	 */
//	public HashMap<Integer,String> getSensitiveWord(String txt , int matchType,int... type){
//		HashMap<Integer,String> wordmap = new HashMap<>();
//		boolean flag=false;
//		int max=0;
//		for(int i = 0 ; i < txt.length() ; i++){
//			max=0;
//			HashMap<Integer, Integer> resmap = CheckSensitiveWord(txt, i, matchType, type);
//
//			for (int key : resmap.keySet()) {
//				if (key>max){
//					max=key;
//				}
//				int value = resmap.get(key);
//				if (value>1){
//					wordmap.put(key,txt.substring(i, i+value));
//					System.out.println("key: "+key+"--->"+txt.substring(i, i+value));
//				}
//			}
//			i=i+max-1;
//		}
//
//		return wordmap;
//	}

	/**
	 * 获取敏感词
	 * @param info 网站信息实体
	 * @param matchType 匹配原则
	 * @param type 匹配的敏感词库
	 * @return
	 */
	public UrlInfo getSensitiveWord(UrlInfo info, int matchType, int... type){

		if (type.length==0){
			type=new int[]{0,1,2,3,4};
		}

		Set<String> sensitiveWordList = new HashSet<String>();
		String text = info.getContent();

		for(int i = 0 ; i < text.length() ; i++){
			ArrayList<Object[]> objects = CheckSensitiveWord(text, i, matchType, type);//判断是否包含敏感字符
			if(objects!=null){    //存在,加入list中
				sensitiveWordList.add((int)objects.get(0)[0]+":"+text.substring(i, i+(int)objects.get(0)[1]));
				i = i + (int)objects.get(0)[1] - 1;    //减1的原因，是因为for会自增
			}
		}

		if (sensitiveWordList.size()!=0){
			Map<Integer, List<String>> integerListMap = handleSet(sensitiveWordList);
			String s = JSONObject.toJSONString(integerListMap);
			info.setViolation(true);
			info.setHits(s);
		}else {
			info.setViolation(false);
			info.setHits("无敏感词");
		}

		return info;
	}
	

//	public String replaceSensitiveWord(String txt,int matchType,String replaceChar){
//
//		String resultTxt = txt;
//		Set<String> set = getSensitiveWord(txt, matchType);
//		Iterator<String> iterator = set.iterator();
//		String word = null;
//		String replaceString = null;
//		while (iterator.hasNext()) {
//			word = iterator.next();
//			replaceString = getReplaceChars(replaceChar, word.length());
//			resultTxt = resultTxt.replaceAll(word, replaceString);
//		}
//		return resultTxt;
//
//	}

//	private String getReplaceChars(String replaceChar,int length){
//		String resultReplace = replaceChar;
//		for(int i = 1 ; i < length ; i++){
//			resultReplace += replaceChar;
//		}
//		return resultReplace;
//	}


//	public HashMap<Integer, Integer> CheckSensitiveWord(String txt, int beginIndex, int matchType, int... type){
//
//		boolean  flag = false;
//		int matchFlag = 0;
//		char word = 0;
//		HashMap nowMap = null;
//		HashMap<Integer,Integer> res = new HashMap<>();
//		for (int k : type) {
//			//遍历词库，结果存放进map
//			nowMap = sensitiveWordMapList.get(k);
//
//			for(int i = beginIndex; i < txt.length() ; i++){
//				word = txt.charAt(i);
//				nowMap = (HashMap) nowMap.get(word);
//				if(nowMap != null){
//					matchFlag++;
//					if("1".equals(nowMap.get("isEnd"))){
//						flag = true;
//						if(SensitivewordFilter.minMatchTYpe == matchType){
//							break;
//						}
//					}
//				}
//				else{
//					break;
//				}
//			}
//
//			if(matchFlag < 2 || !flag){
//				res.put(k,0);
//			}
//			res.put(k,matchFlag);
//		}
//		return res;
//	}


	/**
	 * 检查网页中是否含有敏感词
	 * @param txt 要检测的文本
	 * @param beginIndex 开始检查的位置
	 * @param matchType 检测的类型
	 * @param type 检测的敏感词库的类型
	 * @return
	 */
	public ArrayList<Object[]> CheckSensitiveWord(String txt, int beginIndex, int matchType, int... type){
		ArrayList<Object[]> res = new ArrayList<>();
		boolean  flag = false;
		int matchFlag = 0;
		char word = 0;
		HashMap nowMap = null;
		for (int k : type) {
			//遍历词库，结果存放进map
			nowMap = sensitiveWordMapList.get(k);

			for(int i = beginIndex; i < txt.length() ; i++){
				word = txt.charAt(i);
				nowMap = (HashMap) nowMap.get(word);
				if(nowMap != null){
					matchFlag++;
					if("1".equals(nowMap.get("isEnd"))){
						flag = true;
						if(SensitivewordFilter.minMatchTYpe == matchType){
							break;
						}
					}
				}
				else{
					break;
				}
			}

			if(matchFlag >= 2&& flag){
				res.add(new Object[]{k,matchFlag});
				return res;
			}

		}
		return null;
	}

	public static Map<Integer,List<String>> handleSet(Set<String> res){
		Map<Integer,List<String>> result = new HashMap<>();
		Iterator<String> iterator = res.iterator();
		while (iterator.hasNext()) {
			String[] split = iterator.next().split(":");
			int key = Integer.parseInt(split[0]);
			if (result.keySet().contains(key)) {
				result.get(key).add(split[1]);
			} else {
				List<String> word = new ArrayList<>();
				word.add(split[1]);
				result.put(key, word);
			}
		}
		return  result.size()==0?null:result;
	}


	public static void main(String[] args) {
		SensitivewordFilter filter = new SensitivewordFilter();
		String string = "太多的伤感 情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
				+ "然后法轮功我们的扮演的角色就是跟随着供铲谠的主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
				+ "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制观音法门器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
		System.out.println("待检测语句字数：" + string.length());
		long beginTime = System.currentTimeMillis();
//		HashMap<Integer, String> sensitiveWord = filter.getSensitiveWord(string, 1, 1,2,3,4,0);
//		Set<String> set = filter.getSensitiveWord(string, 1, 1, 2, 3, 4, 0);
		long endTime = System.currentTimeMillis();
//		System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);

//		Map<Integer, List<String>> result = handleSet(set);
//		System.out.println(JSONObject.toJSONString(result));

		System.out.println("总共消耗时间为：" + (endTime - beginTime));
	}
}
