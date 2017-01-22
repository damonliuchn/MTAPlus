# MTAPlus
MTA(腾讯移动分析) Android SDK 封装: Maven引用即可，不用关心AndroidManifest里的配置和Proguard混淆。
##使用
###一、配置
```goovy
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

	dependencies {
	        compile 'com.github.MasonLiuChn:MTAPlus:2.3.0.0'
	}
```

```java
MTAPlus.init(context,"appKey", "channel");
```
###二、应用方单独配置

1、
```java
<!-- MID3.5(mid-sdk-3.5.jar) 以上版本请务必增加此配置-->
<provider
  android:name="com.tencent.mid.api.MidProvider"
  android:authorities="你的包名.TENCENT.MID.V3"
  android:exported="true" >
</provider>
```

2、自行配置libMtaNativeCrash.so

###三、其他使用

```java
		//页面统计
        @Override
        protected void onResume () {
        	super.onResume();
        	StatService.onResume(this);
    	}
        @Override
        protected void onPause () {
        	super.onPause();
        	StatService.onPause(this);
    	}
		//自定义事件
        StatService.trackCustomEvent(this, "onCreate", "");
	
		//上报用户反馈和截屏文件
		postFeedBackFiles(Context context,String strContent, String strScreenshotFileName,StatFBDispatchCallback cb)
		//获取反馈消息列表
		getFeedBackMessage(Context context,int offset,int numLine,StatFBDispatchCallback cb)
		//回复当前反馈消息
		replyFeedBackMessage(Context context, String fbId, String content, StatFBDispatchCallback cb)

        //自定义上传Exception
        try {
            int retval = totalNumber / personInRoom;
        } catch (ArithmeticException e) {
            StatService.reportException(this, e);
        }

        //关闭Exception追踪，app的最初始代码处  必须在所有StatService方法调用之前设置此接口
        StatConfig.setAutoExceptionCaught(false);

        //打开NativeException，app的最初始代码处
        StatConfig.initNativeCrashReport(this, null);

        //key-value 自定义事件
        Properties prop = new Properties();
        prop.setProperty("name", " OK ");
        StatService.trackCustomKVEvent(this, " button_click", prop);

        //自定义事件 时常
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

        //网速测试
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("www.qq.com", 80);
        map.put("pingma.qq.com", 80);
        StatService.testSpeed(ctx, map);

        //获取设备唯一标识
        StatConfig.getMid(Context ctx)


        //使用QQ号做用户画像
        String qq = getUserQQ();
        StatService.reportQQ(this, qq);

        // 游戏用户上报
        StatGameUser gameUser = new StatGameUser();
        gameUser.setWorldName("world1");
        gameUser.setAccount("account1");
        gameUser.setLevel("100");
        StatService.reportGameUser(ctx, gameUser);


        // 获取在线参数onlineKey
        String onlineValue = StatConfig.getCustomProperty("onlineKey", "off");
        if (onlineValue.equalsIgnoreCase("on")) {
            // do something
        } else {
            // do something else
        }

        //设置应用的用户体系帐号
        void StatConfig.setCustomUserId(Context ctx, String customUserId)
```
