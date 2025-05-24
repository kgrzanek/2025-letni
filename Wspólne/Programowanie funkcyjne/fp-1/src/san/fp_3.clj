(ns san.fp-3)

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

1 + 2

+ 1 2 ;; Notacja polska
1 2 + ;; Notacja polska odwrócona

(+ 1 2) ;; s-expression

#_(defmacro always-with-postfix
    [expr postfix]
    `(into (into (empty ~expr) ~expr) (list ~postfix)))

;; (always-with-postfix '(1 2 3 4) :a)
(def f1
  (fn [x] (+ x 6)))

((fn [x] (+ x 6)) 3)

(fn [x] (+ x 6))

(f1 3)

(defn f2 [x]
  (+ x 6))

(def global-1 123)
(* global-1 3)

(defn foo [y]
  (+ 4 y global-1))

(class +)

(def + -)

(+ 4 5)
(def - clojure.core/+)

(+ 5 4)
(- 5 4)

;; JS
;; const f1 = (x) => x + 6;

#_(with-file "/home/kongra/Pobrane/C++/program1.cpp"
    (print it))

#_(defn with-file
    [file body]
    (let [content (slurp file)]
      (body content)))

;; sin(5)

#_(let [a 1
        b 2]
    (+ a b))

(defmacro with-file
  [file & body]
  `(let [~'it (slurp ~file)]
     ~@body))

(with-file "/home/kongra/Pobrane/C++/program1.cpp"
  (print it)
  (count it))

(class (quote this))
(class 'this)
