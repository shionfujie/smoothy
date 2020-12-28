package io.s19f
package x

package object strings {

  class StringOps(s: String) {

    def sentences = s.replaceAll("""([.!?])\s+(?=.)""", "$1|").split("[|]")

    def title = s.split("""(?<=[^\w’']+)(?=\w)""").map(_.capitalize).mkString

  }

  implicit def ops(s: String) = new StringOps(s)

}
