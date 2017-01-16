import com.badlogic.gdx.utils.NumberUtils;

/**
 * Created by yy_yank on 2017/01/16.
 */
public class Sample {


    public static void main(String[] args) {
        new Sample().run();
    }

    public void run() {
        System.out.println(NumberUtils.floatToIntColor(1.572681E-39f));
        System.out.println(NumberUtils.intToFloatColor(0xabaaaff));
        System.out.println(NumberUtils.floatToRawIntBits(222.55f));
        System.out.println(NumberUtils.doubleToLongBits(1.1115d));
        System.out.println(NumberUtils.floatToIntBits(1.1115f));
        System.out.println(NumberUtils.intBitsToFloat(11));
        System.out.println(NumberUtils.longBitsToDouble(12L));
    }
}
