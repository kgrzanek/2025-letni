(ns san.w5)

;; CONSES LAYER
(defn my-cons [x y]
  (fn [dispatch]
    (if (= dispatch 1)
      x
      y)))

(defn my-first [p] (p 1))
(defn my-next  [p] (p 2))

(my-first (my-cons 3 4))
(my-next  (my-cons 3 4))

;; RATIOS LAYER
(defn gcd
  [m n]
  (let [r (mod m n)]
    (if (zero? r)
      n
      (recur n r))))

(defn make-rat
  [n d]
  (let [g (gcd n d)]
    (my-cons (/ n g) (/ d g))))

(defn numer
  [x]
  (my-first x))

(defn denom
  [x]
  (my-next x))

(defn rat->str
  ^String [x]
  (str (numer x) "/" (denom x)))

(defn rat+ [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn rat* [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(rat->str (rat+ (make-rat 6 7) (make-rat 4 5)))
