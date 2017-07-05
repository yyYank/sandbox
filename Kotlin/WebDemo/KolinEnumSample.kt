fun main(args: Array<String>) {
    //==============================================
    // enumの持つ基本的なメソッド
    //==============================================

    println(Direction.NORTH.name)
    println(Direction.NORTH.toString())
    println(Direction.SOUTH.ordinal)
    println(Direction.SOUTH.compareTo(Direction.NORTH))
    println(Direction.SOUTH == Direction.NORTH)


    //==============================================
    // whenでパターンマッチ
    //==============================================
    val d = Direction.SOUTH
    when (d) {
        Direction.NORTH -> println("north")
        Direction.SOUTH -> println("south")
        Direction.WEST -> println("west")
        Direction.EAST -> println("east")
    }

    //==============================================
    // Stateパターン
    //==============================================

    println(ProtocolState.WAITING.signal().signal().signal().signal())

    //==============================================
    // プロパティを持つenum
    //==============================================

    println(Color.RED.rgb)

    //==============================================
    // Kotlin lambda
    //==============================================
    Yen.values().filter { it.price > 100 }.forEach { println(it) }

    //==============================================
    // Java lambda
    //==============================================
    //Stream.of(Yen.values()).filter(x -> x.getPrice() > 100).forEach(System.out::println);
}
