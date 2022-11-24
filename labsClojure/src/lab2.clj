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

(defn takeTimeSieveInString [n]
  (nth (clojure.string/split (with-out-str (time (nth primeSieve n))) #" ") 2))

(defn takeTimeFloat [n]
  (Float/parseFloat (takeTimeSieveInString n)))

(test/deftest lab2Test
  (test/testing "tests:"
    (test/is (> (takeTimeFloat 1000) (* (takeTimeFloat 1000) 100)))
    (test/is (= (time (nth primeSieve 999)) (time (nth primeSieve 999))))
    (test/is (= (nth primeSieve 0) 2))
    (test/is (= (takePrimes 5) (lazy-seq [2, 3, 5, 7, 11])))))