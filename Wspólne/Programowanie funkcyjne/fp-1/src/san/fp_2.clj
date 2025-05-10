(ns san.fp-2)

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(defn **
  ([b n] (** b n 1))

  ([b ^long n result]
   (if (zero? n)
     result

     (recur b (dec n) (*' result b)))))

(defn square [x] (*' x x))

(defn fast-expt [b ^long n]
  (cond (zero? n) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (*' b (fast-expt b (dec n)))))

(defn **-N
  [b ^long n result]
  (cond (zero? n)
        result

        (even? n)
        (recur (*' b b) (bit-shift-right n 1)
               result)

        :else
        (recur b (dec n) (*' b result))))

;; (quick-bench (**-N 1000 1000 1))

;; (matht/expt 1000 1000)
;; (use 'criterium.core)

;; (quick-bench (matht/expt 1000 1000))
;; (quick-bench (fast-expt 1000 1000))

(time (fast-expt 2 10000000))
(time (**-N 2 10000000 1))

(.length (str (fast-expt 1000 1000)))

;; (->> (fast-expt 1000  1000) str        .length)
;;       fastExpt (1000, 1000).toString ().length () ;

;; var s = n.toString () ;
;; var c = s.length () ;
