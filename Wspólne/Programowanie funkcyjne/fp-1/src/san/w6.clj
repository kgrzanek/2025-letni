(ns san.w6)

(defn atom?
  [expr]
  (not (seq? expr)))

(defn constant?
  [expr var]
  (and (atom? expr) (not (= expr var))))

(defn same-var?
  [expr var]
  (and (atom? expr) (= expr var)))

(defn sum?
  [expr]
  (and (seq? expr) (= (first expr) '+)))

(defn product?
  [expr]
  (and (seq? expr) (= (first expr) '*)))

(defn make-sum
  [lhs rhs]
  (cond (number? lhs)
    (cond (number? rhs)
      (+ lhs rhs)
      (zero? lhs)
      rhs
      :else (list '+ lhs rhs))
    (number? rhs)
    (cond (zero? rhs)
      lhs
      :else (list '+ lhs rhs))
    :else (list '+ lhs rhs)))

(defn make-product
  [lhs rhs]
  (cond (number? lhs)
    (cond (number? rhs)
      (* lhs rhs)
      (= 1 lhs)
      rhs
      (zero? lhs)
      0
      :else (list '* lhs rhs))
    (number? rhs)
    (cond (= 1 rhs)
      lhs
      (zero? rhs)
      0
      :else (list '* lhs rhs))
    :else (list '* lhs rhs)))

(defn L
  [expr]
  (second expr))

(defn R
  [expr]
  (nth expr 2))

(defn deriv
  [expr var]
  (cond (constant? expr var)
        0

        (same-var? expr var)
        1

        (sum? expr)
        (make-sum (deriv (L expr) var)
                  (deriv (R expr) var))

        (product? expr)
        (make-sum (make-product (L expr)
                                (deriv (R expr) var))
                  (make-product (deriv (L expr) var)
                                (R expr)))))
        ;; ... kolejne reguły

(def e1 '(+ (* a (* x x))
           (+ (* b x)
             c)))

(deriv e1 'x)

(class (quote e1))
(print (quote abcd))
