import scala.collection.mutable

package object implicits {

  implicit class MyRichMap[K,V](val self: Map[K,V]) extends AnyVal {
    def mix(other: Map[K,V])(f: (V,V) => V): Map[K,V] = {
      val result = mutable.Map(self.toSeq: _*)
      other.toSeq.foreach { case (k, v) =>
        if (result.contains(k))
          result(k) = f(result(k), v)
        else
          result(k) = v
      }
      Map(result.toSeq: _*)
    }
  }
}
