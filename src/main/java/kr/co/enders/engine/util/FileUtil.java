package kr.co.enders.engine.util;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.enders.engine.vo.SegmentMemberVO; 

public class FileUtil
{
	
	public static String[] readFileByLine(String filePath) throws IOException {

		ArrayList<Object> resultList = new ArrayList<Object>(); 
		String[] result = null;

		String line = ""; 
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(filePath));
			while ((line = in.readLine()) != null) {
				if (!line.trim().equals("")) {
					resultList.add(line.trim());
				}
			}
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (in !=null) {
				in.close();
			}
		}
		
		result = new String[resultList.size()];
		resultList.toArray(result);
		return result;
	}
	
	public static SegmentMemberVO getMemberList(String filePath) throws IOException {
		
		String line = "";
		BufferedReader br = null;
		boolean first = true;
		String[] header =null ;
		
		SegmentMemberVO memberVO = new SegmentMemberVO();
		List<HashMap<String,String>> memberList = new ArrayList<HashMap<String, String>>();
		
		try {
			br = new BufferedReader( new InputStreamReader (new FileInputStream(filePath), "UTF-8"));
			
			while ( (line = br.readLine()) != null) {
				if (line.trim().length() == 0) {
					continue;
				}
				if (first) { 
					header = line.split(",");
					first = false;
				} else {
					HashMap<String,String> member = new HashMap<String,String>();
					CSVTokenizer ct = new CSVTokenizer(line);
					int idx = 0 ;
					while (ct.hasMoreTokens()) {
						String token = ct.nextToken();
						member.put(header[idx],token );
						idx++;
					}
					memberList.add(member);
				}
			}
			memberVO.setMemberList(memberList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return memberVO;
	}
}
