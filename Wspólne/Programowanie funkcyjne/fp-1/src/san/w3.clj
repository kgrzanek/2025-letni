(ns san.w3)

(defn my-if [pred consequent alternative]
  (cond pred   consequent
        :else alternative))

(def n 20)

(my-if (even? n) 5 7)
(my-if (odd?  n) 5 7)

(if (even? n) 5 7)
(if (odd?  n) 5 7)

(defn test-1 [n]
  (my-if (not= 0 n) (/ 1 n) :division-by-zero))

#_(test-1 0)

(defn test-2 [n]
  (if (not= 0 n) (/ 1 n) :division-by-zero))

(test-2 0)

;; ARYTMETYKA PEANO
;; 1. Definicja rekurencyjna, proces iteracyjny:
(defn p+ [x y] ;; x jest nieujemne
  (if (= x 0)
    y
    (p+ (dec x) (inc y))))

(dec 4)
(inc 4)

;;  x y
(p+ 3 4)
(p+ 2 5)
(p+ 1 6)
(p+ 0 7)
7

;; 2. Definicja rekurencyjna, proces rekurencyjny:
(defn r+ [x y]
  (if (= x 0)
    y
    (inc (r+ (dec x) y))))

;;  x y
(r+ 3 4)
(inc (r+ (dec 3) 4))
(inc (r+ 2 4))
(inc (inc (r+ (dec 2) 4)))
(inc (inc (r+ 1 4)))
(inc (inc (inc (r+ (dec 1) 4))))
(inc (inc (inc (r+ 0 4))))
(inc (inc (inc 4)))
(inc (inc 5))
(inc 6)
7

(r+ 3 4) ;; recurrere - łac. przybiec z powrotem
(inc (r+ 2 4))
(inc (inc (r+ 1 4)))
(inc (inc (inc (r+ 0 4))))
(inc (inc (inc 4)))
(inc (inc 5))
(inc 6)
7

(defn silnia [n]
  (if (= n 0)
    1
    (*' n (silnia (dec n)))))

;; (silnia 10000)

(defn factorial [n value]
  (if (= n 0)
    value
    (recur (dec n) (*' value n))))

(factorial 10000 1)

;; Ciąg Fibonacciego
(defn fib [n]
  (if (or (= 0 n) (= 1 n))
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(map fib (range 20))
;; (time (fib 40))

(defn fib-gen [[a b]]
  [b (+' a b)])

(fib-gen [2 3])

;; (time (last (take 1000 (map first (iterate fib-gen [0 1])))))
