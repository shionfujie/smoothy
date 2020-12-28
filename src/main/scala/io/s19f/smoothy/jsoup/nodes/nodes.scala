package io.s19f
package smoothy
package jsoup

package object nodes {

  import org.jsoup.nodes.Element

  class ElementOps(el: Element) {

    def $(selector: String) = el.selectFirst(selector)

    def $$(selector: String) = el.select(selector)

    def nextElementSiblings: Stream[Element] =
      Stream
        .iterate(el.nextElementSibling)(_.nextElementSibling)
        .takeWhile(el => el != null)

  }

  implicit def elementOps(el: Element) = new ElementOps(el)

}
