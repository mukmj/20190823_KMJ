package com.java.hdfs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Mapper<(입력키<행번호> : 입력값<행의글자>) , (출력키<글자> : 출력값<1>)>
public class Map extends Mapper<LongWritable, Text, Text, IntWritable> {

	// 출력 키 변수
	protected Text textKey = new Text();
	// 출력 값 변수
	protected IntWritable intValue = new IntWritable();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		String[] col = value.toString().split(",");
		String year = col[0];
		String month = col[1];
		int delay = 0;
		if(col[15] != "NA") {
			delay = Integer.parseInt(col[15]);
		}
		
		// 출력 키에 문자열 변수 적용
		textKey.set(year + month);
		intValue.set(delay);
		System.out.println(intValue);
		// 전체 결과 출력하기
		context.write(textKey, intValue);
	}
	
}
