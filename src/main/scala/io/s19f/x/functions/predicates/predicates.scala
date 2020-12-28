package io.s19f.x
package functions

package object predicates {

  class PredicateOps[A](p: A => Boolean) {
      
    def unary_! : A => Boolean = e => !p(e)
    
  }

  implicit def predicateOps[A](p: A => Boolean) = new PredicateOps(p)

}
