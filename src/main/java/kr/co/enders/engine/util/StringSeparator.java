package kr.co.enders.engine.util;

import java.util.StringTokenizer;

public class StringSeparator
{

  public static String[] doSeparator(String sentence, String sep_key)
  {
    StringTokenizer st = new StringTokenizer(sentence, sep_key);
    String[] rtn_array = new String[st.countTokens()];
    for (int i = 0; i < rtn_array.length; i++) {
      rtn_array[i] = st.nextElement().toString();
    }
    return rtn_array;
  }

  public static String[] doSplit(String sentence, String sep_key)
  {
    String[] rtn_array = StringUtil.nvl(sentence, "").length() > 0 ? sentence.split(sep_key) : null;
    //System.out.println("***** sep_key=[" + sep_key + "] / sentence=[" + sentence + "]");
    return rtn_array;
  }
  
  public String colonSep(String allcmd)
  {
    return allcmd.substring(allcmd.indexOf(":") + 1, allcmd.indexOf(";"));
  }
 
  public String beforeEqual(String key_val)
  {
    return key_val.substring(0, key_val.indexOf("="));
  }

  public String afterEqual(String key_val)
  {
    String rtnStr = "";
    if (key_val.indexOf("=") != -1) {
      rtnStr = key_val.substring(key_val.indexOf("=") + 1);
      if (rtnStr.indexOf(",") != -1) {
        rtnStr = key_val.substring(key_val.indexOf("=") + 1, key_val.indexOf(","));
      }
    }
    return rtnStr;
  }
 
  public String[] doCMDSeparator(String allcmd)
  { // 필수, 선택 구분하지 않음
    String[] rtn_array;
    String key = colonSep(allcmd);
    String[] key_val = doSeparator(key, " "); // key 값만.. 빼낸다.
    rtn_array = new String[key_val.length]; // key 값의 갯수를 빼낸다.
    for (int i = 0; i < rtn_array.length; i++) { // key 에서 실제 value값만 빼낸다.
      rtn_array[i] = afterEqual(key_val[i]);
    }
    return rtn_array;
  }
 
  public String[] doMandatoryVals(String allcmd, String[] mandatory_key)
  { // 필수만
    String[] rtn_array = new String[mandatory_key.length];
    String key = colonSep(allcmd);
    String[] key_val = doSeparator(key, " ");
    String tmp_val1 = "";
    String tmp_val2 = "";
    for (int i = 0; i < mandatory_key.length; i++) {
      tmp_val1 = beforeEqual(key_val[i]);
      tmp_val2 = afterEqual(key_val[i]);
      if (tmp_val1.equals(mandatory_key[i])) {
        rtn_array[i] = tmp_val2;
      }
    }
    return rtn_array;
  }
 
  public String[] doOptionVals(String allcmd, String[] option_key)
  { // 선택만
    String[] rtn_array = new String[option_key.length];
    String key = colonSep(allcmd); // ':' 다음 내용만 분리
    String[] key_val = doSeparator(key, " "); // key=value 로 분리
    String tmp_val1 = "";
    String tmp_val2 = "";
    for (int i = 0; i < option_key.length; i++) {
      for (int j = 0; j < key_val.length; j++) {
        tmp_val1 = beforeEqual(key_val[j]);
        tmp_val2 = afterEqual(key_val[j]);
        if (option_key[i].equals(tmp_val1)) {
          rtn_array[i] = tmp_val2;
        }
      }
    }
    return rtn_array;
  }
 
  public String[] doOptionVals2(String allcmd, String[] option_key)
  { // 선택만
    String[] rtn_array = new String[1];
    return rtn_array;
  }
 
  public StringSeparator() {
  }

}
