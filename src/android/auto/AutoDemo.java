package android.auto;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * Created by yuhao.zx on 16-5-15.
 */
public class AutoDemo extends UiAutomatorTestCase {
    private String s = "18458130694&zx60342800," +
//            "13148465379,13116577045,13175352964,13148479243," +
//            "15578074510,13186997254,13064594693,13175306948," +
//            "13058649661,13132621046,13074671745,13087914676," +
//            "13047824845,13014896242,13296734474,13248748705," +
//            "13025015407,13175498048,13186912674,15678385084," +
//            "13082844097,13248986942,13282416449,13058625476," +
//            "13299218042,15658614245,15676249149,13216974236," +
//            "15676711476,13175497683,13175140947,13034654217," +
//            "13184292541,13117610041,13184358970,13164956426," +
//            "13058647128,15669746742,13157614031,13217873045," +
//            "13020964079,15577912547,15577411424,13175869714," +
//            "13106093164,13197718034,13221722104,13100498104," +
//            "13291633248,13073824079,13132911475,13116604293," +
//            "13064534130,13106094819,15678826714,13086709674," +
//            "13173745097,13186953134,13263817472,13106083574," +
//            "13005932943,13197595064,13291645621,13106021481," +
//            "15677324745,13248442582,13277816374,13094732747," +
//            "13148362749,13221564796,13175874258,15577721854," +
//            "13014911804,13074652403,13020970247,13064522547," +
//            "13296722644,13097814364,13257726741,13237710549," +
//            "13148306463,13064757842,13291484105,13023648194," +
//            "13148369742,13148396147,13296737194,13148386745," +
            "13148462761,13148485740,13282401364,13148434751," +
            "13148361497,13250831524,13148476371,13148459682," +
            "13148436961,13184214363,13184236406,13094810749," +
            "13148389407,13291834461,13067945436,13248413985," +
            "13094817451,13248410867,13148463957,13184231843," +
            "13148497173,13148490406,13148401593,13291836245," +
            "13094816948,13285715446,13094817748,13148472842," +
            "13291842437,13248445351,13291851204,13148374376," +
            "13184234960,15676371640,13086728214,13057844387," +
            "13014896384,13157046914,13282665841,15678871480," +
            "13116501842,13105627164,15676244164,13175367545," +
            "13148408051,13185604192,13148464331,15578127430," +
            "13132914237,13114796764,13175856648,15506764804," +
            "13014931902,13087734643,13211304492,13064755942," +
            "13093808747,13217734098,13173784669,13097817734," +
            "13184232912,13184234940,13123814769,15506873045," +
            "15677354245,13175334635,13106478490,13217819684," +
            "13058720490,13132714964,15578170984,13106485264," +
            "15678710934,15677034246,13221604843,13071863162," +
            "13094813249,13064598343,13136478996,15677132849," +
            "13185769046,15578074801,13221724165,13014845245," +
            "13106489924,13186848043,13094765160,13094765867," +
            "15677296541,13105647923,15577393254";
    public void testNewDemo(){
        try{
            String[] phones = s.split(",");
            for(String phone : phones){
                UiDevice op = getUiDevice();
                UiObject appsTab = new UiObject(new UiSelector().textStartsWith("1"));
                if(null == appsTab){
                    System.out.println("null");
                }
                String text = appsTab.getText();
                String pwd = "zzz";
                phone.split("&");
                if(phone.split("&").length == 2){
                    pwd = phone.split("&")[1];
                    phone = phone.split("&")[0];
                }
                appsTab.setText(phone);
                appsTab.clickBottomRight();
                for (int i = 0; i < text.length(); i++) {
                    op.pressDelete();
                }
                op.click(515,600);
                System.out.println(op.getCurrentPackageName());
                UiObject appsTab1 = new UiObject(new UiSelector().packageName("com.zgy"));
                appsTab1.setText(pwd);
                Thread.sleep(1000);
                op.click(300, 910);
                Thread.sleep(3000);
                op.pressBack();
                Thread.sleep(1000);
                op.click(994, 1477);
                Thread.sleep(1000);
                op.click(1014, 171);
                Thread.sleep(1000);
                op.click(603, 1806);
                Thread.sleep(1000);
                op.click(554, 419);
            }

        }catch (Throwable t){
            System.out.println("error:"+t);
        }
        System.out.println("im working !!!!");
    }
}
