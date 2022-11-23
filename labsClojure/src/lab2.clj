(ns lab2)
(require '[clojure.test :as test])

(defn sieve [start]
  (cons (first start)
        (lazy-seq (sieve (filter #(not= 0 (mod % (first start)))
                                 (rest start))))))

(def primeSieve
  (sieve (iterate inc 2)))

(defn takePrimes [n]
  (take n (sieve (iterate inc 2))))

;(println (time (nth primeSieve 10)))
;(println (time (nth primeSieve 10)))
;(println (takePrimes 5))
;(println (type (takePrimes 5)))

(test/deftest lab2Test
  (test/testing "tests:"
    (test/is (<= (time (nth primeSieve 1000)) (time (nth primeSieve 1000))))
    (test/is (= (nth primeSieve 0) 2))
    (test/is (= (takePrimes 5) (lazy-seq [2, 3, 5, 7, 11])))))