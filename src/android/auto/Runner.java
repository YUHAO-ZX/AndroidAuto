package android.auto;

import android.auto.tools.AutoHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yuhao.zx on 16-5-15.
 */
public class Runner {

    public static void main(String[] args) throws IOException {
        new AutoHelper("Demo","android.auto.AutoDemo","testNewDemo","1");
    }
}
