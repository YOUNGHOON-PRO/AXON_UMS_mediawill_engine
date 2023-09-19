package kr.co.enders.engine.util;

import java.io.UnsupportedEncodingException;

public class StrConvertUtil
{
  public String convertToKr(String ch) throws Exception
  {
    String rtnString = "";
    if (ch != null) {
      rtnString = new String(ch.getBytes("8859_1"), "EUC_KR");
    }
    return rtnString;
  }

  public String convertToEn(String ch) throws Exception
  {
    String rtnString = "";
    if (ch != null) {
      rtnString = new String(ch.getBytes("EUC_KR"), "8859_1");
    }
    return rtnString;
  }

  public String convertAlti(String ch) throws Exception
  {
    String rtnString = "";
    if (ch != null) {
      rtnString = new String(ch.getBytes("EUC_KR"), "KSC5601");
    }
    return rtnString;
  }

  public String convertToUTF(String ch) throws Exception
  {
    String rtnString = "";
    if (ch != null) {
      rtnString = new String(ch.getBytes("EUC_KR"), "UTF-8");
    }
    return rtnString;
  }

  public String a2h(String ch) throws Exception
  {
    String rtnString = "";
    if (ch != null) {
      rtnString = new String(ch.getBytes("US7ASCII"), "KSC5601");
    }
    return rtnString;
  }

  public String[] convertToKr(String[] chArray) throws Exception
  {
    String[] rtnStringArray = new String[chArray.length];
    for (int i = 0; i < chArray.length; i++) {
      rtnStringArray[i] = convertToKr(chArray[i]);
    }
    return rtnStringArray;
  }

  /**
   * 8859_1 --> KSC5601.
   */
  public static String E2K(String english)
  {
    String korean = null;

    if (english == null) { return null; }
    // if (english == null ) return "";

    try {
      korean = new String(english.getBytes("8859_1"), "KSC5601");
    }
    catch (UnsupportedEncodingException e) {
      korean = new String(english);
    }
    return korean;
  }

  /**
   * KSC5601 --> 8859_1.
   */
  public static String K2E(String korean)
  {
    String english = null;

    if (korean == null) { return null; }
    // if (korean == null ) return "";

    english = new String(korean);
    try {
      english = new String(korean.getBytes("KSC5601"), "8859_1");
    }
    catch (UnsupportedEncodingException e) {
      english = new String(korean);
    }
    return english;
  }

  /**
   * 바이트로변환후 한국유니코드적용
   */
  public static String us2kr(String src)
  {
    String ret = "";
    try {
      if (src.length() > 0) {
        ret = new String(src.getBytes("8859_1"), "KSC5601");
      }
    }
    catch (Exception e) {
    }
    return ret;
  }

  /**
   * Class constructor.
   */
  public StrConvertUtil() {
  }
}
