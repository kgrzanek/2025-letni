(ns san.w4)

(defn foo [x y]
  (+ x y))

(class (foo 2 3))
(foo 2 3)
(+ 2 3)

(defn goo [x]
  (fn [y]
    (+ x y)))

((goo 2) 3)

;; (goo 2) => (fn [y]
;;              (+ 2 y))

((fn [y] (+ 2 y)) 3)
(+ 2 3)

(defn sum-ints
  [a b]
  (if (> a b)
    0
    (+ a
       (sum-ints (inc a) b))))

(sum-ints 0 10)

(defn square [x] (* x x))

(defn sum-squares
  [a b]
  (if (> a b)
    0
    (+ (square a)
       (sum-squares (inc a) b))))

(sum-squares 1 10)

(defn pi-sum
  [a b]
  (if (> a b)
    0
    (+ (/ 1 (*' a (+ a 2)))
       (pi-sum (+ a 4) b))))

(pi-sum 1 500)

#_(defn sum
    [term next a b]
    (if (> a b)
      0
      (+ (term a)
         (sum term next (next a) b))))

(defn sum
  [term next a b result]
  (if (> a b)
    result
    (recur term next (next a) b (+' result (term a)))))

(defn step [n]
  (fn [i]
    (+ i n)))

(step 4)

(defn sum-ints'
  [a b]
  (sum identity (step 1) a b 0))

(defn sum-squares'
  [a b]
  (sum square (step 1) a b 0))

(defn pi-sum'
  [a b]
  (sum (fn [a] (/ 1 (*' a (+' a 2))))
       (step 4)
       a
       b
       0))

(sum-ints' 1 10)
(pi-sum' 1 100)

;; OPERATOR PUNKTU STAŁEGO
(defn avg
  [x y]
  (/ (+' x y) 2))

(def ε (rationalize 0.00000000001))

(println ε)

(defn FP-close-enough?
  [x y] (< (abs (- x y)) ε))

(defn FP-iter
  [f old current]
  (if (FP-close-enough? old current)
    current
    (recur f current (f current))))

(defn FIXED-POINT
  [f start]
  (FP-iter f start (f start)))

(defn sqrt
  [x]
  (FIXED-POINT (fn [y] (avg y (/ x y))) 1))

(* (sqrt 2) (sqrt 2))

(defn average-damp
  [f]
  (fn [x] (avg x (f x))))

(defn sqrt'
  [x]
  (FIXED-POINT (average-damp (fn [y] (/ x y))) 1))

(double (sqrt' 2))

;; METODA NEWTONA
(def dx (rationalize 0.0000001))

(defn deriv
  [g]
  (fn [x]
    (/ (- (g (+ x dx)) (g x)) dx)))

(defn Newtons-transform
  [g]
  (let [g' (deriv g)]
    (fn [x]
      (- x (/ (g x) (g' x))))))

(defn Newtons-method
  [f y]
  (FIXED-POINT (Newtons-transform f) y))

(defn sqrt''
  [x]
  (Newtons-method (fn [y] (- (square y) x)) ;; y → y^2 - x
    1))

(double (sqrt'' 2))

(defn qubic-root
  [start x]
  (Newtons-method (fn [y] (- (* y y y) x)) ;; y → y^3 - x
    start))

;; FIXED POINT v2
(defn FIXED-POINT-series
  [f start]
  (->> start
       (iterate f)
       (partition 2 1)
       (take-while (fn [[x y]] (not (FP-close-enough? x y))))
       (map second)))

(defn sqrt-series
  [x]
  (FIXED-POINT-series (average-damp (fn [y] (/ x y))) 1))

(defn sqrt'''
  [x]
  (last (sqrt-series x)))

;; NEWTON'S METHOD v2
(defn Newtons-method-series
  [f y]
  (FIXED-POINT-series (Newtons-transform f) y))

(defn qubic-root-series
  [start x]
  (Newtons-method-series (fn [y] (- (* y y y) x)) ;; y → y^3 - x
    start))

;; (println (.length (str (last (take 8 (qubic-root-series 1 27))))))
