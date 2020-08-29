1.在 JVM 启动的时候加载，通过 javaagent 启动参数 java -javaagent:myagent.jar MyMain，这种方式在程序 main 方法执行之前执行 agent 中的 premain 方法
 public static void premain(String agentArgument, Instrumentation instrumentation) throws Exception
 2.在 JVM 启动后 Attach，通过 Attach API 进行加载，这种方式会在 agent 加载以后执行 agentmain 方法
 public static void agentmain(String agentArgument, Instrumentation instrumentation) throws Exception

这两个方法都有两个参数
 第一个 agentArgument 是 agent 的启动参数，可以在 JVM 启动命令行中设置，比如java -javaagent:<jarfile>=appId:agent-demo,agentType:singleJar test.jar的情况下 agentArgument 的值为 "appId:agent-demo,agentType:singleJar"。
 第二个 instrumentation 是 java.lang.instrument.Instrumentation 的实例，可以通过 addTransformer 方法设置一个 ClassFileTransformer。