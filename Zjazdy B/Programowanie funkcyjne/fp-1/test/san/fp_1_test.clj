(ns san.fp-1-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [san.fp-1 :refer [rev-groups]]))

(deftest test-rev-groups
  (testing "rev-groups correct usage"
    (is (= []   (rev-groups 1 [])))
    (is (= []   (rev-groups 2 [])))
    (is (= []   (rev-groups 4 [])))
    (is (= [:a] (rev-groups 1 [:a])))

    (is (= [4 3 2 1]     (rev-groups 1 [1 2 3 4])))
    (is (= [3 4 1 2]     (rev-groups 2 [1 2 3 4])))
    (is (= [1 2 3 4]     (rev-groups 4 [1 2 3 4])))
    (is (= [5 6 3 4 1 2] (rev-groups 2 [1 2 3 4 5 6])))
    (is (= [4 5 6 1 2 3] (rev-groups 3 [1 2 3 4 5 6]))))

  (testing "rev-groups incorrect usage"
    (is (thrown? clojure.lang.ExceptionInfo (rev-groups 2 [:a])))
    (is (thrown? clojure.lang.ExceptionInfo (rev-groups 3 [:a])))
    (is (thrown? clojure.lang.ExceptionInfo (rev-groups 4 [:a])))))
