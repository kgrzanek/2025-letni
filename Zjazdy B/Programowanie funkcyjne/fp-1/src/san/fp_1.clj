(ns san.fp-1
  (:require
   [clojure.string :as str])
  (:gen-class))

#_(defn -main
    [& _args])

(defn divisible-by? [n m]
  (zero? (mod n m)))

(defn goal? [n]
  (or (divisible-by? n 3) (divisible-by? n 5)))

(reduce + (filter goal? (range 10)))

;; Pr. 1. Suma liczb parzystych do n.
(even? 3)
(odd?  3)
(reduce + (filter even? (range 1000)))
(reduce + (filter odd?  (range 1000)))

(->> 1000 range (filter even?) (reduce +))
;; (f a1) === (->> a1 f)

(filter even? [1 2 3 4 5])
;;      pred  coll

;; [1 2 3 4 5].filter(even?);

;; Pr. 2. Odwrotność liczby, np. 12345 => 54321
(defn reverse-int [n]
  #p (Long/parseLong #p (str/join #p (reverse #p (str #p n)))))

(defn reverse-int' [n]
  (->> n str reverse str/join Long/parseLong))

(reverse-int' 12345)

;; Pr. z tablicy [1 2 3 4 5 6] ==> [5 6 3 4 1 2]

(defn rev-groups [n coll]
  (when-not (and (sequential? coll) (divisible-by? (count coll) n))
    (throw (ex-info "Cannot rev-pairs" {:coll coll :count (count coll)})))

  #_(into (empty coll) (apply concat (reverse (partition n coll))))

  (->> coll
       (partition n)
       reverse
       (apply concat)
       (into (empty coll))))

(def v1 (vec (range 1000)))

;; (use 'criterium.core)
;; (quick-bench (rev-groups 2 v1))

(let [x 1
      y 2
      z 3]

  (+ x y z))
