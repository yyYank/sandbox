import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
 
/**
 * とりあえず、いじってみた。これをクラス分割する感じの予定で。
 * @author yank
 */
public class Janken {
 

    //**********************************************
    // fields
    //**********************************************

    // 定数ならstaticつけちゃう。でも定数持たせるより、やっぱりenum使いたくはなる気がする
    private static final String ROCK = "グー";
    private static final String PAPER = "パー";
    private static final String SCISSORS = "チョキ";

    // randomには再代入しないのでfinalつけている(不変なRandom変数)
    private final Random random = new Random();

    private int win;
    private int loss;
 
    // この書き方だと型推論できないのでダイヤモンド演算子使えないんですね(こんな書き方するなという話)
    private final Map<String, String> results = new HashMap<String, String>(){{
        put(ROCK, PAPER);
        put(PAPER, SCISSORS);
        put(SCISSORS, ROCK);
    }};
    
    // switch文もmapにしちゃえ的な
    private final Map<Integer, String> relations = new HashMap<Integer, String>(){{
        put(1, PAPER);
        put(2, SCISSORS);
        put(3, ROCK);
    }};

    class HogeHogeException extends RuntimeException{
        HogeHogeException() {
            super();
        }
        
        HogeHogeException(String message) {
            super(message);
        }
    }

 
    //**********************************************
    // methods
    //**********************************************
    
    // 例外処理は何も考えていないので参考にはならない
    public static void main(String... args) {
        try {
            Janken janken = new Janken();
            janken.start();
        } catch (HogeHogeException e) {
            System.err.println("エラーや！");
        } catch (NumberFormatException | NullPointerException e) {
            System.err.println("1か2か3だけを入れてね！！！");
        }
    }

    private int randomShapeKey() {
        // java.util.Randomに変えてみた。from-toが無いので、0からNまでの数字をランダム生成。して + 1
        return random.nextInt(3) + 1;
    }
 
    // あんまりシグネチャ変えたくなかったので、中身の簡略化を試みたところ、mapの単純なgetになってしまた
    private String transferNum(int num) {
        return relations.get(num);
    }
 
    // 状態を表すis...メソッドに副作用を持たせたくないため、単純な判定だけにしちゃった
    private boolean isTied(String userShape, String cpuShape) {
        return userShape.equals(cpuShape);
    }
 
    private boolean isWon(String userShape, String cpuShape) {
        String strongerShape = results.get(userShape);
        return !strongerShape.equals(cpuShape);
    }
    
    private boolean isNextGame(BufferedReader br) throws IOException{
            System.out.println();
            System.out.println("もう1回？");
            System.out.println("続けるなら何か入力してEnterを押してね！やめるならEnterのみ押してね！");
 
            String inputText = br.readLine();
            return !inputText.isEmpty();
    }
 
    private void start() {
        // このメッセージがmainメソッドに入ってたのが気になった
        System.out.println("じゃんけんするよ！何出す？？");
        execute();
    }
    
    // ライフサイクルっぽい名前にするだけでそれ風に見えるという気持ち
    private void restart() {
        execute();
    }
 
    // matchだとMatcherを個人的に連想しちゃったので名前変えた
    private void execute() {
 
        // try with resourcesでstreamがcloseされるはず
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("1 グー");
            System.out.println("2 チョキ");
            System.out.println("3 パー");
            System.out.print("1〜3の数字を打ち込んでEnter押してね！⇛");
 
            int inputNum = Integer.parseInt(br.readLine());
            // 変数宣言はJavaでは実行される直前で行うらしい
            String userShape = transferNum(inputNum);
            String cpuShape = transferNum(randomShapeKey());
 

            // if文で勝ち、負け、引き分けを分岐してるわけですが、手続き的で嫌ではある
            if (isTied(userShape, cpuShape)) {
                System.out.println("あなたは" + userShape + "、わたしも" + cpuShape + "。あいこ！もう1回！");
                restart();
            } else if(isWon(userShape, cpuShape)){
                win++;
                System.out.println("あなたは" + userShape + "で、わたしは" + cpuShape + "。あなたの勝ち！");
            } else {
                loss++;
                System.out.println("あなたは" + userShape + "で、わたしは" + cpuShape + "。わたしの勝ち！");
            }
            System.out.println("いま、" + win + "勝" + loss + "敗だよ！");

            if(isNextGame(br)) { 
                restart();
            } 
       // チェック例外はcatchして任意の例外にして再throwするのが良いと思われる
       } catch(IOException e) {
           throw new HogeHogeException(e.getMessage());
       }
   }
}
