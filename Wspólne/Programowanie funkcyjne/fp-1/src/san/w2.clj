(ns san.w2)

;; Pierwiastek kwadratowy z nieujemnej wartości x należącej do R:
;; sqrt(x) to jest takie y, że y^2 = x.x

;; <span style="color:red">Jakiś tekst</span>

(+ 2 3 4 5)

2 + 3 ;; infix
+ 2 3 ;; prefix
2 3 + ;; postfix

(class Math/PI)

(def π 3.14159)
(+ π 5)

;; (def square (fn [x] (* x x)))

(fn [x] (* x x))

;; Wyrażenie, którego wartością jest funkcja (procedura), nazywamy
;; λ - wyrażeniem.

;; Rachunek λ
;; λ x. x * x
;; square = λ x. x * x

(defn square [x]
  (* x x))

;; |x| =  x dla x >= 0
;;     = -x dla x <  0

(defn abs' [x]
  (if (>= x 0) #_predicate
    x          #_consequent
    (- x)      #_alternative))

(abs' -5)

;; (if <predicate> <consequent> <alternative>)

;; Ternary conditional
;; int n = k == 4 ? 5 : 6;
;;         <pred> ?<c>: <a>

;; true/<not-nil> vs false/nil

(class false)
(class nil)

(if "xyz" 4 5)

(= false nil)

(def nan (/ 0.0 0.0))

(== nan nan)

(= "abcd" true)

(or nil nil nil false)
(and 1 2 3 4 5 nil)

(def ε 0.000001)

(defn good-enough? [g x]
  ;; |g^2 - x| < ε
  (< (abs' (- (square g) x)) ε))

(defn avg [x y]
  (/ (+ x y) 2))

(defn improve [g x]
  ;; G ← (avg G (/ X G))
  (avg g (/ x g)))

(defn sqrt [g x]
  (if (good-enough? g x)
    g
    (sqrt (improve g x) x)))

(square (double (sqrt 1 2)))
(double (sqrt 1 2))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(sum-of-squares 3 4)
(+ (square 3) (square 4))
(+ (* 3 3) (* 4 4))
(+ 9 16)
25

(+ ε 4)

;; (+ f 4)

(class (quote f))
(class :f)
