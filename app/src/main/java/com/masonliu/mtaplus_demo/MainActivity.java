package com.masonliu.mtaplus_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;
/**
 try{
 int retval = totalNumber / personInRoom;
 }
 catch (ArithmeticException e){
 StatService.reportException(this, e);
 }

 // app的最初始代码处  必须在所有StatService方法调用之前设置此接口
 StatConfig.setAutoExceptionCaught(false);

 // app的最初始代码处
 StatConfig.initNativeCrashReport (this, null);

 Properties prop = new Properties();
 prop.setProperty("name", " OK ");
 StatService.trackCustomKVEvent(this, " button_click", prop);

 Properties prop = new Properties();
 prop.setProperty("level", "5");
 // 统计用户通关所花时长，关卡等级： 5
 // 用户通关前
 StatService.trackCustomBeginKVEvent(this, " playTime", prop);
 // 用户正在游戏中….
 // …….
 // 用户通关完成时
 StatService.trackCustomEndKVEvent(this, " playTime", prop);




 // 统计用户通关所花时长
 // 用户通关前
 StatService.trackCustomBeginEvent(this, " playTime", "level5");
 // 用户正在游戏中….
 // …….
 // 用户通关完成时
 StatService.trackCustomEndEvent(this, " playTime", " level5");






 // 新建监控接口对象
 StatAppMonitor monitor = new StatAppMonitor("ping:www.qq.com");
 String ip = "www.qq.com";
 Runtime run = Runtime.getRuntime();
 java.lang.Process proc = null;
 try {
 String str = "ping -c 3 -i 0.2 -W 1 " + ip;
 long starttime = System.currentTimeMillis();
 // 被监控的接口
 proc = run.exec(str);
 proc.waitFor();
 long difftime = System.currentTimeMillis() - starttime;
 // 设置接口耗时
 monitor.setMillisecondsConsume(difftime);
 int retCode = proc.waitFor();
 // 设置接口返回码
 monitor.setReturnCode(retCode);
 // 设置请求包大小，若有的话
 monitor.setReqSize(1000);
 // 设置响应包大小，若有的话
 monitor.setRespSize(2000);
 // 设置抽样率
 // 默认为1，表示100%。
 // 如果是50%，则填2(100/50)，如果是25%，则填4(100/25)，以此类推。
 monitor.setSampling(2);
 if (retCode == 0) {
 logger.debug("ping连接成功");
 // 标记为成功
 monitor.setResultType(StatAppMonitor.SUCCESS_RESULT_TYPE);
 } else {
 logger.debug("ping测试失败");
 // 标记为逻辑失败，可能由网络未连接等原因引起的
 // 但对于业务来说不是致命的，是可容忍的
 monitor.setResultType(StatAppMonitor.LOGIC_FAILURE_RESULT_TYPE);
 }
 } catch (Exception e) {
 logger.e(e);
 // 接口调用出现异常，致命的，标识为失败
 monitor.setResultType(StatAppMonitor.FAILURE_RESULT_TYPE);
 } finally {
 proc.destroy();
 }
 // 上报接口监控
 StatService.reportAppMonitorStat(ctx, monitor);





 Map<String, Integer> map = new HashMap<String, Integer>();
 map.put("www.qq.com", 80);
 map.put("pingma.qq.com", 80);
 StatService.testSpeed(ctx, map);


 StatConfig.getMid(Context ctx)



 // 获取QQ号码
 String qq = getUserQQ();
 StatService.reportQQ(this, qq);

 // 游戏用户上报
 StatGameUser gameUser = new StatGameUser();
 gameUser.setWorldName("world1");
 gameUser.setAccount("account1");
 gameUser.setLevel("100");
 StatService.reportGameUser(ctx, gameUser);


 // 获取在线参数onlineKey
 String onlineValue = StatConfig.getCustomProperty("onlineKey", "off" );
 if(onlineValue.equalsIgnoreCase("on")){
 // do something
 }else{
 // do something else
 }


 void StatConfig.setCustomUserId(Context ctx, String customUserId)

 **/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatConfig.setAppKey("AE9N8RCM74WP");
        StatConfig.setInstallChannel("wode");
        StatConfig.setDebugEnable(true);
        StatService.trackCustomEvent(this, "onCreate", "");

    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }
}
