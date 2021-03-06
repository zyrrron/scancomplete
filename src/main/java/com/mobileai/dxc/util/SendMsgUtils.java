package com.mobileai.dxc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 发送短信工具类
 * 
 * @param to     被发送的手机号码
 * @param status 状态码：1.验证短信 2.通知短信（商家）3.通知短信（用户）4.通知短信（拒绝）
 * 
 * @return 随机数验证码
 */
public class SendMsgUtils {

    /**
     * ACCOUNT_SID:开发者主账号ID(注册后自动生成)
     */
    public static final String ACCOUNT_SID = "d8f51d252cef438cb65d90e912ce51c2";

    /**
     * AUTH_TOKEN:开发者账号认证密匙(注册后自动生成)
     */
    public static final String AUTH_TOKEN = "f4109d797322492b80b1ebf7e2816a6f";

    /**
     * BASE_URL:验证请求地址
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

    /**
     * RESP_DATA_TYPE:数据返回格式为JSON格式
     */
    public static final String RESP_DATA_TYPE = "json";

    /**
     * randNum:生成的验证码随机数
     */
    static String randNum = createRandNum();

    /**
     * smsContent_validate:短信内容(短信签名+短信内容，注意要和配置的模板一致，否则报错)
     */

    public static String[] smsContent = { "【稻香村】尊敬的用户，您的验证码为" + randNum, "【稻香村】亲爱的用户，您有新的订单等待确认，请及时登录。",
            "【稻香村】亲爱的用户，您的申请已经被接受，请在约定时间到达当地享受农家乐的欢愉吧！" ,"【稻花香】你有一个订单被退回，登录网站了解详情。"};

    /**
     * 
     * @Title：sendMsgTo
     * @Description：发送短信验证码 @param：
     * @return：String
     */
    public static String sendMsgTo(String to, int status) {

        /**
         * 获取时间戳
         */
        String timestamp = getTimestamp();

        /**
         * 获取签名
         */
        String sig = MD5Utils.md5(ACCOUNT_SID + AUTH_TOKEN + timestamp);

        /**
         * 要提交的post数据
         */
        String http_post = "accountSid=" + ACCOUNT_SID + "&smsContent=" + smsContent[status-1] + "&to=" + to
                + "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + RESP_DATA_TYPE;

        OutputStreamWriter osw = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(BASE_URL);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);
            osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(http_post);
            osw.flush();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randNum.toString();
    }

    /**
     * 
     * @Title:getTimestamp
     * @Description:获取时间戳
     * @param:
     * @return:String
     */
    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * 
     * @Title:createRandNum
     * @Description:生成一个6位的随机码
     * @param:
     * @return:String
     */
    public static String createRandNum() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= 5; i++) {
            String s = random.nextInt(10) + "";
            sb.append(s);
        }
        return sb.toString();
    }

}