package android.auto.tools;

import java.io.*;

/**
 * Created by yuhao.zx on 16-5-15.
 */
public class AutoHelper {
    public static String WORKSPACE_PATH;
    public AutoHelper(String jarName,String targetClass,String targetMethod,String androidId){
        System.out.println("auto start");
        WORKSPACE_PATH = getWrokSpace();
        System.out.println("work path:"+WORKSPACE_PATH);
        createAntBuildXml(jarName,androidId);

        modfileBuild();

        antBuild();

        pushJarToAndroid(WORKSPACE_PATH + "\\bin\\" + jarName + ".jar");

        if (androidId.equals("")) {
            runTest(jarName, targetClass);
        } else {
            runTest(jarName, targetClass + "#" + targetMethod);
        }

        System.out.println("---AutoTest End----");
    }

    public void runTest(String jarName, String testName) {
        System.out.println("--------测试方法 开始---------");
        String runCmd = "adb shell uiautomator runtest ";
        String testCmd = jarName + ".jar " + "--nohup -c " + testName;
        exeCmd(runCmd + testCmd);
        System.out.println("--------测试方法 完成---------");
    }

    public void pushJarToAndroid(String localPath) {
        System.out.println("--------push jar 开始---------");
        localPath = "\"" + localPath + "\"";
        System.out.println("jar包路径:" + localPath);
        String pushCmd = "adb push " + localPath + " /data/local/tmp/";
        exeCmd(pushCmd);
        System.out.println("--------push jar 完成---------");
    }

    //获取项目空间
    public String getWrokSpace(){
        File f = new File("");
        return f.getAbsolutePath();
    }

    //创建工程构建文件
    public void createAntBuildXml(String jarName,String androidID){
        exeCmd("cmd /c android create uitest-project -n " + jarName + " -t "
                + androidID + " -p " + "\"" + WORKSPACE_PATH + "\"");
    }

    //执行窗口命令
    public void exeCmd(String cmd){
        System.out.println("cmd:"+cmd);
        try{
            Process result = Runtime.getRuntime().exec(cmd,
                    new String[]{"Path="+System.getenv().get("Path")+";D:\\soft\\ant\\apache-ant-1.9.7\\bin"});
            BufferedReader bf = new BufferedReader(new InputStreamReader(result.getInputStream(),"gbk"));
            String line ;
            while((line = bf.readLine()) != null){
                System.out.println(line);
            }
            BufferedReader errorBf = new BufferedReader(new InputStreamReader(result.getErrorStream(),"gbk"));
            while((line = errorBf.readLine()) != null){
                System.out.println(line);
            }
            bf.close();
            errorBf.close();
        }catch (Throwable throwable){
            System.out.println(throwable);
        }
    }

    public void modfileBuild() {
        System.out.println("--------修改build.xml 开始---------");
        StringBuffer stringBuffer = new StringBuffer();
        try {
            File file = new File("build.xml");
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (lineTxt.matches(".*help.*")) {
                        lineTxt = lineTxt.replaceAll("help", "build");
                    }
                    stringBuffer = stringBuffer.append(lineTxt).append("\t\n");
                }
                read.close();
            } else {
                System.out.println("找不到build.xml文件");
            }
        } catch (Exception e) {
            System.out.println("读取build.xml内容出错");
            e.printStackTrace();
        }
        // 重新写回build.xml
        rewriteBuildxml("build.xml", new String(stringBuffer));
        System.out.println("--------修改build.xml 完成---------");
    }

    public void rewriteBuildxml(String path, String content) {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(path));
            bw1.write(content);
            bw1.flush();
            bw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void antBuild() {
        System.out.println("--------编译build.xml 开始---------");
        exeCmd("cmd /c ant -buildfile " + "\"" + WORKSPACE_PATH + "\"");
        System.out.println("--------编译build.xml 完成---------");
    }
}
