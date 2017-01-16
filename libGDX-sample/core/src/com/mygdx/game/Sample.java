package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


/**
 * Created by yy_yank on 2017/01/16.
 */
public class Sample {


    public void run() throws IOException {

        { // NumberUtils
            System.out.println(NumberUtils.floatToIntColor(-4.2535296E37f));
            System.out.println(NumberUtils.floatToRawIntBits(-4.2535296E37f));
            System.out.println(NumberUtils.intToFloatColor(Color.BLACK.toIntBits()));
            System.out.println(Color.BLACK.toFloatBits());
            System.out.println(Color.BLUE.toIntBits());
            System.out.println(NumberUtils.floatToRawIntBits(222.55f));
            System.out.println(NumberUtils.doubleToLongBits(1.1115d));
            System.out.println(NumberUtils.floatToIntBits(1.1115f));
            System.out.println(NumberUtils.intBitsToFloat(11));
            System.out.println(NumberUtils.longBitsToDouble(12L));
        }


        { // TimeUtils

            // System.currentTimeMillis()と同じ
            System.out.println("ミリ秒 : " + TimeUtils.millis()); // ミリ秒 : 1484574498965

            // ミリ秒をナノ秒へ変換
            System.out.println("ミリ秒をナノ秒へ変換 : " + TimeUtils.millisToNanos(TimeUtils.millis())); // ミリ秒をナノ秒へ変換 : 1484574498965000000

            // ナノ秒をミリ秒へ変換
            System.out.println("ナノ秒をミリ秒へ変換 : " + TimeUtils.nanosToMillis(TimeUtils.millisToNanos(TimeUtils.millis()))); // ナノ秒をミリ秒へ変換 : 1484574498965

            // 引数からの経過時間(ミリ秒)
            System.out.println("2011/12/5 11:11からの経過時間（ミリ秒）" + TimeUtils.timeSinceMillis(TimeUtils.nanosToMillis(LocalDateTime.of(2011, 12, 5, 11, 11).getNano()))); // 2011/12/5 11:11からの経過時間（ミリ秒）1484574498976

            // 引数からの経過時間(ナノ秒)
            System.out.println("2011/12/5 11:11からの経過時間（ナノ秒）" + TimeUtils.timeSinceNanos(LocalDateTime.of(2011, 12, 5, 11, 11).getNano())); // 2011/12/5 11:11からの経過時間（ナノ秒）872694705378791
        }


        {// PropertiesUtils
            ObjectMap<String, String> map = new ObjectMap<>();
            PropertiesUtils.load(map, new FileReader(Gdx.files.internal("hoge.properties").file()));
            map.forEach(System.out::println);
        }

        {// ScreenUtils

            //  defaultのフレームバッファーをbyte[]で返す。配列のlengthはscreen width * height * 4。
//            byte[] bytes = ScreenUtils.getFrameBufferPixels(true);


            // フレームバッファーから指定した位置をreadしてPixmapを返す
            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
//            Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, width, height);

            // フレームバッファーからTextureを生成する
//            TextureRegion region = ScreenUtils.getFrameBufferTexture();
        }


        {// BufferUtils
            // Factoryメソッド
            ByteBuffer src = BufferUtils.newByteBuffer(128);
            byte[] b = new byte[128];
            for (int i = 0; i < 128; i++) {
                b[i] = new Byte(String.valueOf(i));
            }
            src.put(b);
            ByteBuffer dest = BufferUtils.newByteBuffer(128);


            // コピー
            BufferUtils.copy(src, dest, 10);
        }

        { // StreamUtils
            ByteBuffer output = BufferUtils.newByteBuffer(100);

            Path tmpFile = Files.createTempFile(Paths.get("").toAbsolutePath(), "tmp", "file");
            tmpFile.toFile().deleteOnExit();
            StreamUtils.copyStream(Gdx.files.internal("hoge.properties").read(), new FileOutputStream(tmpFile.getFileName().toString()));
            InputStream is = Gdx.files.internal("hoge.properties").read();

            // Stringに変換
            String streamToString = StreamUtils.copyStreamToString(is);
            System.out.println("streamToString" + streamToString);

            // close処理
            StreamUtils.closeQuietly(is);
        }

    }
}
