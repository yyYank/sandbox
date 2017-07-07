package v_m8

fun runs(a: IntArray): Int {
  return a.answer({(i: Int): Boolean -> i + 1 < a.size && a.get(i) != a.get(i + 1) })
}
 
fun IntArray.answer(filter: (i: Int) -> Boolean): Int {
  var result =when (this.size) { 0 -> 0 else -> 1}
  for (i in this.indices) if (filter(i)) result++
  return result
}
