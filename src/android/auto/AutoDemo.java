package android.auto;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * Created by yuhao.zx on 16-5-15.
 */
public class AutoDemo extends UiAutomatorTestCase {
    public void testNewDemo(){
        try{
            UiDevice.getInstance().pressMenu();
        }catch (Throwable t){
            System.out.println("error"+t);
        }
        System.out.println("im working !!!!");
    }
}
