package io.s19f
package smoothy
package jsoup

package object nodes {

  import scala.collection.JavaConverters._
  import org.jsoup.nodes.Element
  import org.jsoup.select.Elements

  class ElementOps(el: Element) {

    def $(selector: String) = el.selectFirst(selector)

    def $$(selector: String) = el.select(selector)

    def nextElementSiblings: Stream[Element] =
      Stream
        .iterate(el.nextElementSibling)(_.nextElementSibling)
        .takeWhile(el => el != null)

    def headings = $$("h1,h2,h3,h4,h5,h6")

  }

  implicit def elementOps(el: Element) = new ElementOps(el)

  implicit def elements2coll(els: Elements) = els.asScala

}
