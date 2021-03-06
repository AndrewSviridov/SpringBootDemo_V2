package com.hanlp.instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.hankcs.hanlp.model.perceptron.feature.FeatureMap;
import com.hankcs.hanlp.model.perceptron.instance.NERInstance;
import com.hankcs.hanlp.model.perceptron.tagset.NERTagSet;

/**
 * Title: 
 * Description: 查获时间<SeizedOrganization> 命名实体 特征提取
 * Copyright: 2020 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SpringBootDemo
 * Author: 王杰
 * Create Time:2020/2/28 13:44
 */
public class CustomsNERInstanceForSeizedOrganization extends NERInstance {

	public CustomsNERInstanceForSeizedOrganization(String[] wordArray, String[] posArray, String[] nerArray, NERTagSet tagSet, FeatureMap featureMap) {
		super(wordArray, posArray, nerArray, tagSet, featureMap);
	}

	public CustomsNERInstanceForSeizedOrganization(String[] wordArray, String[] posArray, FeatureMap featureMap) {
		super(wordArray, posArray, featureMap);
	}

	@Override
	protected int[] extractFeature(String[] wordArray, String[] posArray, FeatureMap featureMap, int position) {
		List<Integer> featVec = new ArrayList<>();

		String pre2Word = position >= 2 ? wordArray[position - 2] : "_B_";
		String preWord = position >= 1 ? wordArray[position - 1] : "_B_";
		String curWord = wordArray[position];
		String nextWord = position <= wordArray.length - 2 ? wordArray[position + 1] : "_E_";
		String next2Word = position <= wordArray.length - 3 ? wordArray[position + 2] : "_E_";

		String pre2Pos = position >= 2 ? posArray[position - 2] : "_B_";
		String prePos = position >= 1 ? posArray[position - 1] : "_B_";
		String curPos = posArray[position];
		String nextPos = position <= posArray.length - 2 ? posArray[position + 1] : "_E_";
		String next2Pos = position <= posArray.length - 3 ? posArray[position + 2] : "_E_";

		// 下面是动词、介词
		String customsNextPos = position <= posArray.length - 2 ?
				((Objects.equals("v", posArray[position + 1]) || Objects.equals("p", posArray[position + 1])) ? "Y" : "N") : "N";
		String customsCurrentPos = Objects.equals("nt", posArray[position]) ? "IS_NT" : "NOT_NT";

		StringBuilder sb = new StringBuilder();
		addFeatureThenClear(sb.append(pre2Word).append('1'), featVec, featureMap);
		addFeatureThenClear(sb.append(preWord).append('2'), featVec, featureMap);
		addFeatureThenClear(sb.append(curWord).append('3'), featVec, featureMap);
		addFeatureThenClear(sb.append(nextWord).append('4'), featVec, featureMap);
		addFeatureThenClear(sb.append(next2Word).append('5'), featVec, featureMap);

		addFeatureThenClear(sb.append(pre2Pos).append('A'), featVec, featureMap);
		addFeatureThenClear(sb.append(prePos).append('B'), featVec, featureMap);
		addFeatureThenClear(sb.append(curPos).append('C'), featVec, featureMap);
		addFeatureThenClear(sb.append(nextPos).append('D'), featVec, featureMap);
		addFeatureThenClear(sb.append(next2Pos).append('E'), featVec, featureMap);
		addFeatureThenClear(sb.append(pre2Pos).append(prePos).append('F'), featVec, featureMap);
		addFeatureThenClear(sb.append(prePos).append(curPos).append('G'), featVec, featureMap);
		addFeatureThenClear(sb.append(curPos).append(nextPos).append('H'), featVec, featureMap);
		addFeatureThenClear(sb.append(nextPos).append(next2Pos).append('I'), featVec, featureMap);

		// 考虑词性问题
		addFeatureThenClear(sb.append(customsNextPos).append('1'), featVec, featureMap);
		addFeatureThenClear(sb.append(customsNextPos).append('2'), featVec, featureMap);
		addFeatureThenClear(sb.append(customsNextPos).append('3'), featVec, featureMap);
		addFeatureThenClear(sb.append(customsNextPos).append('4'), featVec, featureMap);
		addFeatureThenClear(sb.append(customsCurrentPos).append('1'), featVec, featureMap);
		addFeatureThenClear(sb.append(customsCurrentPos).append('2'), featVec, featureMap);
		addFeatureThenClear(sb.append(customsCurrentPos).append('3'), featVec, featureMap);
		addFeatureThenClear(sb.append(customsCurrentPos).append('4'), featVec, featureMap);

		return toFeatureArray(featVec);
	}
}
